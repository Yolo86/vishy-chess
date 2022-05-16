package ChessBoard;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import Pieces.Piece;
import Players.Side;

public class Square extends JComponent{
	
	private final Board board;
    private final int row;
    private final int col;
    private boolean legal;
    private int color;
    private Piece piece;
    
    public Square(Board board, int row, int col,int color){
        this.board = board;
        this.row = row;
        this.col = col;
        this.color=color;
        this.setBorder(BorderFactory.createLineBorder(Color.black,3));
//        System.out.println(color);
    }

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece(){
        return piece;
    }
	
	public Board getBoard(){
        return board;
    }
	
	public int getColor(){
        return color;
    }

    public Square getAdjacentSquare(int horizontal, int vertical){
        return board.getSquare(vertical + row, horizontal + col);
    }

    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }

    public void setLegal(boolean legal){
        this.legal = legal;
    }

    public boolean isLegal(){
        return legal;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(this.legal || (this.getPiece() != null && this.getPiece().getWon())) 
        	g.setColor(new Color(255,215,0));
        else if (color == 0) 
            g.setColor(new Color(232, 235, 239));
        else 
            g.setColor(new Color(125, 135, 150));
        
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if(piece != null) {
        	piece.draw(g);
        }
    }
    
    @Override
    public String toString(){
        return String.valueOf(row)+" "+String.valueOf(col);
    }

}
