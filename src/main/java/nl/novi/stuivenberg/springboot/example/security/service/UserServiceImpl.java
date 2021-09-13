package com.customuserdetailsservice.demo.service;

import com.customuserdetailsservice.demo.Exception.BadRequestException;
import com.customuserdetailsservice.demo.Exception.RecordNotFoundException;
import com.customuserdetailsservice.demo.model.Agenda;
import com.customuserdetailsservice.demo.model.Coins;
import com.customuserdetailsservice.demo.model.User;
import com.customuserdetailsservice.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void changePassword(String newPassword, long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.get().getPassword().equals(newPassword)) {
            throw new BadRequestException();
        } else {
            user.get().setPassword(newPassword);
            userRepository.save(user.get());
        }
    }

    @Override
    public User addUser(User user) {
        List<User> list = userRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            User user2 = list.get(i);
            if (user.getUsername().equals(user2.getUsername())) {
                throw new BadRequestException();
            }
            if (user.getPassword().equals(user2.getPassword())) {
                throw new BadRequestException();
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUser(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public void uploadPicture(long id, MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User user2 = user.get();
            user2.setProfilePicture(file.getBytes());
            userRepository.save(user2);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void subscribeToNewsletter(long userId) {
        Optional<User> user = userRepository.findById(userId);
        try {
            if  (!user.get().isSubscribeToNewsletter()) {
                user.get().setSubscribeToNewsletter(true);
            }
            if  (user.get().isSubscribeToNewsletter()) {
                user.get().setSubscribeToNewsletter(false);
            }
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] getProfilePicture(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getProfilePicture();
        } else {
            throw new RecordNotFoundException();
        }
    }


}
