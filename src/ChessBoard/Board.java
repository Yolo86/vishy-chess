package ChessBoard;

import java.util.ArrayList;
import java.util.List;

import Pieces.AllPieces;
import Pieces.Bishop;
import Pieces.BlackPawn;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.WhitePawn;
import Players.Side;

public class Board {
	public static final int LENGTH = 8;

    private Square[][] squares;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    
    public Board(){
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();
        squares = new Square[LENGTH][LENGTH];
        int k=0;
        
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
                squares[i][j] = new Square(this, i, j, k);
                k=1-k;
            }   
            k=1-k;
        }
    }

    public List<Piece> getPieces(Side side){
        return side == Side.WHITE ? whitePieces : blackPieces;
    }
    
    public void addPiece(Piece piece) {
    	if(piece.getSide() == Side.WHITE) whitePieces.add(piece);
    	else blackPieces.add(piece);
    }
    
    public void removePiece(Piece piece) {
    	if(piece.getSide() == Side.WHITE) whitePieces.remove(piece);
    	else blackPieces.remove(piece);
    }

    public Square getSquare(int row, int col){
        return (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) ? null : squares[row][col];
    }
    
    public void makeMove(Piece piece,Move move) {
    	if(move.getTargetPiece() != null) {
    		move.getTargetPiece().setSquare(null);
    		removePiece(move.getTargetPiece());
    	}
    	move.getTargetSquare().setPiece(piece);
    	piece.setSquare(move.getTargetSquare());
    	move.getInitialSquare().setPiece(null);
    	
    	if(piece.toString() == "P" && piece.getSquare().getRow() == 0) {
    		piece.setSquare(null);
    		removePiece(piece);
    		move.getTargetSquare().setPiece(new Queen(move.getTargetSquare(),Side.WHITE,900,"whiteQueen.png"));
    	}
    	
    	if(piece.toString() == "p" && piece.getSquare().getRow() == 7) {
    		piece.setSquare(null);
    		removePiece(piece);
    		move.getTargetSquare().setPiece(new Queen(move.getTargetSquare(),Side.BLACK,900,"blackQueen.png"));
    	}
    }
    
    public void undoMove(Piece piece,Move move) {
    	removePiece(move.getTargetSquare().getPiece());
    	addPiece(piece);
    	if(move.getTargetPiece() != null) {
    		addPiece(move.getTargetPiece());
    		move.getTargetPiece().setSquare(move.getTargetSquare());
    	}
    	move.getTargetSquare().setPiece(move.getTargetPiece());
    	piece.setSquare(move.getInitialSquare());
    	move.getInitialSquare().setPiece(piece);
    }
    
    public boolean inCheck(Side side) {
    	List<Square> legalMoves = new ArrayList<Square>();
		Square sq = getSquare(0, 0);
		
    	if(side == Side.WHITE) {    		
    		for(Piece p:blackPieces)
    			legalMoves.addAll(p.computeLegalMoves());
    		for(Piece p:whitePieces) {
    			if(p.toString() == "K") {
    				sq = p.getSquare();
    				break;
    			}
    		}
    		for(Square ls:legalMoves) {
    			if(ls.getRow() == sq.getRow() && ls.getCol() == sq.getCol())
    				return true;
    		}
    	}
    	else {
    		for(Piece p:whitePieces)
    			legalMoves.addAll(p.computeLegalMoves());
    		for(Piece p:blackPieces) {
    			if(p.toString() == "k") {
    				sq = p.getSquare();
    				break;
    			}
    		}
    		for(Square ls:legalMoves) {
    			if(ls.getRow() == sq.getRow() && ls.getCol() == sq.getCol())
    				return true;
    		}
    	}
    	return false;
    }
    
    public boolean isValid(Piece piece,Move move) {
    	makeMove(piece,move);
    	if(inCheck(piece.getSide())) {
    		undoMove(piece,move);
    		return false;
    	}
    	undoMove(piece,move);
    	return true;
    }
    
    public boolean isCheckMate(int turn) {    
    	List<Piece> allPieces = new ArrayList<Piece>();
    	
    	if(turn == 1) {    		
    		for(Piece p:whitePieces) {
    			allPieces.add(p);
    		}
    		for(Piece p:allPieces) {
    			List<Square> allMoves = p.computeLegalMoves();
    			for(Square sq:allMoves) {
    				Move move = new Move(p.getSquare(),sq,
						sq.getPiece());
    				if(isValid(p, move))
    					return false;
    			}
    		}
    	}
    	else {
    		for(Piece p:blackPieces) {
    			allPieces.add(p);
    		}
    		for(Piece p:allPieces) {
    			List<Square> allMoves = p.computeLegalMoves();
    			for(Square sq:allMoves) {
    				Move move = new Move(p.getSquare(),sq,
						sq.getPiece());
    				if(isValid(p, move))
    					return false;
    			}
    		}
    	}
    	
    	return true;
    }
    
    public void hasWon(int s) {
    	if(s == 1) {
    		for(Piece p:whitePieces) {
    			p.setWon();
    		}
    	}
    	else {
    		for(Piece p:blackPieces) {
    			p.setWon();
    		}
    	}
    }

    public int computeRating(){
        return Rating.rating(this);
    }

    public void display(){
    	for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
                if (squares[i][j].getPiece() != null)
                    System.out.print(squares[i][j].getPiece());
                else System.out.print(".");
            }
            System.out.println();
        }
    }
    
    public void resetLegalSquares() {
    	for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
                squares[i][j].setLegal(false);
            }   
        }
    }

}
