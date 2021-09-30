package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

//De klasse 'JpaRepository' is in staat om op basis van een geschreven methode een query te genereren richting de database.
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
