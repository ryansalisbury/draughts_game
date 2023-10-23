package com.example.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;


public class GameLogic {
    private Game game;

    public GameLogic(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }



    //Handle Player Inputs
    public boolean validateMove( Player player, Board board, Coordinate from, Coordinate to) {
        if(board.getPieceAtCoordinate(from) != null){
            Piece playersPiece = board.getPieceAtCoordinate(from);

            if(playersPiece.getPlayer().equals(player)){
                if(isValidMove(from, to)){
                //checks if space at desired destination is occupied by any other piece
            if(!board.getSpaceAtCoordinate(to).getOccupied()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false; // or false if the move is invalid
        }
        }
        else{
            System.out.println("This is not your piece!");
            return false;
        }
      
        }
        else{
            return false;
        }


        
    }


    //Update game state
    public boolean makeMove(Game game, Player player, Coordinate from, Coordinate to) {
        Board board = game.getBoard();
        Piece selectedPiece = board.getPieceAtCoordinate(from); //gets piece from coordinate on the board


        if (!validateMove(player, board, from, to)) {
            return false; // Invalid move
        }
        if(isTakeMove(from, to)){
            if(checkTakePiece(player, board,  from, to, selectedPiece)){
                //set the position of the moving players piece
                //remove other players piece from the game
                int midX = (from.getX() + to.getX()) / 2;
                int midY = (from.getY() + to.getY()) / 2;
                //set the taken space && piece to null and false
                Coordinate oppostionSpace  = new Coordinate(midX, midY);
                Space takenSpace = board.getSpaceAtCoordinate(oppostionSpace);
                Player oppositionPlayer = takenSpace.getPiece().getPlayer();
                //remove the piece from the arraylist of pieces
                oppositionPlayer.getPieces().remove(board.getPieceAtCoordinate(oppostionSpace));
                //set space of taken piece to false
                takenSpace.setOccupied(false);
                //set piece variable of that space to null
                takenSpace.setPiece(null);

                //set position of selected piece
                selectedPiece.setPosition(to);
                
                
                return true;

            }
            else{
                return false;
            }
        }
        //regular validated move
        else{
        // Update the position of the piece
        selectedPiece.setPosition(to); //Sets the position of selected piece to requested position
        board.getSpaceAtCoordinate(to).setOccupied(true);//Sets the space as occupied
        Piece oldPiece = board.getSpaceAtCoordinate(from).getPiece();
        oldPiece = null;
        checkTakePiece(player, board, from, to, selectedPiece);
        switchTurn(game);

        return true;

        }

    }

    public boolean checkTakePiece(Player player, Board board, Coordinate from, Coordinate to, Piece selectedPiece){
        Piece destinationPiece = board.getPieceAtCoordinate(to);

        // Check if destination coordinate is occupied by an opponent's piece
        if (destinationPiece != null && !destinationPiece.getPlayer().getId().equals(player.getId())) {
            // Check if the move from 'from' to 'to' is a valid taking move
            // This depends on the specific rules of your game
            if (isValidTakingMove(board, from, to, selectedPiece)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTakeMove(Coordinate from, Coordinate to) {
        int deltaX = Math.abs(to.getX() - from.getX());
        int deltaY = Math.abs(to.getY() - from.getY());
    
        // Check if the move is two spaces diagonally
        return deltaX == 2 && deltaY == 2;
    }

    public boolean isValidMove(Coordinate from, Coordinate to) {
        int toX = to.getX();
        int toY = to.getY();
        //checks the move is within the board parameters
        if((toX <= 0 || toY <= 0) || (toX > 7 || toY > 7)){
            return false;
        }
        //gets the added magnitude of each move
        int deltaX = Math.abs(to.getX() - from.getX());
        int deltaY = Math.abs(to.getY() - from.getY());

        // Check if the move is diagonal
        if (deltaX != deltaY) {
            return false; // Not a diagonal move
        }

        // Check if the move is a 'take' move
        if (deltaX == 2 && deltaY == 2) {
            return true; // Valid 'take' move
        }

        // Check if the move is a regular diagonal move
        if (deltaX == 1 && deltaY == 1) {
            return true; // Valid regular diagonal move
        }

        return false; // Not a valid move
    }


    public boolean isValidTakingMove(Board board, Coordinate from, Coordinate to, Piece selectedPiece){
        if(isValidMove(from, to)){
            if(isTakeMove(from, to) == true){
                //checks that the to coordinates are not occupied by any other piece
                if(board.getSpaceAtCoordinate(to).getOccupied() == false){
                    return true;
                }
                else{
                    return false;
                }
            }

        }
        //checks that the move is diaganol and is 2 squares
        return false;
    }

    public boolean takePiece(Board board, Coordinate from, Coordinate to) {
    // Find the midpoint coordinates
    int midX = (from.getX() + to.getX()) / 2;
    int midY = (from.getY() + to.getY()) / 2;

    // Get the space at the midpoint
    Space takenSpace = board.getSpaceAtCoordinate(new Coordinate(midX, midY));
    
    // Check if there is an opposition piece at the midpoint
    if (takenSpace != null && takenSpace.getPiece() != null && takenSpace.getOccupied()) {
        // Remove the taken piece
        takenSpace.setPiece(null);
        takenSpace.setOccupied(false);
        return true; // Piece successfully taken
    }

    return false; // No piece taken
}


    //Check for Win/Lose Conditions
    public boolean checkWinCondition() {
        
        // Implement the logic to check if a player has won the game
        return false;
    }
    
    public boolean checkLoseCondition(Game game, Player player) {
        if(game.getPlayer(player).getPieces().size()==0){
            game.getPlayer(player).setWinnerStatus(false);
            game.setStatus(player.getUsername()+" LOSES!!");
            return true;
        }

        // Implement the logic to check if a player has lost the game
        return false;
    }

    //Turn-Based Gameplay


    public void switchTurn(Game game) {
        if (game.getCurrentPlayer().equals(game.getPlayer1())) {
            game.setCurrentPlayer(game.getPlayer2());
        } else {
            game.setCurrentPlayer(game.getPlayer1());
        }
    }
}
