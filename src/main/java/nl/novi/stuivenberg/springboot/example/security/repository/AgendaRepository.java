package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
//    Agenda findAgendaByUser_Id(long userId);
}
