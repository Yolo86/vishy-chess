package Players;

import java.util.List;
import java.util.Random;

import ChessBoard.Board;
import ChessBoard.Move;
import Pieces.Piece;

public class RandomPlayer extends Player {
	
	private Random random;

	public RandomPlayer(Board board, Side side) {
		super(board, side);
		random = new Random();
	}

	@Override
	public Move decideMove() {
		List<Move> moves =  computeAllLegalMoves();
		return moves.get(random.nextInt(moves.size()));
	}

	@Override
	public boolean isHuman() {
		return false;
	}

}
