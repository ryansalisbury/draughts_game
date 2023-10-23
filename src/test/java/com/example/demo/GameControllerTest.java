// package com.example.demo;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.boot.test.context.SpringBootTest;
// import com.example.demo.repositories.GameRepository;
// import com.example.demo.repositories.PlayerRepository;
// import com.example.demo.controllers.GameController;
// import com.example.demo.models.Coordinate;
// import com.example.demo.models.Game;
// import com.example.demo.models.Player;

// import static org.mockito.Mockito.*;

// @SpringBootTest
// public class GameControllerTest {

//     @Mock
//     private GameRepository gameRepository;

//     @Mock
//     private PlayerRepository playerRepository;

//     @InjectMocks
//     private GameController gameController;

//     @Test
//     public void testMakeMove() {
//         Player examplePlayer1 = new Player("Player1TestMakeMove", "exampleTestMakeMove1Email@email.com", 100, false, "Test");// Create a player entity for testing
//         Player examplePlayer2 = new Player("Player2TestMakeMove", "exampleTestMakeMove2Email@email.com", 100, false, "Test");// Create a player entity for testing

//         // Setup test data
//         String gameId = "1";
//         String playerUsername = "player1";
//         Coordinate from = new Coordinate(0, 0);
//         Coordinate to = new Coordinate(1, 1);

//         Game game = new Game(examplePlayer1, examplePlayer2, examplePlayer1);  // Create a game entity for testing
        

//         // Mock repository calls
//         when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
//         when(playerRepository.findByUsername(playerUsername)).thenReturn(player);

//         // Call the method to test
//         gameController.makeMove(gameId, playerUsername, from, to);

//         // Verify interactions with mocked objects
//         verify(gameRepository).findById(gameId);
//         verify(playerRepository).findByUsername(playerUsername);
//         verify(gameRepository).save(game);
//     }
// }
