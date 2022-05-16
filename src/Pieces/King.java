package Pieces;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ChessBoard.Square;
import Players.Side;

public class King extends Piece {

	public King(Square square, Side side, int value, String img_file){
		super(square, side, value, img_file);
	}
	
	@Override
    public String toString(){
        return side == Side.WHITE ? "K" : "k";
    }

    @Override
    public List<Square> computeLegalMoves(){
        List<Square> legalMoves = new ArrayList<Square>();
        
        Square targetSquare = square.getAdjacentSquare(0, 1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(0, -1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(1, 1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(1, -1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(1, 0);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-1, 1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-1, -1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-1, 0);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        
        return legalMoves;
    }

}
