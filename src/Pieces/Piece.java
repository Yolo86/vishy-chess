package Pieces;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import ChessBoard.Board;
import ChessBoard.Square;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import Players.Side;


public abstract class Piece {
	
	private final int value;
    protected Square square;
    protected final Side side;
    private BufferedImage img;
    private Board board;
    protected boolean moved;
    protected boolean won;
    private String img_file;
    
    public Piece(Square square, Side side, int value, String img_file){
        this.square = square;
        this.square.setPiece(this);
        this.value = value;
        this.side = side;
        this.img_file = img_file;
        this.board = this.square.getBoard();
        try {
            if (this.img == null) {
              this.img = ImageIO.read(new FileInputStream("Images/"+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
        moved = false;
        won = false;
        this.board.addPiece(this);
    }
    
    public abstract List<Square> computeLegalMoves();
    
    public void setSquare(Square square){
        this.square = square;
    }
    
    public String getImgFile() {
    	return this.img_file;
    }

    public Square getSquare(){
        return square;
    }

    public Image getImg(){
        return img;
    }

    public int getValue(){
        return value;
    }

    public Side getSide(){
        return side;
    }
    
    public void setMoved() {
    	moved = true;
    }
    
    public boolean hasMoved() {
    	return moved;
    }
    
    public void setWon() {
    	won = true;
    }
    
    public boolean getWon() {
    	return won;
    }
    
    public void draw(Graphics g) {
        int x = square.getX();
        int y = square.getY();
        
        g.drawImage(this.img, x+10, y+7, null);
    }
    
    protected List<Square> computeLinearMoves(){
    	
    	List<Square> legalMoves = new ArrayList<Square>();
        
        int dx=0,dy=1;        
        Square targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=0;dy=-1;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=-1;dy=0;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=1;dy=0;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        
        return legalMoves;
    }
    
    protected List<Square> computeDiagonalMoves(){
    	
    	List<Square> legalMoves = new ArrayList<Square>();
        
        int dx=1,dy=1;        
        Square targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=1;dy=-1;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=-1;dy=1;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        dx=-1;dy=-1;
        targetSquare = square.getAdjacentSquare(dx, dy);
        while(targetSquare != null) {
        	Piece pi = targetSquare.getPiece();
        	if(pi == null)legalMoves.add(targetSquare);
        	else if(pi.side == side)break;
        	else {
        		legalMoves.add(targetSquare);
        		break;
        	}
        	targetSquare = targetSquare.getAdjacentSquare(dx, dy);
        }
        
        
        return legalMoves;
    }

}
