package springboot.with.security.service;

import springboot.with.security.domain.User;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.exception.UserNotFoundException;
import springboot.with.security.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void findUserByIdSuccess() {
        //ARRANGE
        long userId = 1;

        //ACT
        User user = new User();
        user.setId(userId);

        //ASSERT
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User user2 = userService.getUserWithId(userId);
        Assertions.assertEquals(user, user2);
    }

    @Test
    public void findUserByIdException() {
        //ARRANGE
        long id = 1;

        //ASSERT
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserWithId(id));
    }

    @Test
    public void findUserByUsernameSuccess() {
        //ARRANGE
        String username = "username";

        //ACT
        Optional<User> user = Optional.of(new User());
        user.get().setUsername(username);

        //ASSERT
        when(userRepository.findByUsername(user.get().getUsername())).thenReturn(user);
        Optional<User> user2 = userService.findByUsername(username);
        Assertions.assertEquals(user, user2);
    }

    @Test
    public void findUserByUsernameException() {
        //ARRANGE
        String username = "username";

        //ASSERT
        Assertions.assertThrows(UserNotFoundException.class,() -> userService.findByUsername(username));
    }

    @Test
    public void findUserByPasswordSuccess() {
        //ARRANGE
        String password = "password";
        //ACT
        User user = new User();
        user.setPassword(password);

        //ASSERT
        when(userRepository.findByPassword(user.getPassword())).thenReturn(user);
        User user2 = userService.findByPassword(password);
        Assertions.assertEquals(user, user2);
    }

    @Test
    public void findUserByPasswordException() {
        //ARRANGE
        String password = "password";

        //ASSERT
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findByPassword(password));
    }

    @Test
    public void changePasswordSuccess() {
        //ARRANGE
        String newPassword = "newPassword";
        String password = "password";
        long id = 1;

        List<User> list = new ArrayList<>();
        Optional<User> user = Optional.of(new User());
        user.get().setPassword(password);
        user.get().setId(id);

        //ACT
        when(userRepository.findAll()).thenReturn(list);
        when(userRepository.findById(id)).thenReturn(user);
        when(passwordEncoder.encode(newPassword)).thenReturn(newPassword);
        userService.changePassword(newPassword, id);

        //ASSERT | VERIFY
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(newPassword, userCaptor.getValue().getPassword());
    }

    @Test
    public void changePasswordException() {
        //ARRANGE
        String newPassword = "password";
        long id = 1;

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> userService.changePassword(newPassword, id));
    }

    @Test
    public void deleteUserSuccess() {
        //ARRANGE
        long userId = 1;
        String username = "username";
        String password = "password";
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);

        //ACT
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        userService.removeUser(userId);

        //ASSERT
        verify(userRepository).delete(userCaptor.capture());
        Assertions.assertEquals(userCaptor.getValue(), user);
    }

    @Test
    public void deleteUserThrowsException() {
        //ARRANGE
        long userId = 1;

        //ASSERT
        Assertions.assertThrows(RecordNotFoundException.class, () -> userService.removeUser(userId));
    }

    @Test
    public void subtractCoinsFromUserSuccess() {
        //ARRANGE
        Optional<User> user = Optional.of(new User());
        user.get().setCoinBalance(1L);
        long userId = 1;

        //ACT
        when(userRepository.findById(userId)).thenReturn(Optional.of(user.get()));
        userService.subtractCoins(userId);

        //VERIFY || ASSERT
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(userCaptor.getValue().getCoinBalance(), 0);
    }

    @Test
    public void subtractCoinsFromUserThrowsException() {
        //ARRANGE
        long userId = 1;
        User user = new User();
        user.setCoinBalance(0L);

        //ACT
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> userService.subtractCoins(userId));
    }
}
