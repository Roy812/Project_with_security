package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.service.ReviewServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class ReviewController {

    private ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
    this.reviewService = reviewService;
}

    //Functies voor de gebruiker met de rol 'USER':

    //Maak een Review over een Lesson.
    @PostMapping(value = "/review/add/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> addReview(@PathVariable("id") long userId, @RequestBody Review review) {
        reviewService.addReview(review, userId);
        return ResponseEntity.ok("Review added");
    }

    //Vind alle instanties van Review in de database op basis van de zoekcriteria 'title'.
    @GetMapping(value = "/review/findby/title/{title}")
    @PreAuthorize("hasRole('USER')")
    public List<Review> getReviewByTitle(@PathVariable ("title") String title) {
        return reviewService.getReviewByTitle(title);
    }

    //Functies voor de gebruiker met de rol 'ADMIN':

    //Verwijderen van een Review over een Lesson.
    @DeleteMapping(value = "/review/delete/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted with reviewId: " + reviewId);
    }

    //Functies voor de gebruiker met de rol 'MODERATOR'.

    //Voeg een 'reply' toe aan een instantie van een Review die al bestaat in de database.
    @PatchMapping(value = "/review/reply/id/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Object> replyToReview(@PathVariable ("id") long reviewId, @RequestBody String reply) {
        reviewService.teacherReply(reviewId, reply);
        return ResponseEntity.ok("Reply added to review with Id: " + reviewId);
    }
}
