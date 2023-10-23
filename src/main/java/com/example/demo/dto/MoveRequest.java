package com.example.demo.dto;
import com.example.demo.models.Coordinate;

public class MoveRequest {
    private Coordinate from;
    private Coordinate to;

    // getters and setters
    public Coordinate getFrom() {
        return from;
    }

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public Coordinate getTo() {
        return to;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }
}

