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
    private List<Player> players;
    private List<Move> moves;

    public Game() {
        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
    }

    public String getGameId(){
        return gameId;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void setPlayers(List<Player> players){
        this.players = players;
    }

    public List<Move> getMoves(){
        return moves;
    }

    public void setMoves(List<Move> moves){
        this.moves = moves;
        
    }

}
