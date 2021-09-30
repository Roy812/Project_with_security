package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.exception.BadRequestException;
import nl.novi.stuivenberg.springboot.example.security.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;

    @Autowired
    public void LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void addLesson(Lesson lesson) {
        try {
            lessonRepository.save(lesson);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void uploadClassVideo(long lessonId, MultipartFile file) {
        try {
            Optional<Lesson> lesson = lessonRepository.findById(lessonId);
            lesson.get().setClassVideo(file.getBytes());
            lessonRepository.save(lesson.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] getClassVideo(long lessonId) {
        try {
            Optional<Lesson> lesson = lessonRepository.findById(lessonId);
            return lesson.get().getClassVideo();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void uploadClassGuide(long lessonId, MultipartFile file) {
        try {
            Optional<Lesson> lesson = lessonRepository.findById(lessonId);
            lesson.get().setLessonGuide(file.getBytes());
            lessonRepository.save(lesson.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] downloadClassGuide(long lessonId) {
        try {
            Optional<Lesson> lesson = lessonRepository.findById(lessonId);
            return lesson.get().getLessonGuide();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
