package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;

public interface ReviewService {
    void addReview(Review review, long userId);
    void deleteReview(long reviewId);
    void teacherReply(long reviewId, String reply);
    Review getReviewByTitle(String title);
}
