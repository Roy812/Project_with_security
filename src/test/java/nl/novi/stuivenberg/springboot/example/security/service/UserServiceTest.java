package com.customuserdetailsservice.demo.service;

import com.customuserdetailsservice.demo.Exception.BadRequestException;
import com.customuserdetailsservice.demo.model.User;
import com.customuserdetailsservice.demo.repository.UserRepository;
import com.customuserdetailsservice.demo.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void changePasswordSuccess() {
        String newPassword = "newPassword";
        String password = "password";
        long id = 1;

        User user = new User();
        user.setPassword(password);
        user.setUserId(id);

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
        userService.changePassword(newPassword, id);
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(newPassword, userCaptor.getValue().getPassword());
    }

    @Test
    public void changePasswordException() {
        String newPassword = "password";
        String password = "password";
        long id = 1;

        User user = new User();
        user.setPassword(password);
        user.setUserId(id);

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

        Assertions.assertThrows(BadRequestException.class, () -> userService.changePassword(newPassword, id));
    }

    @Test
    public void addUserSuccess() {
        //ARRANGE
        User user = new User();
        user.setUsername("Username");
        user.setPassword("Password");

        //ACT
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("Encoded");
        userService.addUser(user);

        //ASSERT
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(userCaptor.getValue(), user);
    }

    @Test
    public void addUserException() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        when(userRepository.findAll()).thenReturn(List.of(user));
//        userService.addUser(user);

        Assertions.assertThrows(BadRequestException.class, () -> userService.addUser(user));

    }





}
