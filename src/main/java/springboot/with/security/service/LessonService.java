package springboot.with.security.service;

import springboot.with.security.domain.Lesson;
import org.springframework.web.multipart.MultipartFile;

public interface LessonService {
    void addLesson(Lesson lesson);
    void uploadClassVideo(long lessonId, MultipartFile file);
    byte[] getClassVideo(long lessonId);
    void uploadClassGuide(long lessonId, MultipartFile file);
    byte[] downloadClassGuide(long lessonId);
}
