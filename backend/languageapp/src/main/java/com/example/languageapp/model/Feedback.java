package com.example.languageapp.model;
import jakarta.persistence.*;
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long speechId;

    @Column(length = 10000)
    private String transcript;

    @Column(length = 10000)
    private String feedbackText;

    public Feedback() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSpeechId() { return speechId; }
    public void setSpeechId(Long speechId) { this.speechId = speechId; }

    public String getTranscript() { return transcript; }
    public void setTranscript(String transcript) { this.transcript = transcript; }

    public String getFeedbackText() { return feedbackText; }
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}
