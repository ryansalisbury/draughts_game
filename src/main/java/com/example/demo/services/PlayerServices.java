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
public class PlayerServices {

    @Autowired
    private PlayerRepository playerRepository;
    //Create Player method - Saves to DB
    public void createPlayer() {
        System.out.println("Example player data creation started...");

        // Create players
        Player examplePlayer1 = new Player("ExamplePlayer1", "exampleplayer1@email.com", 100, false);

        System.out.println("Data creation complete...");

        playerRepository.save(examplePlayer1);
    }
    //show a player's score based on username search
    public void showPlayerScore(String username){
        System.out.println("Getting score for player: " + username);
        Player player = playerRepository.findByUsername(username);
        if(player == null){
            System.out.println("Player not found");
        }
        else {
            System.out.println(username +"'s score is " + player.getScore());

        }

    }
}
