package springboot.with.security.service;

import springboot.with.security.domain.Lesson;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    LessonRepository lessonRepository;

    @InjectMocks
    LessonServiceImpl lessonService;

    @Captor
    ArgumentCaptor<Lesson> lessonCaptor;

    @Test
    public void addLessonSuccess() {
        //ARRANGE
        Lesson lesson = new Lesson();

        //ACT
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        lessonService.addLesson(lesson);

        //ASSERT
        verify(lessonRepository).save(lessonCaptor.capture());
        Assertions.assertEquals(lessonCaptor.getValue(), lesson);
    }

    @Test
    public void addLessonThrowsException() {
        //ARRANGE
        Lesson lesson = new Lesson();

        //ACT
        when(lessonRepository.save(lesson)).thenThrow(BadRequestException.class);

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> lessonService.addLesson(lesson));
    }
}
