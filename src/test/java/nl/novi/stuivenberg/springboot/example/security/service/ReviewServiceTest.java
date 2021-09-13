package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.exception.BadRequestException;
import nl.novi.stuivenberg.springboot.example.security.repository.ReviewRepository;
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
public class ReviewServiceForTeacherTest {

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
        Review review = new Review();
        String teacherReply = "reply";
        long reviewId = 1;

//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
//        when(reviewRepository.save(review)).thenThrow(BadRequestException.class);

        Assertions.assertThrows(BadRequestException.class, () -> reviewService.teacherReply(reviewId, teacherReply));
    }




}
