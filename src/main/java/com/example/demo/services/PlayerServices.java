package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.models.Player;

import com.example.demo.repositories.PlayerRepository;

@Service
public class PlayerServices {

    @Autowired
    private PlayerRepository playerRepository;
    //Create Player method - Saves to DB
    public void createPlayer() {
        System.out.println("Example player data creation started...");

        // Create players
        Player examplePlayer1 = new Player("ExamplePlayer1", "exampleplayer1@email.com", 100, false, "Test");

        System.out.println("Data creation complete...");

        playerRepository.save(examplePlayer1);
    }

    public Player createNewPlayer(String username, String email, int score, boolean winner, String password){
        Player existingPlayerUsername = playerRepository.findByUsername(username);
        if (existingPlayerUsername != null) {
            
            throw new IllegalArgumentException("Player with username " + username + " already exists");
        }
        
        Player existingPlayerEmail = playerRepository.findByEmail(email);
        if (existingPlayerEmail != null) {
            System.out.println("Player with email already exists");
            throw new IllegalArgumentException("Player with email " + email + " already exists");
        }
        else{

            Player player = new Player(username, email, 0, false, password);
            return playerRepository.save(player);
        }

    }
    //show a player's score based on username search
    public int showPlayerScore(String username){
        System.out.println("Getting score for player: " + username);
        Player player = playerRepository.findByUsername(username);
        if(player == null){
            throw new IllegalArgumentException("Player not found");
        }
        else {
            System.out.println(username +"'s score is " + player.getScore());
            return player.getScore();
        }

    }
    //will delete a player if wanted
    public void deletePlayer(String username) {
        Player player = playerRepository.findByUsername(username);
        if (player != null) {
            playerRepository.delete(player);
        } else {
            System.out.println("Player not found");
        }
    }
}
