package com.customuserdetailsservice.demo.repository;

import com.customuserdetailsservice.demo.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
//    List<Agenda> findAgendaByUserId(long userId);
}
