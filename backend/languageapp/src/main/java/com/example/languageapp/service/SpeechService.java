package com.example.languageapp.service;

import com.example.languageapp.model.Speech;
import com.example.languageapp.repository.SpeechRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeechService {

    private final SpeechRepository speechRepository;

    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public Speech saveSpeech(Speech speech) {
        return speechRepository.save(speech);
    }

    public List<Speech> getPublicSpeeches() {
        return speechRepository.findByIsPublicTrue();
    }

    public List<Speech> getSpeechesByUser(Long userId) {
        return speechRepository.findByUserId(userId);
    }
}
