package com.customuserdetailsservice.demo.repository;

import com.customuserdetailsservice.demo.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
