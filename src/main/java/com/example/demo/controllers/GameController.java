package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.models.Game;
import com.example.demo.services.GameServices;
import com.example.demo.dto.GameRequest;
import com.example.demo.dto.MoveRequest;

@RestController
@RequestMapping("/games/api")
public class GameController {

    @Autowired
    private GameServices gameServices;


    // @PostMapping("/create_new_game")
    // public ResponseEntity<Game> createNewGame(@RequestParam String player1Username, @RequestParam String player2Username) {
    //     try {
    //         Game newGame = gameServices.createNewGame(player1Username, player2Username);
    //         return ResponseEntity.ok(newGame);
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    @PostMapping("/create_new_game")
    public ResponseEntity<Game> createNewGame(@RequestBody GameRequest gameRequest){
        try{
            
            Game newGame = gameServices.createNewGame(gameRequest.getPlayer1Username(), gameRequest.getPlayer2Username());
            return ResponseEntity.ok(newGame);

        }
        catch (IllegalArgumentException e){
            System.out.println("Build failed");;
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping("/{gameId}/move")
    public ResponseEntity<String> makeMove(
            @PathVariable String gameId,
            @RequestParam String playerUsername,
            @RequestBody MoveRequest moveRequest) {
        try {
            gameServices.makeMove(gameId, playerUsername, moveRequest.getFrom(), moveRequest.getTo());
            return ResponseEntity.ok("Move successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

