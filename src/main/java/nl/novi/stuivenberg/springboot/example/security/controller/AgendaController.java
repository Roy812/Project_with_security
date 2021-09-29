package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Agenda;
import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.domain.Review;
import nl.novi.stuivenberg.springboot.example.security.dto.addBookingDTO;
import nl.novi.stuivenberg.springboot.example.security.service.AgendaServiceImpl;
import nl.novi.stuivenberg.springboot.example.security.service.AgendaServicePreAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("agenda")
public class AgendaController {

//    private AgendaServicePreAuth agendaService;
    private AgendaServiceImpl agendaService;

//    public AgendaController(AgendaServicePreAuth agendaService) {
//        this.agendaService = agendaService;
//    }
    public AgendaController(AgendaServiceImpl agendaService) {
        this.agendaService = agendaService;
    }

    //FUNCTIONS FOR USER:
    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> addAgenda(@RequestBody addBookingDTO dto) {
        agendaService.bookClass(dto.userId, dto.lessonId, dto.title);
        return ResponseEntity.ok("Agenda added");
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("hasRole('USER')")
    public List<Agenda> getAllBookings() {
        List<Agenda> list = agendaService.getAllBookings();
        return list;
    }

    //FUNCTIONS FOR ADMIN:
    @DeleteMapping(value = "delete/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteAgenda(@PathVariable("id") long agendaId) {
        agendaService.deleteBooking(agendaId);
        return ResponseEntity.ok("Agenda deleted");
    }

}
