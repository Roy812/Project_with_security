package springboot.with.security.service;

import springboot.with.security.domain.Review;

import java.util.List;

public interface ReviewService {
    void addReview(Review review, long userId);
    void deleteReview(long reviewId);
    void teacherReply(long reviewId, String reply);
    List<Review> getReviewByTitle(String title);
}
