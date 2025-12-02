package com.example.languageapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private  String learningLanguage;
    private String level;
    private String interest;


    //constructor

    public User() {}

    // getters and setters
        public Long getId () {
            return id;
        }
        public void setId (Long id){
            this.id = id;
        }

        public String getName () {
            return name;
        }
        public void setName (String name){
            this.name = name;
        }

        public String getLearningLanguage () {
            return learningLanguage;
        }
        public void setLearningLanguage (String learningLanguage){
            this.learningLanguage = learningLanguage;
        }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }


}
