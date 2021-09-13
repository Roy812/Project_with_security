package com.customuserdetailsservice.demo.controller;

import com.customuserdetailsservice.demo.Exception.BadRequestException;
import com.customuserdetailsservice.demo.model.User;
import com.customuserdetailsservice.demo.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class Usercontroller {

    private UserService userService;

    public Usercontroller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("New user added: " + user);
    }

    @PostMapping(value = "/subscribe/newsletter")
    public ResponseEntity<Object> subscribeToNewsletter(@RequestBody long userId) {
        userService.subscribeToNewsletter(userId);
        return ResponseEntity.ok("Preferences changed");
    }

    @PatchMapping(value = "/password/id/{id}")
    public ResponseEntity<Object> changeUserPassword(@PathVariable("id") long id, @RequestBody String newPassword) {
        userService.changePassword(newPassword, id);
        return ResponseEntity.ok("Password updated from user with id: " + id);
    }

    @PostMapping(value = "/upload/id/{id}")
    public void uploadProfilePicture(@PathVariable ("id") long id, @RequestParam("file") MultipartFile file) {
        try {
            userService.uploadPicture(id, file);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @GetMapping(value = "/download/id/{id}")
    public ResponseEntity<byte[]> downloadLessonGuide(@PathVariable ("id") long id) {
        var downloadbytes = userService.getProfilePicture(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }



}
