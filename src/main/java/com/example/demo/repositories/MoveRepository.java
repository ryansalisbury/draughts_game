package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//import models.Move;
import com.example.demo.models.Move;

@Repository
public interface MoveRepository extends MongoRepository<Move, String>{
    
}
