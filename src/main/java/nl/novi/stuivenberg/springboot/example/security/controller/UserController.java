package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.exception.BadRequestException;
import nl.novi.stuivenberg.springboot.example.security.service.UserService;
import nl.novi.stuivenberg.springboot.example.security.service.UserServicePreAuth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class UserController {

//    private UserService userService;
    private UserServicePreAuth userServicePreAuth;

    public UserController(UserService userService, UserServicePreAuth userServicePreAuth) {
//        this.userService = userService;
        this.userServicePreAuth = userServicePreAuth;
    }


    //FUNCTIONS USER:
    @PatchMapping(value = "/password/id/{id}")
    public ResponseEntity<Object> changeUserPassword(@PathVariable("id") long id, @RequestBody String newPassword) {
//        userService.changePassword(newPassword, id); OLD!!
//        return ResponseEntity.ok("Password updated from user with id: " + id); OLD!!
        userServicePreAuth.changePassword(newPassword, id);
        return ResponseEntity.ok("Password changed");
    }

    @PatchMapping(value = "/newsletter/id/{id}")
    public ResponseEntity<Object> subscribeToNewsletter(@PathVariable("id") long userId) {
//        userService.subscribeToNewsletter(userId);
        userServicePreAuth.subscribeToNewsletter(userId);
        return ResponseEntity.ok("Preferences changed");
    }

    @PatchMapping(value = "/upload/id/{id}")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long userId, @RequestParam("file") MultipartFile file) throws IOException {
        userServicePreAuth.uploadProfilePicture(userId, file);
        return ResponseEntity.ok("Photo Accepted");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long userId) {
        userServicePreAuth.removeUser(userId);
        return ResponseEntity.ok("User deleted with id: " + userId);
    }

    @PatchMapping(value = "subtract/coins/id/{id}")
    public ResponseEntity<Object> subtractCoinsUser(@PathVariable("id") long userId) {
        userServicePreAuth.subtractCoins(userId);
        return ResponseEntity.ok("Coins subtracted");
    }

    @PatchMapping(value = "/balance/id/{id}")
    public ResponseEntity<Object> updateCoinBalance(@PathVariable ("id") long userId, @RequestBody Long amount) {
        userServicePreAuth.updateCoinBalance(userId, amount);
        return ResponseEntity.ok("Balance is updated");
    }


    //FUNCTIONS ADMIN:
    @GetMapping(value = "/username/{username}")
    public ResponseEntity<Object> findUserByUsername(@PathVariable("username") String username) {
        User user = userServicePreAuth.getUserWithUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/password/{password}")
    public ResponseEntity<Object> findUserByPassword(@PathVariable("password") String password) {
        User user = userServicePreAuth.getUserWithPassword(password);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable("id") long userId) {
        User user = userServicePreAuth.getUserWithId(userId);
        return ResponseEntity.ok(user);
    }

}
