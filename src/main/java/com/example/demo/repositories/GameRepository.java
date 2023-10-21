package com.example.demo.repositories;

//import models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.demo.models.Game;


@Repository
public interface GameRepository extends MongoRepository<Game, String>{
    //Should find the games by players usernames
    @Query("{ 'players.username' : ?0 }")
    List<Game> findGamesByPlayerUsername(String username);
    

}