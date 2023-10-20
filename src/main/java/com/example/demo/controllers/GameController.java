package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.GameServices;
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameServices gameServices;

    @PostMapping
    public void createGames() {
        gameServices.createGames();
    }
    //This works
    @GetMapping("/show_games")
    public void showAllGames() {
        gameServices.showAllGames();
    }
    //This works
    @GetMapping("/{id}")
    public void getGameById(@PathVariable String id) {
        gameServices.getGameById(id);
    }

    //This works
    @GetMapping("/player")
    public void getGamesByPlayerUsername(@RequestParam String username) {
        gameServices.getGamesByPlayerUsername(username);
    }
    //This works
    @GetMapping("/count")
    public void findCountOfGames() {
        gameServices.findCountOfGames();
    }
}
