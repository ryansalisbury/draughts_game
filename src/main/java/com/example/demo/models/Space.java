package com.example.demo.models;

public class Space {
    private Coordinate coordinate; //Will tell us the coordinates of that space on the board
    private Piece piece; //Will tell us what piece is occupied and which team that piece is on if occupied
    private boolean occupied;//Tells us if this space on the board is occupied

    public Space(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
        
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

