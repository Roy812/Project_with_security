package com.customuserdetailsservice.demo.service;

import com.customuserdetailsservice.demo.model.Agenda;
import com.customuserdetailsservice.demo.model.Coins;
import com.customuserdetailsservice.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    void changePassword(String newPassword, long id);
    User addUser(User user);
    Optional<User> getUser(String username);
    void uploadPicture(long id, MultipartFile file) throws IOException;
    void subscribeToNewsletter(long userId);
    byte[] getProfilePicture(long id);




}
