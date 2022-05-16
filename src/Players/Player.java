package Players;

import java.util.ArrayList;
import java.util.List;

import ChessBoard.Board;
import ChessBoard.Move;
import ChessBoard.Square;
import Pieces.Piece;

public abstract class Player {
	protected Board board;
	protected Side side;
	
	public Player(Board board,Side side) {
		this.board = board;
		this.side = side;
	}
	
	public abstract Move decideMove();
	public abstract boolean isHuman();
	
	public Side getSide() {
		return side;
	}
	
	public List<Move> computeAllLegalMoves(){
		List<Piece> allPieces = new ArrayList<Piece>();
		List<Move> allLegalMoves = new ArrayList<Move>();
		
		for(Piece p:board.getPieces(side))
			allPieces.add(p);
		
		for(Piece p:allPieces) {
			List<Square> allMoves = p.computeLegalMoves();
			for(Square sq:allMoves) {
				Move move = new Move(p.getSquare(),sq,
					sq.getPiece());
				if(board.isValid(p, move))
					allLegalMoves.add(move);
			}
		}
		return allLegalMoves;
	}

}
