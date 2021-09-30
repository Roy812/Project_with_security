package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByPassword (String password);
    Boolean existsByUsername(String username);
}
