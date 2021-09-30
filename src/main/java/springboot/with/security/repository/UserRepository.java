package springboot.with.security.repository;

import springboot.with.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByPassword (String password);
    Boolean existsByUsername(String username);
}
