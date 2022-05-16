package Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ChessBoard.Board;
import ChessBoard.Move;
import ChessBoard.Square;
import Pieces.Piece;
import ThreadedModel.ThreadBoard;
import ThreadedModel.ThreadedMove;

public class AlphaBeta extends Player {
	
	private int maxDepth = 4;
	private float sequentialPercent = (float) 0.0;
	
	public AlphaBeta(Board board, Side side) {
		super(board, side);
	}
	
	class Sortbyrating implements Comparator<Move> {
        public int compare(Move a, Move b){
            return b.rating - a.rating;
        }
    }
	
	@Override
	public Move decideMove() {
		long start = System.currentTimeMillis();		
//		Move move = iterativeDeepening();		
//		Move move = minimax();
		Move move = threadedMinimax();
		long end = System.currentTimeMillis();	
		System.out.println((float)(end-start)/1000.0 + "s taken");
		return move;
	}
	
	private Move minimax() {
		int mx = Integer.MIN_VALUE;
		List<Move> moves = computeAllLegalMoves();
		Move bestMove = moves.get(0);
				
		for(Move move:moves) {
			Piece pi = move.getInitialSquare().getPiece();
			board.makeMove(pi, move);
			side = side.opposite();
				
			int value = minimizer(maxDepth-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
				
			board.undoMove(pi, move);
			side = side.opposite();
			
			if(value>mx) {
				bestMove = move;
				mx = value;
			}
			System.out.println("Move: " + move + ", value: "+ value);
		}
		
		return bestMove;
	}
	
	private Move threadedMinimax() {
		int mx = Integer.MIN_VALUE;		
		int mn = Integer.MAX_VALUE;	
		ThreadBoard tBoard = new ThreadBoard(board,null,0,0,0);
		tBoard.setBoard();
//		System.out.println("ChessBoard:");
//		tBoard.displayPieces();
		
		tBoard.flipBoard();
		
//		System.out.println("Flipped ChessBoard:");
//    	tBoard.displayPieces();
		
		
		List<ThreadedMove> moves = tBoard.computeAllLegalMoves();
//		tBoard.flipBoard();
		ThreadedMove bestMove = moves.get(0);
//		for(int i=0;i<sequentialPercent*moves.size();i++) {
//			System.out.println("yo");
//			ThreadedMove move=moves.get(i);
//			tBoard.makeMove(move);
////			System.out.println("Moveeeee: " + move);
////			tBoard.displayPieces();
//			tBoard.flipBoard();
////			System.out.println("Flipppp");
////			tBoard.displayPieces();
//			int value = tBoard.minimizer(maxDepth-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
//			
//			tBoard.flipBoard();
//			tBoard.undoMove(move);
////			System.out.println("Undo");
////			tBoard.displayPieces();
//			if(value>mx) {
//				bestMove = move;
//				mx = value;
//			}
//			mn = Integer.min(mn, value);
////			System.out.println(move + ", value: " + value);
//		}
		
		List<Thread> arr = new ArrayList<Thread>();
		List<Runnable> vv = new ArrayList<Runnable>();
		
		for(int i=(int) (sequentialPercent*moves.size()),j=0;i<moves.size();i++,j++) {
			System.out.println("i: "+i);
			ThreadedMove move=moves.get(i);
			Runnable r = new ThreadBoard(board,move,maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
//			((ThreadBoard) r).flipBoard();
//			((ThreadBoard) r).makeMove(move);
//			((ThreadBoard) r).flipBoard();
			
			vv.add(r);
			arr.add(new Thread(r));
			arr.get(j).start();
		}
		
		for(int i=(int) (sequentialPercent*moves.size()),j=0;i<moves.size();i++,j++) {
			System.out.println("i: "+i);
			ThreadedMove move=moves.get(i);
			try {
				arr.get(j).join();
				int value = ((ThreadBoard) vv.get(j)).getValue();
				if(value>mx) {
					bestMove = move;
					mx = value;
				}
//				System.out.println(move + ", value: " + value);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		int a = 7-bestMove.getInitialRow(),b = 7-bestMove.getInitialCol();
		int c = 7-bestMove.getTargetRow(),d = 7-bestMove.getTargetCol();
		Square sq1 = board.getSquare(a, b);
		Square sq2 = board.getSquare(c, d);
		Move bMove = new Move(sq1, sq2, sq2.getPiece());
//		System.out.println("Best move: " + bMove);
		return bMove;
	}

	private int maximizer(int depth, int alpha, int beta) {
		
		if(depth == 0)
			return board.computeRating();
		
		int mx = Integer.MIN_VALUE;
		List<Move> moves =  computeAllLegalMoves();
		
		for(Move move:moves) {
			Piece pi = move.getInitialSquare().getPiece();
			board.makeMove(pi, move);
			side = side.opposite();
			
			int value = minimizer(depth-1, alpha, beta);
			
			board.undoMove(pi, move);
			side = side.opposite();
			mx = Integer.max(mx, value);
			alpha = Integer.max(alpha, value);
			if(beta <= alpha)
				return mx;
		}
		
		return mx;
	}

	private int minimizer(int depth, int alpha, int beta) {
		
		if(depth == 0)
			return board.computeRating();
		
		int mn = Integer.MAX_VALUE;
		List<Move> moves =  computeAllLegalMoves();
		
		for(Move move:moves) {
			Piece pi = move.getInitialSquare().getPiece();
			board.makeMove(pi, move);
			side = side.opposite();
			
			int value = maximizer(depth-1, alpha, beta);
			
			board.undoMove(pi, move);
			side = side.opposite();
			mn = Integer.min(mn, value);
			beta = Integer.min(beta,value);
			if(beta <= alpha)
				return mn;	
		}
		
		return mn;
	}
	
	

	
	private Move iterativeDeepening() {
		
		int mx = Integer.MIN_VALUE;
		List<Move> moves = new ArrayList<Move>();
		moves =  computeAllLegalMoves();
		
		for(int dep = 1; dep <= maxDepth; dep++) {
			
			
			for(Move move:moves) {
				Piece pi = move.getInitialSquare().getPiece();
				board.makeMove(pi, move);
				side = side.opposite();
				
				int value = minimizer(dep-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
				
				board.undoMove(pi, move);
				side = side.opposite();
				move.rating = value;
			}
			
			for(Move move:moves) {
				move.setTargetPiece(move.getTargetSquare().getPiece());
			}
			
			Collections.sort(moves,new Sortbyrating());
		}
		
		
		return moves.get(0);
	}

	@Override
	public boolean isHuman() {
		return false;
	}

}
