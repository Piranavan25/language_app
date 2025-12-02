package com.example.languageapp.repository;

import com.example.languageapp.model.Speech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeechRepository extends JpaRepository<Speech, Long> {

    // speeches by user
    List<Speech> findByUserId(Long userId);

    // public speeches (for feed)
    List<Speech> findByIsPublicTrue();
}