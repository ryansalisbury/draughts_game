package com.example.demo.models;
//package import for this needs to go here e.g. package com.example.....
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;


@Document
public class Game {
    @Id
    private String gameId;
    private Player player1;
    private Player player2;
    private List<Move> moves;
    private Board board;
    private String status;
    private Player currentPlayer;
    private GameLogic gameLogic;


    // Constructors, getters, and setters
    public Game(Player player1, Player player2, Player currentPlayer) {
        System.out.println("In game Constructor: Game.java");
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(player1, player2,8, 8 );  // Initialize the board with the two players
        this.status = "ONGOING";  // Initial status of the game
        this.currentPlayer = player1;
        this.gameLogic = new GameLogic(this);
    }

    public String getGameId(){
        return gameId;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Move> getMoves(){
        return moves;
    }

    public void setMoves(List<Move> moves){
        this.moves = moves;
        
    }
    public Player getPlayer(Player player){
        return player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    
    public GameLogic getGameLogic() {
        return gameLogic;
    }

}
