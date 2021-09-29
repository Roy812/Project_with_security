package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LessonServicePreAuth {

//    private LessonServiceImpl lessonService;
//
//    public LessonServicePreAuth(LessonServiceImpl lessonService) {
//        this.lessonService = lessonService;
//    }

    //FUNCTIONS USER:
//    @PreAuthorize("hasRole('USER')")
//    public byte[] getClassVideo(long lessonId) {
//        return lessonService.getClassVideo(lessonId);
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    public byte[] getLessonGuide(long lessonId) {
//        return lessonService.downloadClassGuide(lessonId);
//    }

//    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
//    public byte[] getClassVideo(long lessonId) {
//        return lessonService.getClassVideo(lessonId);
//    }
//
//    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
//    public byte[] getLessonGuide(long lessonId) {
//        return lessonService.downloadClassGuide(lessonId);
//    }
//
//    //FUNCTIONS ADMIN:
//    @PreAuthorize("hasRole('ADMIN')")
//    public String addLesson(Lesson lesson) {
//        lessonService.addLesson(lesson);
//        return "Lesson added";
//    }

//    @PreAuthorize("hasRole('ADMIN')")
//    public String uploadClassVideo(long lessonId, MultipartFile file) {
//        lessonService.uploadClassVideo(lessonId, file);
//        return "Video uploaded";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    public String uploadClassGuide(long lessonId, MultipartFile file) {
//        lessonService.uploadClassGuide(lessonId, file);
//        return "Guide uploaded";
//    }

}
