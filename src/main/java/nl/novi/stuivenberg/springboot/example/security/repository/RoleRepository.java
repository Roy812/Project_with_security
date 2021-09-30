package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.ERole;
import nl.novi.stuivenberg.springboot.example.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
