package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import ChessBoard.Board;
import ChessBoard.Move;
import ChessBoard.Square;
import Pieces.AllPieces;
import Pieces.Bishop;
import Pieces.BlackPawn;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.WhitePawn;
import Players.AlphaBeta;
import Players.Human;
import Players.Player;
import Players.RandomPlayer;
import Players.Side;

public class Arena extends JPanel implements MouseListener {
	
	private static final String wPawn = "whitePawn.png";	
	private static final String wRook = "whiteRook.png";
	private static final String wBishop = "whiteBishop.png";
	private static final String wKnight = "whiteKnight.png";
	private static final String wKing = "whiteKing.png";
	private static final String wQueen = "whiteQueen.png";
	
	private static final String bPawn = "blackPawn.png";
	private static final String bRook = "blackRook.png";
	private static final String bBishop = "blackBishop.png";
	private static final String bKnight = "blackKnight.png";
	private static final String bKing = "blackKing.png";
	private static final String bQueen = "blackQueen.png";
	
	private final Board board;
	private final GameWindow window;
	private Player whitePlayer;
	private AlphaBeta blackPlayer;
	
	private int turn;
    private Piece cur_piece;
    private int cur_x;
    private int cur_y;
    private boolean isOver;
    
    public Arena(GameWindow window) {
        this.window = window;
        board = new Board();
        setLayout(new GridLayout(8, 8, 0, 0));
        
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
//        		System.out.println(i+" "+j+" "+board.getSquare(i, j).getColor());
        		this.add(board.getSquare(i, j));
        	}
        }
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(560, 560));
        this.setMaximumSize(new Dimension(560, 560));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(560, 560));
        initializePieces();
        
        whitePlayer = new Human(this.board,Side.WHITE);
        blackPlayer = new AlphaBeta(this.board,Side.BLACK);

        turn = 1;
        isOver = false;
//        whitePlayerMove();
    }
    
    private void initializePieces() {
    	
    	for(int i=0;i<8;i++) {
    		board.getSquare(6, i).setPiece(new WhitePawn(board.getSquare(6, i),Side.WHITE,100,wPawn));
    	}
    	board.getSquare(7, 0).setPiece(new Rook(board.getSquare(7, 0),Side.WHITE,500,wRook));
    	board.getSquare(7, 7).setPiece(new Rook(board.getSquare(7, 7),Side.WHITE,500,wRook));
    	board.getSquare(7, 1).setPiece(new Knight(board.getSquare(7, 1),Side.WHITE,320,wKnight));
    	board.getSquare(7, 6).setPiece(new Knight(board.getSquare(7, 6),Side.WHITE,320,wKnight));
    	board.getSquare(7, 2).setPiece(new Bishop(board.getSquare(7, 2),Side.WHITE,330,wBishop));
    	board.getSquare(7, 5).setPiece(new Bishop(board.getSquare(7, 5),Side.WHITE,330,wBishop));
    	board.getSquare(7, 3).setPiece(new Queen(board.getSquare(7, 3),Side.WHITE,900,wQueen));
    	board.getSquare(7, 4).setPiece(new King(board.getSquare(7, 4),Side.WHITE,20000,wKing));
    	
    	for(int i=0;i<8;i++) {
    		board.getSquare(1, i).setPiece(new BlackPawn(board.getSquare(1, i),Side.BLACK,100,bPawn));
    	}
    	board.getSquare(0, 0).setPiece(new Rook(board.getSquare(0, 0),Side.BLACK,500,bRook));
    	board.getSquare(0, 7).setPiece(new Rook(board.getSquare(0, 7),Side.BLACK,500,bRook));
    	board.getSquare(0, 1).setPiece(new Knight(board.getSquare(0, 1),Side.BLACK,320,bKnight));
    	board.getSquare(0, 6).setPiece(new Knight(board.getSquare(0, 6),Side.BLACK,320,bKnight));
    	board.getSquare(0, 2).setPiece(new Bishop(board.getSquare(0, 2),Side.BLACK,330,bBishop));
    	board.getSquare(0, 5).setPiece(new Bishop(board.getSquare(0, 5),Side.BLACK,330,bBishop));
    	board.getSquare(0, 3).setPiece(new Queen(board.getSquare(0, 3),Side.BLACK,900,bQueen));
    	board.getSquare(0, 4).setPiece(new King(board.getSquare(0, 4),Side.BLACK,20000,bKing));
    	
	}
    
    public void displayPieces() {
    	for(Piece p:board.getPieces(Side.WHITE))
    		System.out.print(p);
    	System.out.println();
    	for(Piece p:board.getPieces(Side.BLACK))
    		System.out.print(p);
    	System.out.println();
    }

	@Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

    	for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
                Square sq = board.getSquare(i, j);
                sq.paintComponent(g);
            }
        }
    }
	
	public void blackPlayerMove() {
		System.out.println("Black player deciding move......");
		Move move = blackPlayer.decideMove();
		Square sq = move.getInitialSquare();
		move.setInitialSquare(board.getSquare(sq.getRow(),
				sq.getCol()));
		
		sq = move.getTargetSquare();
		move.setTargetSquare(board.getSquare(sq.getRow(),
				sq.getCol()));
		move.setTargetPiece(move.getTargetSquare().getPiece());
		
		cur_piece = move.getInitialSquare().getPiece();		
		System.out.println(move);
		board.makeMove(cur_piece,move);
		turn = 1-turn;
		cur_piece.setMoved();
		board.resetLegalSquares();
		if(board.isCheckMate(turn)) {
			isOver = true;
			board.hasWon(1-turn);
		}
			
		cur_piece = null;
		repaint();
		if(!isOver && !whitePlayer.isHuman()) {
			System.out.println("White to move....");
			whitePlayerMove();
		}
		return;
	}
	
	public void whitePlayerMove() {
		System.out.println("White player deciding move......");
		Move move = whitePlayer.decideMove();
		Square sq = move.getInitialSquare();
		move.setInitialSquare(board.getSquare(sq.getRow(),
				sq.getCol()));
		
		sq = move.getTargetSquare();
		move.setTargetSquare(board.getSquare(sq.getRow(),
				sq.getCol()));
		move.setTargetPiece(move.getTargetSquare().getPiece());
		
		cur_piece = move.getInitialSquare().getPiece();		
		System.out.println(move);
		board.makeMove(cur_piece,move);
		turn = 1-turn;
		cur_piece.setMoved();
		board.resetLegalSquares();
		if(board.isCheckMate(turn)) {
			isOver = true;
			board.hasWon(1-turn);
		}
			
		cur_piece = null;
		repaint();
		if(!isOver && !blackPlayer.isHuman()) {
			System.out.println("Black to move....");
			blackPlayerMove();
		}
		
		return;
	}
	
