package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaServicePreAuth {

//    private AgendaServiceImpl agendaService;
//
//    public AgendaServicePreAuth(AgendaServiceImpl agendaService) {
//        this.agendaService = agendaService;
//    }
//
//    //FUNCTIONS FOR USER:
//    @PreAuthorize("hasRole('USER')")
//    public String addAgenda(Long userId, Long lessonId, String title) {
//        agendaService.bookClass(userId, lessonId, title);
//        return "Agenda added with " + lessonId + " To user with Id: " + userId;
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    public List<Agenda> getAllBookings() {
//        List<Agenda> list = agendaService.getAllBookings();
//        return list;
//    }
//
//    //FUNCTIONS FOR ADMIN:
//    @PreAuthorize("hasRole('ADMIN')")
//    public String deleteAgenda(long agendaId) {
//        agendaService.deleteBooking(agendaId);
//        return "Agenda deleted";
//    }

}
