package Pieces;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ChessBoard.Square;
import Players.Side;

public class Knight extends Piece {

	public Knight(Square square, Side side, int value, String img_file){
		super(square, side, value, img_file);
	}
	
	@Override
    public String toString(){
        return side == Side.WHITE ? "N" : "n";
    }

    @Override
    public List<Square> computeLegalMoves(){
        List<Square> legalMoves = new ArrayList<Square>();
        
        Square targetSquare = square.getAdjacentSquare(2, 1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(2, -1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-2, 1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-2, -1);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-1, 2);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(-1, -2);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(1, -2);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        targetSquare = square.getAdjacentSquare(1, 2);
        if(targetSquare!=null && (targetSquare.getPiece()==null || targetSquare.getPiece().getSide()!=side))
        		legalMoves.add(targetSquare);
        
        return legalMoves;
    }
}
