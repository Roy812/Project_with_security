package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTitle(String title);
}
