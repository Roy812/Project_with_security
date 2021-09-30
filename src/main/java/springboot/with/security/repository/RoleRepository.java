package springboot.with.security.repository;

import springboot.with.security.domain.ERole;
import springboot.with.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
