package springboot.with.security.controller;

import springboot.with.security.domain.Agenda;
import springboot.with.security.dto.addBookingDTO;
import springboot.with.security.service.AgendaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("agenda")
public class AgendaController {

    private AgendaServiceImpl agendaService;

    public AgendaController(AgendaServiceImpl agendaService) {
        this.agendaService = agendaService;
    }

    //Functies voor de gebruiker met de rol 'USER':

    //Toevoegen van een Agenda.
    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> addAgenda(@RequestBody addBookingDTO dto) {
        agendaService.bookClass(dto.userId, dto.lessonId, dto.title);
        return ResponseEntity.ok("Agenda added");
    }

    //Alle instanties van Agenda opzoeken.
    @GetMapping(value = "/find/all")
    @PreAuthorize("hasRole('USER')")
    public List<Agenda> getAllBookings() {
        List<Agenda> list = agendaService.getAllBookings();
        return list;
    }

    //Functies voor de gebruiker met de rol 'ADMIN':

    //Verwijderen van een Agenda.
    @DeleteMapping(value = "delete/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteAgenda(@PathVariable("id") long agendaId) {
        agendaService.deleteBooking(agendaId);
        return ResponseEntity.ok("Agenda deleted");
    }
}
