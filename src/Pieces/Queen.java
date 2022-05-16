package Pieces;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ChessBoard.Square;
import Players.Side;

public class Queen extends Piece {

	public Queen(Square square, Side side, int value, String img_file){
		super(square, side, value, img_file);
	}
	
	@Override
    public String toString(){
        return side == Side.WHITE ? "Q" : "q";
    }

    @Override
    public List<Square> computeLegalMoves(){
    	List<Square> legalMoves = new ArrayList<Square>();
    	legalMoves.addAll(computeLinearMoves());
    	legalMoves.addAll(computeDiagonalMoves());
        return legalMoves;
    }

}
