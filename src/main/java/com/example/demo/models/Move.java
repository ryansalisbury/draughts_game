package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Move {
    private Coordinate startPosition;
    private Coordinate endPosition;
    // other move attributes

    public Move(Coordinate startPosition, Coordinate endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        // initialize other attributes
    }

    public Coordinate getStartPosition(){
        return startPosition;
    }

    public void setStartPosition(Coordinate startPosition){
        this.startPosition = startPosition;
    }

    public Coordinate getEndPosition(){
        return endPosition;
    }

    public void setEndPosition(Coordinate endPosition){
        this.endPosition = endPosition;
    }

    // getters and setters
}



