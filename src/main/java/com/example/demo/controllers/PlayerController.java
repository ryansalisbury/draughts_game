package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.services.PlayerServices;


@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServices playerServices;

    //Create test player data
    @PostMapping
    public void createPlayer() {
        playerServices.createPlayer();
    }

    @GetMapping("/show_player_score")
    public void showPlayerScore(@RequestParam String username) {
        playerServices.showPlayerScore(username);
    }
    
}
