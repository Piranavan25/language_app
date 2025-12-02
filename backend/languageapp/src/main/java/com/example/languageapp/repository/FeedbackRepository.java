package com.example.languageapp.repository;

import com.example.languageapp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Optional<Feedback> findBySpeechId(Long speechId);
}
