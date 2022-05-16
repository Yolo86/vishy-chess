package Pieces;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import ChessBoard.Square;
import Players.Side;

public class WhitePawn extends Piece {

	public WhitePawn(Square square, Side side, int value, String img_file){
		super(square, side, value, img_file);
	}
	
	@Override
    public String toString(){
        return "P";
    }
	
	@Override
	public List<Square> computeLegalMoves() {
		
		List<Square> legalMoves = new ArrayList<Square>();
		Square targetSquare = square.getAdjacentSquare(0, -1);

        if(targetSquare != null && targetSquare.getPiece() == null){
            legalMoves.add(targetSquare);
            if(!hasMoved()) {
            	targetSquare = square.getAdjacentSquare(0, -2);
            	if(targetSquare != null && targetSquare.getPiece() == null){
                    legalMoves.add(targetSquare);
                }
            }
        }
             
        targetSquare = square.getAdjacentSquare(-1, -1);

        if (targetSquare!=null && targetSquare.getPiece()!=null && targetSquare.getPiece().side!=side){
            legalMoves.add(targetSquare);
        }

        targetSquare = square.getAdjacentSquare(1, -1);

        if (targetSquare!=null && targetSquare.getPiece()!=null && targetSquare.getPiece().side!=side){
            legalMoves.add(targetSquare);
        }
		
		
		return legalMoves;
	}

}
