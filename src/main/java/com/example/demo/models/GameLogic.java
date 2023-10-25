package com.example.demo.models;



public class GameLogic {
    
    public GameLogic() {
    }


     //Update game state
    public boolean makeMove(Game game, Player player, Coordinate from, Coordinate to) {

        if(player.equals(null)){
            System.out.println("Make move failed as player = null;");
            return false;
        }
        //Gets the board from the game parameter
        Board board = game.getBoard();
        //Gets the SelectedPiece from the board using the Coordinate parameters
        Piece selectedPiece = board.getPieceAtCoordinate(from); //gets piece from coordinate on the board

        //Validates the move from the player
        if (!validateMove(player, board, from, to)) {
            System.out.println("Invalid Move!");
            return false; // Invalid move
        }
        //Checks if the Move is a "Take" e.g. is diaganol and is 2 x 2 square move
        if(isTakeMove(from, to)){
            if(checkTakePiece(player, board,  from, to, selectedPiece)){
                //set the position of the moving players piece
                //remove other players piece from the game
                int midX = (from.getX() + to.getX()) / 2;
                int midY = (from.getY() + to.getY()) / 2;
                //set the taken space && piece to null and false
                Coordinate oppostionSpace  = new Coordinate(midX, midY);
                Space takenSpace = board.getSpaceAtCoordinate(oppostionSpace);
                //Get the opposition Player
                String OppositionPlayerUsername = takenSpace.getPiece().getPlayerUsername();
                Player oppositionPlayer = game.getPlayerByUsername(OppositionPlayerUsername);
                //remove the piece from the arraylist of pieces
                oppositionPlayer.getPieces().remove(board.getPieceAtCoordinate(oppostionSpace));
                //set space of taken piece to false
                takenSpace.setOccupied(false);
                //set piece variable of that space to null
                takenSpace.setPiece(null);

                //set position of selected piece
                selectedPiece.setPosition(to);
                System.out.println("Test print - inside the if(isTakeMove) and if(checkTakePiece)");

                return true;

            }
            else{
                System.out.println("checkTakePiece returned false");

                return false;
            }
        }
        //regular validated move
        else{


        /*Need to update 3 things everytime a piece is moved:
         * 1) Update the Players.piece array
         * 2) Update the board.pieces array
         * 3) Update board.spaces too
         */
        int pieceNumber = selectedPiece.getPieceNumber();
        System.out.println("Selected Pieces array, before modification: "+ player.getPieces());
        // 1) Update the Players.piece array
        player.getPieces().remove(selectedPiece);
        System.out.println("Selected piece prior to modifying coordinates: .toString: " + selectedPiece.toString()+", selectedPiece.getPosition(): "+selectedPiece.getPosition()+", selectedPiece.getPlayerUsername(): "+selectedPiece.getPlayerUsername());
        selectedPiece.setPosition(to);
        player.getPieces().add(selectedPiece);
        System.out.println("Selected piece after to modifying coordinates (.setPosition(to)): .toString: " + selectedPiece.toString()+", selectedPiece.getPosition(): "+selectedPiece.getPosition()+", selectedPiece.getPlayerUsername(): "+selectedPiece.getPlayerUsername());
        player.setPieces(null);


        // 2) Update the board.pieces array
        board.getPlayerPieces(player).remove(selectedPiece);
        board.getPlayerPieces(player).add(selectedPiece);

        // 3) Update board.spaces too
        board.getSpaceAtCoordinate(from).setOccupied(false);
        board.getSpaceAtCoordinate(from).setPiece(null);
        board.getSpaceAtCoordinate(to).setOccupied(true);
        board.getSpaceAtCoordinate(to).setPiece(selectedPiece);

        //Switch turns in game
        switchTurn(game);

        return true;

        }

    }

    //Handle Player Inputs
    public boolean validateMove(Player player, Board board, Coordinate from, Coordinate to) {
        if(board.getPieceAtCoordinate(from) != null){
            Piece playersPiece = board.getPieceAtCoordinate(from);

            if(playersPiece.getPlayerUsername().equals(player.getUsername())){
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




    public boolean checkTakePiece(Player player, Board board, Coordinate from, Coordinate to, Piece selectedPiece) {
        Space destinationSpace = board.getSpaceAtCoordinate(to);
    
        // Check if destination space is unoccupied and the move is a valid taking move
        return !destinationSpace.getOccupied() && isValidTakingMove(board, from, to, selectedPiece);
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
    
        // Check if the move is within the board parameters
        if (toX < 0 || toY < 0 || toX > 7 || toY > 7) {
            return false;
        }
    
        int deltaX = Math.abs(to.getX() - from.getX());
        int deltaY = Math.abs(to.getY() - from.getY());
    
        // Check if the move is diagonal
        if (deltaX != deltaY) {
            return false;
        }
    
        // Check if the move is a 'take' move or a regular diagonal move
        return deltaX == 2 || deltaX == 1;
    }
    
    public boolean isValidTakingMove(Board board, Coordinate from, Coordinate to, Piece selectedPiece) {
        return isValidMove(from, to) && isTakeMove(from, to);
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
