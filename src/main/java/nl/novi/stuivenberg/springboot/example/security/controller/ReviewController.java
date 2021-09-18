package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.dto.findReviewDTO;
import nl.novi.stuivenberg.springboot.example.security.service.ReviewServicePreAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class ReviewController {

    private ReviewServicePreAuth reviewServicePreAuth;

    public ReviewController(ReviewServicePreAuth reviewServicePreAuth) {
        this.reviewServicePreAuth = reviewServicePreAuth;
    }

    //FUNCTIONS FOR USER
    @PostMapping(value = "/review/add/id/{id}")
    public ResponseEntity<Object> addReview(@PathVariable("id") long userId, @RequestBody Review review) {
        reviewServicePreAuth.addReview(review, userId);
        return ResponseEntity.ok("Review added");
    }

    //FUNCTIONS FOR ADMIN
    @DeleteMapping(value = "/review/delete/id/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") long reviewId) {
        reviewServicePreAuth.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted with reviewId: " + reviewId);
    }

    //FUNCTIONS FOR MODERATOR
    @PatchMapping(value = "/review/reply/id/{id}")
    public ResponseEntity<Object> replyToReview(@PathVariable ("id") long reviewId, @RequestBody String reply) {
        reviewServicePreAuth.teacherReply(reviewId, reply);
        return ResponseEntity.ok("Reply added to review with Id: " + reviewId);
    }

    //FUNCTIONS FOR ALL
    @GetMapping(value = "review/findby/title")
    public Review getReviewByTitle(findReviewDTO dto) {
        return reviewServicePreAuth.getReviewByTitle(dto.title);
    }



}
