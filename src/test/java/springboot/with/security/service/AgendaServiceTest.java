package springboot.with.security.service;

import springboot.with.security.domain.Agenda;
import springboot.with.security.domain.Lesson;
import springboot.with.security.domain.User;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.repository.AgendaRepository;
import springboot.with.security.repository.LessonRepository;
import springboot.with.security.repository.UserRepository;
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
public class AgendaServiceTest {

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    LessonRepository lessonRepository;

    @InjectMocks
    private AgendaServiceImpl agendaService;

    @Captor
    ArgumentCaptor<Agenda> agendaCaptor;

    @Test
    public void bookClassSuccess() {
        //ARRANGE
        User user = new User();
        user.setId(1);

        Lesson lesson = new Lesson();
        lesson.setId(1);

        Agenda agenda = new Agenda();
        String title = "title";
        agenda.setUser(user);
        agenda.setLesson(lesson);

        //ACT
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        agendaService.bookClass(user.getId(), lesson.getId(), title);

        //ASSERT
        verify(agendaRepository).save(agendaCaptor.capture());
        Assertions.assertEquals(agendaCaptor.getValue().getUser().getId(), 1);
        Assertions.assertEquals(agendaCaptor.getValue().getLesson().getId(), 1);
    }

    @Test
    public void bookClassException() {
        //ARRANGE
        String title = "title";
        long userId = 1;
        long lessonId = 2;

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> agendaService.bookClass(userId, lessonId, title));
    }

    @Test
    public void deleteBookingSuccess() {
        //ARRANGE
        long agendaId = 1;
        Agenda agenda = new Agenda();

        //ACT
        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        agendaService.deleteBooking(agendaId);

        //ASSERT
        verify(agendaRepository).delete(agendaCaptor.capture());
        Assertions.assertEquals(agendaCaptor.getValue(), agenda);
    }

    @Test
    public void deleteBookingThrowsException() {
        //ARRANGE
        long agendaId = 1;

        //ASSERT
        Assertions.assertThrows(RecordNotFoundException.class, () -> agendaService.deleteBooking(agendaId));
    }
}
