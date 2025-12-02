package com.example.languageapp.controller;

import com.example.languageapp.model.Topic;
import com.example.languageapp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    // GET all topics
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    // GET topic by ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return topicRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new topic
    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    // PUT update topic
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
        return topicRepository.findById(id).map(topic -> {
            topic.setTitle(topicDetails.getTitle());
            Topic updatedTopic = topicRepository.save(topic);
            return ResponseEntity.ok(updatedTopic);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE topic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        return topicRepository.findById(id).map(topic -> {
            topicRepository.delete(topic);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
