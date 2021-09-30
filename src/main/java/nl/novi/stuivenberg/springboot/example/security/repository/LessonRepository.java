package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
