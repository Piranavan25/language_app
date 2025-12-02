package com.example.languageapp.controller;

import com.example.languageapp.model.Speech;
import com.example.languageapp.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speeches")
public class SpeechController {

    @Autowired
    private SpeechRepository speechRepository;

    // GET all speeches
    @GetMapping
    public List<Speech> getAllSpeeches() {
        return speechRepository.findAll();
    }

    // GET speech by ID
    @GetMapping("/{id}")
    public ResponseEntity<Speech> getSpeechById(@PathVariable Long id) {
        return speechRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new speech
    @PostMapping
    public Speech createSpeech(@RequestBody Speech speech) {
        return speechRepository.save(speech);
    }

    // PUT update speech
    @PutMapping("/{id}")
    public ResponseEntity<Speech> updateSpeech(@PathVariable Long id, @RequestBody Speech speechDetails) {
        return speechRepository.findById(id).map(speech -> {
            speech.setDurationSeconds(speechDetails.getDurationSeconds());
            speech.setMediaUrl(speechDetails.getMediaUrl());
            speech.setPublic(speechDetails.isPublic());   // fixed
            speech.setTopic(speechDetails.getTopic());
            speech.setUserId(speechDetails.getUserId());  // fixed
            Speech updatedSpeech = speechRepository.save(speech);
            return ResponseEntity.ok(updatedSpeech);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE speech
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeech(@PathVariable Long id) {
        return speechRepository.findById(id).map(speech -> {
            speechRepository.delete(speech);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
