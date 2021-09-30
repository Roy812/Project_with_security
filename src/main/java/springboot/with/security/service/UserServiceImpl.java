package springboot.with.security.service;

import springboot.with.security.domain.User;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.exception.UserNotFoundException;
import springboot.with.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            Optional<User> user = userRepository.findById(id);
            List<User> userList = userRepository.findAll();

            for (int i = 0; i < userList.size(); i++) {
                User user2 = userList.get(i);
                if (user2.getPassword().equals(newPassword)) {
                    throw new BadRequestException();
                }
            }
            String finalPassword = passwordEncoder.encode(newPassword);
            user.get().setPassword(finalPassword);
            userRepository.save(user.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void subscribeToNewsletter(long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if  (!user.get().getSubscribeToNewsletter()) {
                user.get().setSubscribeToNewsletter(true);

            } else {
                user.get().setSubscribeToNewsletter(false);
            }
            userRepository.save(user.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void uploadPicture(long id, MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setProfilePicture(file.getBytes());
            userRepository.save(user.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public byte[] getUserProfilePicture(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getProfilePicture();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateCoinBalance(long userId, Long amount) {
        Optional<User> user = userRepository.findById(userId);
        Long coinBalance = user.get().getCoinBalance();
        Long coinBalanceNew = coinBalance + amount;
        try {
            user.get().setCoinBalance(coinBalanceNew);
            userRepository.save(user.get());
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void subtractCoins(long userId) {
           Optional<User> user = userRepository.findById(userId);
           Long coinBalance = user.get().getCoinBalance();
           if (coinBalance >= 1) {
               user.get().setCoinBalance(coinBalance -1);
               userRepository.save(user.get());
           } else {
               throw new BadRequestException();
           }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException("User not found..");
        }
    }

    @Override
    public User findByPassword(String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByPassword(password));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found..");
        }
    }

    @Override
    public User getUserWithId(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found..");
        }
    }

    @Override
    public void removeUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            userRepository.delete(user.get());
        }
    }
}
