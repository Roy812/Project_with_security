package springboot.with.security.service;

import springboot.with.security.domain.Agenda;
import springboot.with.security.domain.Lesson;
import springboot.with.security.domain.User;
import springboot.with.security.exception.BadRequestException;
import springboot.with.security.exception.RecordNotFoundException;
import springboot.with.security.repository.AgendaRepository;
import springboot.with.security.repository.LessonRepository;
import springboot.with.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    private AgendaRepository agendaRepository;
    private UserRepository userRepository;
    private LessonRepository lessonRepository;

    @Autowired
    public AgendaServiceImpl(AgendaRepository agendaRepository, UserRepository userRepository, LessonRepository lessonRepository) {
        this.agendaRepository = agendaRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void bookClass(Long userId, Long lessonId, String title) {
        try {
            Agenda agenda = new Agenda();
            agenda.setTitle(title);
            Optional<User> user = userRepository.findById(userId);
            agenda.setUser(user.get());
            Optional<Lesson> lesson = lessonRepository.findById(lessonId);
            agenda.setLesson(lesson.get());
            agendaRepository.save(agenda);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteBooking(long agendaId) {
        try {
            Optional<Agenda> agenda = agendaRepository.findById(agendaId);
            agendaRepository.delete(agenda.get());
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Agenda> getAllBookings() {
        try {
            List<Agenda> list = agendaRepository.findAll();
            return list;
        }  catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }
}
