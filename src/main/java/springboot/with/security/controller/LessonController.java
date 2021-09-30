package springboot.with.security.controller;

import springboot.with.security.domain.Lesson;
import springboot.with.security.service.LessonServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lesson")
public class LessonController {

    private LessonServiceImpl lessonService;

    public LessonController(LessonServiceImpl lessonService) {
    this.lessonService = lessonService;
}

    //Functies voor alle gebruikers:

    //Download een video bestand vanaf een Lesson instantie in de database.
    @GetMapping(value = "/download/video/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<byte[]> downloadLessonVideo(@PathVariable ("id") long lessonId) {
        var downloadbytes = lessonService.getClassVideo(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    //Download een pdf bestand vanaf een Lesson instantie in de database.
    @GetMapping(value = "/download/guide/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<byte[]> downloadLessonGuide(@PathVariable ("id") long lessonId) {
        var downloadbytes = lessonService.downloadClassGuide(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    //Functies van de gebruiker met de rol 'ADMIN':

    //Toevoegen van een Lesson.
    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addLesson(@RequestBody Lesson lesson) {
        lessonService.addLesson(lesson);
        return ResponseEntity.ok("Lesson added");
    }

    //Üploaden van een video bestand bij één instantie van een Lesson.
    @PatchMapping(value = "/upload/video/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> uploadClassVideo(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
        lessonService.uploadClassVideo(lessonId, file);
        return ResponseEntity.ok("Video uploaded");
    }

    //Üploaden van een pdf bestand bij één instantie van een Lesson.
    @PatchMapping(value = "/upload/guide/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> uploadLessonGuide(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
        lessonService.uploadClassGuide(lessonId, file);
        return ResponseEntity.ok("Guide uploaded");
    }
}
