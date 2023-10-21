package com.example.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Board {
    @Id
    private String id;

    private List<List<Space>> spaces; // List of spaces on the board that ill tell us what piece and player is occupying that space or not
    private List<Piece> player1Pieces; // List of playter 1 pieces
    private List<Piece> player2Pieces; //List of player 2 pieces
    private Player player1;
    private Player player2;


    //initialise board, with spaces:

    public Board(Player player1, Player player2, int numRows, int numCols) {
        this.player1 = player1;
        this.player2 = player2;
        this.spaces = initializeSpaces(numRows, numCols);
        this.player1Pieces = initialisePlayer1Pieces(player1, numRows, numCols); 
        this.player2Pieces = initialisePlayer2Pieces(player2, numRows, numCols);
    }
    
    //Method to initialise the playoing board
    private List<List<Space>> initializeSpaces(int numRows, int numCols) {
        System.out.println("In space initialiser: Board.java:");
        List<List<Space>> spaces = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<Space> spaceRow = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                Coordinate coordinate = new Coordinate(row, col);
                Space space = new Space(coordinate, null);
                spaceRow.add(space);
            }
            spaces.add(spaceRow);
        }
        return spaces;
    }
    //Method to initlise Player 1's pieces
    private List<Piece> initialisePlayer1Pieces(Player player, int numRows, int numCols){
        List<Piece> player1Pieces = new ArrayList<>();
        int pieceCounter = 0;
        System.out.println("In player1pieces initialiser: Board.java");

        //For loop to loop through the board
        for(int i = 0; i < numRows; i++){
            for(int j =0; j < numCols; j++){
                int x = i + j;
                if(x%2 == 0){
                    Coordinate coordinate = new Coordinate(i, j);
                    Piece piece = new Piece(pieceCounter, coordinate, true, player.getUsername());  // Assuming you have a constructor that takes these parameters
                    // Update the space to be occupied and assign the piece to that coordinate
                    spaces.get(i).get(j).setOccupied(true);
                    spaces.get(i).get(j).setPiece(piece);
                    //add piece to araylist
                    player1Pieces.add(piece);
                    pieceCounter++;
                }
               else{
                    spaces.get(i).get(j).setOccupied(false); //TEST THIS
                }
            }
        }
        return player1Pieces;
    }
    //Method to initilise player2's pieces
    private List<Piece> initialisePlayer2Pieces(Player player, int numRows, int numCols){
        System.out.println("In player2pieces initialiser: Board.java");
        List<Piece> player2Pieces = new ArrayList<>();
        int pieceCounter = 0;

        //For loop to loop through the board
        for(int i = numRows -1; i >= 0; i--){
            for(int j =numCols -1; j >= 0; j--){
                int x = i + j;
                if(x%2 == 1){
                    Coordinate coordinate = new Coordinate(i, j);
                    Piece piece = new Piece(pieceCounter, coordinate, true, player.getUsername());  // Assuming you have a constructor that takes these parameters
                    System.out.println(piece);
                    
                    // Update the space to be occupied and assign the piece to that coordinate
                    spaces.get(i).get(j).setOccupied(true);
                    spaces.get(i).get(j).setPiece(piece);
                    System.out.println(spaces.get(i).get(j)); // Add this line
                    player2Pieces.add(piece);
                    pieceCounter++;

                }
                else{
                    spaces.get(i).get(j).setOccupied(false); //TEST THIS
                }
            }
        }
        return player2Pieces;
    }

    public String getId() {
        return id;
    }

    ////////////////////////////////////////////////////////////////
    // Getters and setters...
    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<Piece> getPlayer1Pieces() {
        return player1Pieces;
    }

    public void setPlayer1Pieces(List<Piece> player1Pieces) {
        this.player1Pieces = player1Pieces;
    }

    public List<Piece> getPlayer2Pieces() {
        return player2Pieces;
    }

    public void setPlayer2Pieces(List<Piece> player2Pieces) {
        this.player2Pieces = player2Pieces;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}


