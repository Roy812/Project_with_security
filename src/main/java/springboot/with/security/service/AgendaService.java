package springboot.with.security.service;

import springboot.with.security.domain.Agenda;

import java.util.List;

public interface AgendaService {
    void bookClass(Long userId, Long lessonId, String title);
    void deleteBooking(long agendaId);
    List<Agenda> getAllBookings();
}
