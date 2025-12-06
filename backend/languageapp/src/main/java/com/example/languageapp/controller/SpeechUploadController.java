package com.example.languageapp.controller;

import com.example.languageapp.model.Speech;
import com.example.languageapp.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/speech")
public class SpeechUploadController {

    @Value("${whisper.url}")
    private String whisperUrl;   // ex: https://xxxx.ngrok-free.dev/transcribe

    private final SpeechRepository speechRepository;

    public SpeechUploadController(SpeechRepository repo) {
        this.speechRepository = repo;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadSpeech(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("topic") String topic,
            @RequestParam("isPublic") boolean isPublic
    ) {
        try {
            // 1. Create RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // 2. Prepare multipart request for Whisper
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // convert MultipartFile → byte array resource
            ByteArrayResource audioResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            body.add("file", audioResource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> whisperRequest =
                    new HttpEntity<>(body, headers);

            // 3. Send audio to Whisper API
            ResponseEntity<String> whisperResponse = restTemplate.postForEntity(
                    whisperUrl,
                    whisperRequest,
                    String.class
            );

            String transcript = whisperResponse.getBody(); // JSON text

            // 4. Save speech record
            Speech speech = new Speech();
            speech.setUserId(userId);
            speech.setTopic(topic);
            speech.setPublic(isPublic);
            speech.setMediaUrl("local-file-storage/" + file.getOriginalFilename());
            speech.setDurationSeconds(180); // you can calculate later

            speechRepository.save(speech);

            // 5. Return transcript to app
            return ResponseEntity.ok(transcript);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
