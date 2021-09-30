package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    void changePassword(String newPassword, long id);
    void uploadPicture(long id, MultipartFile file) throws IOException;
    void subscribeToNewsletter(long userId);
    byte[] getUserProfilePicture(long id);
    void updateCoinBalance(long userId, Long amount);
    void subtractCoins(long userId);
    Optional<User> findByUsername(String username);
    User findByPassword (String password);
    User getUserWithId(long userId);
    void removeUser(long id);
}
