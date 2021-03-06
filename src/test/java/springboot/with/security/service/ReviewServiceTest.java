package springboot.with.security.service;

import springboot.with.security.domain.Review;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Captor
    ArgumentCaptor<Review> reviewCaptor;

    @Test
    public void replyFromTeacherSuccess() {
        //ARRANGE:
        Review review = new Review();
        long reviewId = 1;
        String teacherReply = "This is a reply";

        //ACT:
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        reviewService.teacherReply(reviewId, teacherReply);

        //ASSERT || VERIFY:
        verify(reviewRepository).save(reviewCaptor.capture());
        Assertions.assertEquals(reviewCaptor.getValue().getTeacherReply(), teacherReply);
    }

    @Test
    public void replyFromTeacherException() {
        //ARRANGE
        String teacherReply = "reply";
        long reviewId = 1;

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> reviewService.teacherReply(reviewId, teacherReply));
    }

    @Test
    public void deleteReviewSuccess() {
        //ARRANGE
        long reviewId = 1;
        Review review = new Review();

        //ACT
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        reviewService.deleteReview(reviewId);

        //ASSERT
        verify(reviewRepository).delete(reviewCaptor.capture());
        Assertions.assertEquals(reviewCaptor.getValue(), review);
    }

    @Test
    public void deleteReviewThrowsException() {
        //ARRANGE
        long reviewId = 1;

        //ASSERT
        Assertions.assertThrows(RecordNotFoundException.class, () -> reviewService.deleteReview(reviewId));
    }
}
