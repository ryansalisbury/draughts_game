package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Player {
    @Id
    private String id;
    private String username;
    private String email;
    private int score;
    private Boolean winner;

    public Player(String username, String email, int score, Boolean winner) {
        this.username = username;
        this.email = email;
        this.score = score;
        this.winner = winner;
    }

    public Boolean getWinnerStatus(){
        return winner;
    }

    public void setWinnerStatus(Boolean winner){
        this.winner = winner;
    }

    // Getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
    this.score = score;
    }
}