//	public void blackPlayerMove() {
//		Move move = blackPlayer.decideMove();
//		cur_piece = move.getInitialSquare().getPiece();				
//		board.makeMove(cur_piece,move);
//		turn = 1-turn;
//		cur_piece.setMoved();
//		board.resetLegalSquares();
//		if(board.isCheckMate(turn)) {
//			isOver = true;
//			board.hasWon(1-turn);
//		}
//			
//		cur_piece = null;
//		repaint();
//		return;
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(isOver)
			return;
		
		if((turn == 1 && !whitePlayer.isHuman()) || 
				(turn == 0 && !blackPlayer.isHuman()))
			return;
				
		cur_x = e.getX();
		cur_y = e.getY();
		System.out.println(cur_x+" "+cur_y);
		Square target_square = (Square) this.getComponentAt(new Point(cur_x, cur_y));		
		
		if(cur_piece == null) {
			cur_piece = target_square.getPiece();
			if(cur_piece == null || (cur_piece.getSide()==Side.WHITE && turn==0)
					|| (cur_piece.getSide()==Side.BLACK && turn==1)) {
				cur_piece = null;
				return;
			}
			List<Square> legalMoves = cur_piece.computeLegalMoves();
			for(Square sq:legalMoves) {
				Move move = new Move(cur_piece.getSquare(),sq,
						sq.getPiece());
				if(board.isValid(cur_piece, move))
					sq.setLegal(true);
			}
			repaint();
		}
		else {
			if(target_square.isLegal()) {
				Move move = new Move(cur_piece.getSquare(),target_square,
						target_square.getPiece());
				System.out.println(move);
				board.makeMove(cur_piece,move);				
				turn = 1-turn;
				cur_piece.setMoved();
				board.resetLegalSquares();
				
				cur_piece = null;
				repaint();
				
				if(board.isCheckMate(turn)) {
					isOver = true;
					board.hasWon(1-turn);
				}	
				else blackPlayerMove();
				
				
			}
			else if(target_square.getPiece() != null) {
				board.resetLegalSquares();
				if(target_square.getPiece().getSide() == cur_piece.getSide()) {					
					cur_piece = target_square.getPiece();
					List<Square> legalMoves = cur_piece.computeLegalMoves();
					for(Square sq:legalMoves) {
						Move move = new Move(cur_piece.getSquare(),sq,
								sq.getPiece());
						if(board.isValid(cur_piece, move))
							sq.setLegal(true);
					}
				}
				repaint();				
			}
			else {
				board.resetLegalSquares();
				cur_piece = null;
				repaint();
			}
			
		}
			
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

}
