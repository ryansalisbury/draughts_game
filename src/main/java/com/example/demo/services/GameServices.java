package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Game;
import com.example.demo.models.Move;
import com.example.demo.models.Player;
import com.example.demo.models.Coordinate;

import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.PlayerRepository;




@Service
public class GameServices {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    // CREATE
    public void createGames() {
        System.out.println("Data creation started...");

        // Create players
        Player player1 = new Player("Player1", "player1@email.com", 100, false,"Test");
        Player player2 = new Player("Player2", "player2@email.com", 150, false,"Test");

        Game game1 = new Game(player1, player2);
        // Create moves
        Coordinate start1 = new Coordinate(0, 0);
        Coordinate end1 = new Coordinate(1, 1);
        Move move1 = new Move(start1, end1);

        Coordinate start2 = new Coordinate(2, 2);
        Coordinate end2 = new Coordinate(3, 3);
        Move move2 = new Move(start2, end2);

        // Create games
        
        //game1.setPlayers(List.of(player1, player2));
        game1.setMoves(List.of(move1, move2));

        gameRepository.save(game1);

        System.out.println("Data creation complete...");
    }
    public Game createNewGame(String player1Username, String player2Username) {

        // check if player 1 exists
        Player player1 = playerRepository.findByUsername(player1Username);
        if (player1 == null) {
            throw new IllegalArgumentException("Player with username " + player1Username + " does not exist");
        }
    
        // check if player 2 exists
        Player player2 = playerRepository.findByUsername(player2Username);
        if (player2 == null) {
            throw new IllegalArgumentException("Player with username " + player2Username + " does not exist");
        }
        System.out.println("New Game being created");
    
        Game game = new Game(player1, player2); // assuming an 8x8 board
        return gameRepository.save(game);
        
    }

    // Show all the games
    public void showAllGames() {
        gameRepository.findAll().forEach(game -> System.out.println(getGameDetails(game)));
    }

    // Get game by ID
    public void getGameById(String id) {
        System.out.println("Getting game by ID: " + id);
        Game game = gameRepository.findById(id).orElse(null);
        System.out.println(getGameDetails(game));
    }

    // Get games by player name
    public void getGamesByPlayerUsername(String playerName) {
        System.out.println("Getting games for player: " + playerName);
        List<Game> games = gameRepository.findGamesByPlayerUsername(playerName);
        games.forEach(game -> System.out.println(getGameDetails(game)));
    }

    // Get count of games in the collection
    public void findCountOfGames() {
        long count = gameRepository.count();
        System.out.println("Number of games in the collection: " + count);
    }

    private String getGameDetails(Game game) {
        if (game == null) {
            return "Game not found";
        }
        
        // Here, you would add logic to format the game details as a string
        return "Game details: " + game.toString();
    }

}
