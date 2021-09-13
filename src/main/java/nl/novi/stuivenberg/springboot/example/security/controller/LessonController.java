package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.service.LessonServiceImpl;
import nl.novi.stuivenberg.springboot.example.security.service.LessonServicePreAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lesson")
public class LessonController {

    private LessonServicePreAuth lessonServicePreAuth;

    public LessonController(LessonServicePreAuth lessonServicePreAuth) {
        this.lessonServicePreAuth = lessonServicePreAuth;
    }

    //FUNCTIONS FOR USER:
    @GetMapping(value = "/download/video/id/{id}")
    public ResponseEntity<byte[]> downloadLessonVideo(@PathVariable ("id") long lessonId) {
        var downloadbytes = lessonServicePreAuth.getClassVideo(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    @GetMapping(value = "/download/guide/id/{id}")
    public ResponseEntity<byte[]> downloadLessonGuide(@PathVariable ("id") long lessonId) {
        var downloadbytes = lessonServicePreAuth.getLessonGuide(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    //FUNCTIONS FOR ADMIN:
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addLesson(@RequestBody Lesson lesson) {
        lessonServicePreAuth.addLesson(lesson);
        return ResponseEntity.ok("Lesson added");
    }

    @PatchMapping(value = "/upload/video/id/{id}")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
        lessonServicePreAuth.uploadClassVideo(lessonId, file);
        return ResponseEntity.ok("Video uploaded");
    }

    @PatchMapping(value = "/upload/guide/id/{id}")
    public ResponseEntity<Object> uploadLessonGuide(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
        lessonServicePreAuth.uploadClassGuide(lessonId, file);
        return ResponseEntity.ok("Guide uploaded");
    }

}
