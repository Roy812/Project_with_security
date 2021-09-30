package springboot.with.security.service;

import springboot.with.security.domain.Review;
import springboot.with.security.domain.User;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.repository.ReviewRepository;
import springboot.with.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;

    @Autowired
    public void ReviewServiceForUserImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addReview(Review review, long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            review.setUser(user.get());
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteReview(long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void teacherReply(long reviewId, String reply) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        try {
            review.get().setTeacherReply(reply);
            reviewRepository.save(review.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<Review> getReviewByTitle(String title) {
        List<Review> reviews = reviewRepository.findByTitle(title);
        if(reviews != null) {
            return reviews;
        }
        throw new BadRequestException();
    }
}
