package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.dto.changePasswordDTO;
import nl.novi.stuivenberg.springboot.example.security.exception.BadRequestException;
import nl.novi.stuivenberg.springboot.example.security.service.UserService;
import nl.novi.stuivenberg.springboot.example.security.service.UserServiceImpl;
import nl.novi.stuivenberg.springboot.example.security.service.UserServicePreAuth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class UserController {

//    private UserService userService;
//    private UserServicePreAuth userServicePreAuth;
private UserServiceImpl userService;

//    public UserController(UserService userService, UserServicePreAuth userServicePreAuth) {
////        this.userService = userService;
//        this.userServicePreAuth = userServicePreAuth;
//    }
public UserController(UserServiceImpl userService) {
        this.userService = userService;
}

    //FUNCTIONS USER:
    @PatchMapping(value = "/password/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> changeUserPassword(@PathVariable("id") long id, @RequestBody changePasswordDTO dto) {
        userService.changePassword(dto.newPassword, id);
        return ResponseEntity.ok("Password changed");
    }

    @PatchMapping(value = "/newsletter/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> subscribeToNewsletter(@PathVariable("id") long userId) {
        userService.subscribeToNewsletter(userId);
        return ResponseEntity.ok("Preferences changed");
    }

    @PatchMapping(value = "/upload/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long userId, @RequestParam("file") MultipartFile file) throws IOException {
        userService.uploadPicture(userId, file);
        return ResponseEntity.ok("Photo Accepted");
    }

    @GetMapping(value = "/picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> getUserProfilePicture(@PathVariable ("id") long userId) {
    var picture = userService.getUserProfilePicture(userId);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"picture\"").body(picture);
    }


    @DeleteMapping(value = "/delete/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok("User deleted with id: " + userId);
    }

    @PatchMapping(value = "subtract/coins/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> subtractCoinsUser(@PathVariable("id") long userId) {
        userService.subtractCoins(userId);
        return ResponseEntity.ok("Coins subtracted");
    }


    //FUNCTIONS ADMIN:
    @GetMapping(value = "/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/password/{password}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findUserByPassword(@PathVariable("password") String password) {
        User user = userService.findByPassword(password);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasAnyRole('USER') or ('ADMIN')")
    public ResponseEntity<Object> findUserById(@PathVariable("id") long userId) {
        User user = userService.getUserWithId(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/balance/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateCoinBalance(@PathVariable ("id") long userId, @RequestBody Long amount) {
        userService.updateCoinBalance(userId, amount);
        return ResponseEntity.ok("Balance is updated");
    }

}
