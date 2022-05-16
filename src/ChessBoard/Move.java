package ChessBoard;

import java.util.Comparator;

import Pieces.Piece;

public class Move {
	private Square initialSquare;
    private Square targetSquare;
    private Piece targetPiece;
    public int rating;

    public Move(Square initialSquare, Square targetSquare, Piece targetPiece){
        this.initialSquare = initialSquare;
        this.targetSquare = targetSquare;
        this.targetPiece = targetPiece;
        rating = 0;
    }
    
    
    public Square getInitialSquare(){
        return initialSquare;
    }

    public void setInitialSquare(Square initialSquare){
        this.initialSquare = initialSquare;
    }

    public Square getTargetSquare(){
        return targetSquare;
    }

    public void setTargetSquare(Square targetSquare){
        this.targetSquare = targetSquare;
    }

    public Piece getTargetPiece(){
        return targetPiece;
    }

    public void setTargetPiece(Piece targetPiece){
        this.targetPiece = targetPiece;
    }

    @Override
    public String toString(){
        return initialSquare.getPiece() + ": " + initialSquare + " -> " + targetSquare + ": " + targetPiece;
    }
}
