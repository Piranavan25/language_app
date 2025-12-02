package com.example.languageapp.service;

import com.example.languageapp.model.Feedback;
import com.example.languageapp.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Optional<Feedback> getFeedbackBySpeech(Long speechId) {
        return feedbackRepository.findBySpeechId(speechId);
    }
}
