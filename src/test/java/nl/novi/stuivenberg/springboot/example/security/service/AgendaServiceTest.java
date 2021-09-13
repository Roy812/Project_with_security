package com.customuserdetailsservice.demo.service;

import com.customuserdetailsservice.demo.Exception.BadRequestException;
import com.customuserdetailsservice.demo.Exception.RecordNotFoundException;
import com.customuserdetailsservice.demo.Exception.UserNotFoundException;
import com.customuserdetailsservice.demo.model.Agenda;
import com.customuserdetailsservice.demo.model.Lesson;
import com.customuserdetailsservice.demo.model.User;
import com.customuserdetailsservice.demo.repository.AgendaRepository;
import com.customuserdetailsservice.demo.repository.LessonRepository;
import com.customuserdetailsservice.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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
        long userId = 1;
        User user = new User();
        user.setUserId(1);
        user.setUsername("user");
        user.setPassword("password");

        Lesson lesson = new Lesson();
        lesson.setId(1);

        Agenda agenda = new Agenda();
        agenda.setLesson(lesson);

        //ACT
        when(agendaRepository.save(agenda)).thenReturn(agenda);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        agendaService.bookClass(agenda, userId, lesson.getId());

        //ASSERT
        verify(agendaRepository).save(agendaCaptor.capture());
        Assertions.assertEquals(agendaCaptor.getValue(), agenda);
    }

    @Test
    public void bookClassException() {
        long userId = 1;
        long lessonId = 2;
        Agenda agenda = new Agenda();

        Assertions.assertThrows(BadRequestException.class, () -> agendaService.bookClass(agenda, userId, lessonId));
    }

//    @Test
//    public void findUserBookingsWithUserIdSuccess() {
//        //ARRANGE
//        long userId1 = 1;
//        User user = new User();
//        user.setUsername("Nick");
//        user.setPassword("Novi");
//        Agenda agenda = new Agenda();
//        agenda.setUser(user);
//
//        //ACT
//        when(agendaRepository.findAgendaByUserId(agenda.getUser().getUserId())).thenReturn(List.of(agenda));
//        List<Agenda> list = agendaService.getUserBookingsWithUserId(agenda.getUser().getUserId());
//
//
//        //ASSERT
//        Assertions.assertEquals(list.get(0), agenda);
//    }
//
//    @Test
//    public void findUserBookingsWithUserIdException() {
//        long userId = 1;
//        Agenda agenda = new Agenda();
//
////        when(agendaRepository.findAgendaByUserId(userId)).thenReturn(List.of(agenda));
//        when(agendaRepository.findAgendaByUserId(userId)).thenThrow(RecordNotFoundException.class);
//
////        Assertions.assertThrows(RecordNotFoundException.class, () -> agendaService.getUserBookingsWithUserId(userId));
//    }



}
