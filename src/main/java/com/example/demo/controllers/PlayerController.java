package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.models.Player;


import com.example.demo.services.PlayerServices;





@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServices playerServices;



    //@RequestBody annotation is used to bind the JSON request body to the Player object
    @PostMapping("/create_new_player")
    public ResponseEntity<Player> createNewPlayer(@RequestBody Player player) {
        try {
            Player newPlayer = playerServices.createNewPlayer(player.getUsername(), player.getEmail(), player.getScore(), player.getWinnerStatus(), player.getPassword());
            return ResponseEntity.ok(newPlayer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    //Create test player data
    // @PostMapping("/create_test_player")
    // public void createPlayer() {
    //     playerServices.createPlayer();
    // }

    @GetMapping("/show_player_score")
    public ResponseEntity<String> showPlayerScore(@RequestParam String username) {
        try {
            int score = playerServices.showPlayerScore(username);
            return ResponseEntity.ok("Player's score: " + score);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
