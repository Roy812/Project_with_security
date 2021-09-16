package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.exception.BadRequestException;
import nl.novi.stuivenberg.springboot.example.security.exception.RecordNotFoundException;
import nl.novi.stuivenberg.springboot.example.security.repository.AgendaRepository;
import nl.novi.stuivenberg.springboot.example.security.repository.LessonRepository;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;
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
        agenda.setUser(user);
        agenda.setLesson(lesson);

        //ACT
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        agendaService.bookClass(user.getId(), lesson.getId());

        //ASSERT
        verify(agendaRepository).save(agendaCaptor.capture());
        Assertions.assertEquals(agendaCaptor.getValue().getUser().getId(), 1);
        Assertions.assertEquals(agendaCaptor.getValue().getLesson().getId(), 1);
    }

    @Test
    public void bookClassException() {
        //ARRANGE
        long userId = 1;
        long lessonId = 2;

        //ASSERT
        Assertions.assertThrows(BadRequestException.class, () -> agendaService.bookClass(userId, lessonId));
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
