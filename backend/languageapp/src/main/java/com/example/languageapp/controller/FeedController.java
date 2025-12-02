package com.example.languageapp.controller;

import com.example.languageapp.model.Feedback;
import com.example.languageapp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // GET all feedbacks
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // GET feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new feedback
    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // PUT update feedback
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedbackDetails) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setFeedbackText(feedbackDetails.getFeedbackText());
            feedback.setTranscript(feedbackDetails.getTranscript());
            feedback.setSpeechId(feedbackDetails.getSpeechId()); // corrected
            Feedback updatedFeedback = feedbackRepository.save(feedback);
            return ResponseEntity.ok(updatedFeedback);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedbackRepository.delete(feedback);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
