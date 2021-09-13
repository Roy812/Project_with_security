package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;

public interface AgendaService {
    void bookClass(Long userId, Long lessonId);
    void deleteBooking(long agendaId);
//    Agenda getAgendaByUserId(long userId);
}
