package com.customuserdetailsservice.demo.repository;

import com.customuserdetailsservice.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
