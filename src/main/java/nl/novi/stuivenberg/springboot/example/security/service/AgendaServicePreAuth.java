package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AgendaServicePreAuth {

    private AgendaServiceImpl agendaService;

    public AgendaServicePreAuth(AgendaServiceImpl agendaService) {
        this.agendaService = agendaService;
    }

    //FUNCTIONS FOR USER:
    @PreAuthorize("hasRole('USER')")
    public String addAgenda(Long userId, Long lessonId) {
        agendaService.bookClass(userId, lessonId);
        return "Agenda added with " + lessonId + " To user with Id: " + userId;
    }

//    @PreAuthorize("hasRole('USER')")
//    public Agenda getAgendaByUserId(Long userId) {
//        Agenda agenda = agendaService.getAgendaByUserId(userId);
//        return agenda;
//    }

    //FUNCTIONS FOR ADMIN:
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAgenda(long agendaId) {
        agendaService.deleteBooking(agendaId);
        return "Agenda deleted";
    }

}
