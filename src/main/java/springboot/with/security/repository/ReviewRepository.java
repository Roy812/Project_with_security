package springboot.with.security.repository;

import springboot.with.security.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTitle(String title);
}
