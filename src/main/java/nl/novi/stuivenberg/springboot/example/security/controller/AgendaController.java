package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.dto.addBookingDTO;
import nl.novi.stuivenberg.springboot.example.security.service.AgendaServicePreAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("agenda")
public class AgendaController {

    private AgendaServicePreAuth agendaService;

    public AgendaController(AgendaServicePreAuth agendaService) {
        this.agendaService = agendaService;
    }

    //FUNCTIONS FOR USER:
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addAgenda(@RequestBody addBookingDTO dto) {
        agendaService.addAgenda(dto.userId, dto.lessonId);
        return ResponseEntity.ok("Agenda added");
    }

    @GetMapping(value = "/find/all")
    public List<Agenda> getAllBookings() {
        List<Agenda> list = agendaService.getAllBookings();
        return list;
    }

    //FUNCTIONS FOR ADMIN:
    @DeleteMapping(value = "delete/id/{id}")
    public ResponseEntity<Object> deleteAgenda(@PathVariable("id") long agendaId) {
        agendaService.deleteAgenda(agendaId);
        return ResponseEntity.ok("Agenda deleted");
    }

}
