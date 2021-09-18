package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ReviewServicePreAuth {

    private ReviewServiceImpl reviewService;

    public ReviewServicePreAuth(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    //FUNCTIONS FOR USER
    @PreAuthorize("hasRole('USER')")
    public String addReview(Review review, long userId) {
        reviewService.addReview(review, userId);
        return "Review added";
    }

    //FUNCTIONS FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteReview(long reviewId) {
        reviewService.deleteReview(reviewId);
        return "Review deleted";
    }

    //FUNCTIONS MODERATOR
    @PreAuthorize("hasRole('MODERATOR')")
    public String teacherReply(long reviewId, String reply) {
        reviewService.teacherReply(reviewId, reply);
        return "Reply added to Review with reviewId " + reviewId;
    }

    //FUNCTIONS FOR ALL
    @PreAuthorize("hasAnyRole('USER' or 'ADMIN' or 'MODERATOR')")
    public Review getReviewByTitle(String title) {
        return reviewService.getReviewByTitle(title);
    }

}
