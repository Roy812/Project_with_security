package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.dto.findReviewDTO;
import nl.novi.stuivenberg.springboot.example.security.service.ReviewServiceImpl;
import nl.novi.stuivenberg.springboot.example.security.service.ReviewServicePreAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class ReviewController {

//    private ReviewServicePreAuth reviewServicePreAuth;
private ReviewServiceImpl reviewService;

//    public ReviewController(ReviewServicePreAuth reviewServicePreAuth) {
//        this.reviewServicePreAuth = reviewServicePreAuth;
//    }
public ReviewController(ReviewServiceImpl reviewService) {
    this.reviewService = reviewService;
}

    //FUNCTIONS FOR USER
    @PostMapping(value = "/review/add/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> addReview(@PathVariable("id") long userId, @RequestBody Review review) {
//        reviewServicePreAuth.addReview(review, userId);
        reviewService.addReview(review, userId);
        return ResponseEntity.ok("Review added");
    }

    //FUNCTIONS FOR ADMIN
    @DeleteMapping(value = "/review/delete/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") long reviewId) {
//        reviewServicePreAuth.deleteReview(reviewId);
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted with reviewId: " + reviewId);
    }

    //FUNCTIONS FOR MODERATOR
    @PatchMapping(value = "/review/reply/id/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Object> replyToReview(@PathVariable ("id") long reviewId, @RequestBody String reply) {
//        reviewServicePreAuth.teacherReply(reviewId, reply);
        reviewService.teacherReply(reviewId, reply);
        return ResponseEntity.ok("Reply added to review with Id: " + reviewId);
    }

    //FUNCTIONS FOR ALL
    @GetMapping(value = "/review/findby/title/{title}")
    @PreAuthorize("hasRole('USER')")
    public List<Review> getReviewByTitle(@PathVariable ("title") String title) {
//        return reviewServicePreAuth.getReviewByTitle(title);
        return reviewService.getReviewByTitle(title);
    }



}
