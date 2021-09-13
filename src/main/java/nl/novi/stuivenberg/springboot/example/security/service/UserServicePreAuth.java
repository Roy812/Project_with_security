package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserServicePreAuth {

    private UserServiceImpl userService;
//    private ReviewServiceImpl reviewService;

    public UserServicePreAuth(UserServiceImpl userService, ReviewServiceImpl reviewService) {
        this.userService = userService;
//        this.reviewService = reviewService;
    }

    //FUNCTIONS USER:
    @PreAuthorize("hasRole('USER')")
    public String changePassword(String newPassword, long userId) {
        userService.changePassword(newPassword, userId);
        return "Password changed";
    }

    @PreAuthorize("hasRole('USER')")
    public String subscribeToNewsletter(long userId) {
        userService.subscribeToNewsletter(userId);
        return "Preferences changed";
    }

    @PreAuthorize("hasRole('USER')")
    public String uploadProfilePicture(long userId, MultipartFile file) throws IOException {
        userService.uploadPicture(userId, file);
        return "Picture uploaded";
    }

    @PreAuthorize("hasRole('USER')")
    public String updateCoinBalance(long userId, Long amount) {
        userService.updateCoinBalance(userId, amount);
        return "Balance updated";
    }

    @PreAuthorize("hasRole('USER')")
    public String subtractCoins(long userId) {
        userService.subtractCoins(userId);
        return "Review deleted";
    }

    @PreAuthorize("hasRole('USER')")
    public String removeUser(long userId) {
        userService.removeUser(userId);
        return "User Removed";
    }

    //FUNCTIONS ADMIN:
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserWithUsername(String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User getUserWithPassword(String password) {
        Optional<User> user = Optional.ofNullable(userService.findByPassword(password));
        return user.get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User getUserWithId(long userId) {
        User user = userService.getUserWithId(userId);
        return user;
    }

    //Test Functions
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String generateUserContent() {
        return "User Content.";
    }

    @PreAuthorize("hasRole('MODERATOR')")
    public String generateModContent() {
        return "Moderator Board.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String generateAdminContent() {
        return "Admin Board.";
    }
}
