package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Lesson;
import nl.novi.stuivenberg.springboot.example.security.service.LessonServiceImpl;
import nl.novi.stuivenberg.springboot.example.security.service.LessonServicePreAuth;
import org.springframework.beans.factory.annotation.Autowired;
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

   /* private LessonServicePreAuth lessonServicePreAuth;*/
    private LessonServiceImpl lessonService;

//    public LessonController(LessonServicePreAuth lessonServicePreAuth) {
//        this.lessonServicePreAuth = lessonServicePreAuth;
//    }
public LessonController(LessonServiceImpl lessonService) {
    this.lessonService = lessonService;
}

    //FUNCTIONS FOR USER:
    @GetMapping(value = "/download/video/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<byte[]> downloadLessonVideo(@PathVariable ("id") long lessonId) {
//        var downloadbytes = lessonServicePreAuth.getClassVideo(lessonId);
        var downloadbytes = lessonService.getClassVideo(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    @GetMapping(value = "/download/guide/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<byte[]> downloadLessonGuide(@PathVariable ("id") long lessonId) {
//        var downloadbytes = lessonServicePreAuth.getLessonGuide(lessonId);
        var downloadbytes = lessonService.downloadClassGuide(lessonId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"license.pdf\"").body(downloadbytes);
    }

    //FUNCTIONS FOR ADMIN:
    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addLesson(@RequestBody Lesson lesson) {
//        lessonServicePreAuth.addLesson(lesson);
        lessonService.addLesson(lesson);
        return ResponseEntity.ok("Lesson added");
    }

    @PatchMapping(value = "/upload/video/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
//        lessonServicePreAuth.uploadClassVideo(lessonId, file);
        lessonService.uploadClassVideo(lessonId, file);
        return ResponseEntity.ok("Video uploaded");
    }

    @PatchMapping(value = "/upload/guide/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> uploadLessonGuide(@PathVariable ("id") long lessonId, @RequestParam("file") MultipartFile file) throws IOException {
//        lessonServicePreAuth.uploadClassGuide(lessonId, file);
        lessonService.uploadClassGuide(lessonId, file);
        return ResponseEntity.ok("Guide uploaded");
    }

}
