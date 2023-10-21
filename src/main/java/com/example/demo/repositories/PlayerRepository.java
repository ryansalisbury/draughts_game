package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//import models.Player;
import com.example.demo.models.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{
    Player findByUsername(String username);
    Player findByEmail(String email);

}
