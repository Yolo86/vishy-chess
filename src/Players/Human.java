package Players;

import java.util.List;

import ChessBoard.Board;
import ChessBoard.Move;
import Pieces.Piece;

public class Human extends Player {

	public Human(Board board, Side side) {
		super(board, side);
	}

	@Override
	public Move decideMove() {
		return null;
	}

	@Override
	public boolean isHuman() {
		return true;
	}

}
