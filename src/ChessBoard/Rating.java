package ChessBoard;

import java.util.List;

import Pieces.Piece;
import Players.Side;
import ThreadedModel.ThreadBoard;

public class Rating {
	static int pawnBoard[][]={
			{ 0,  0,  0,  0,  0,  0,  0,  0},
	        {50, 50, 50, 50, 50, 50, 50, 50},
	        {10, 10, 20, 30, 30, 20, 10, 10},
	        { 5,  5, 10, 25, 25, 10,  5,  5},
	        { 0,  0,  0, 20, 20,  0,  0,  0},
	        { 5, -5,-10,  0,  0,-10, -5,  5},
	        { 5, 10, 10,-20,-20, 10, 10,  5},
	        { 0,  0,  0,  0,  0,  0,  0,  0}};
	static int rookBoard[][]={
	        { 0,  0,  0,  0,  0,  0,  0,  0},
	        { 5, 10, 10, 10, 10, 10, 10,  5},
	        {-5,  0,  0,  0,  0,  0,  0, -5},
	        {-5,  0,  0,  0,  0,  0,  0, -5},
	        {-5,  0,  0,  0,  0,  0,  0, -5},
	        {-5,  0,  0,  0,  0,  0,  0, -5},
	        {-5,  0,  0,  0,  0,  0,  0, -5},
	        { 0,  0,  0,  5,  5,  0,  0,  0}};
	static int knightBoard[][]={
	        {-50,-40,-30,-30,-30,-30,-40,-50},
	        {-40,-20,  0,  0,  0,  0,-20,-40},
	        {-30,  0, 10, 15, 15, 10,  0,-30},
	        {-30,  5, 15, 20, 20, 15,  5,-30},
	        {-30,  0, 15, 20, 20, 15,  0,-30},
	        {-30,  5, 10, 15, 15, 10,  5,-30},
	        {-40,-20,  0,  5,  5,  0,-20,-40},
	        {-50,-40,-30,-30,-30,-30,-40,-50}};
	static int bishopBoard[][]={
	        {-20,-10,-10,-10,-10,-10,-10,-20},
	        {-10,  0,  0,  0,  0,  0,  0,-10},
	        {-10,  0,  5, 10, 10,  5,  0,-10},
	        {-10,  5,  5, 10, 10,  5,  5,-10},
	        {-10,  0, 10, 10, 10, 10,  0,-10},
	        {-10, 10, 10, 10, 10, 10, 10,-10},
	        {-10,  5,  0,  0,  0,  0,  5,-10},
	        {-20,-10,-10,-10,-10,-10,-10,-20}};
	static int queenBoard[][]={
	        {-20,-10,-10, -5, -5,-10,-10,-20},
	        {-10,  0,  0,  0,  0,  0,  0,-10},
	        {-10,  0,  5,  5,  5,  5,  0,-10},
	        { -5,  0,  5,  5,  5,  5,  0, -5},
	        {  0,  0,  5,  5,  5,  5,  0, -5},
	        {-10,  5,  5,  5,  5,  5,  0,-10},
	        {-10,  0,  5,  0,  0,  0,  0,-10},
	        {-20,-10,-10, -5, -5,-10,-10,-20}};
	static int kingMidBoard[][]={
	        {-30,-40,-40,-50,-50,-40,-40,-30},
	        {-30,-40,-40,-50,-50,-40,-40,-30},
	        {-30,-40,-40,-50,-50,-40,-40,-30},
	        {-30,-40,-40,-50,-50,-40,-40,-30},
	        {-20,-30,-30,-40,-40,-30,-30,-20},
	        {-10,-20,-20,-20,-20,-20,-20,-10},
	        { 20, 20,  0,  0,  0,  0, 20, 20},
	        { 20, 30, 10,  0,  0, 10, 30, 20}};
	static int kingEndBoard[][]={
	        {-50,-40,-30,-20,-20,-30,-40,-50},
	        {-30,-20,-10,  0,  0,-10,-20,-30},
	        {-30,-10, 20, 30, 30, 20,-10,-30},
	        {-30,-10, 30, 40, 40, 30,-10,-30},
	        {-30,-10, 30, 40, 40, 30,-10,-30},
	        {-30,-10, 20, 30, 30, 20,-10,-30},
	        {-30,-30,  0,  0,  0,  0,-30,-30},
	        {-50,-30,-30,-30,-30,-30,-30,-50}};
	
	public static int rating(Board board) {
		List<Piece> whitePieces = board.getPieces(Side.WHITE);
		List<Piece> blackPieces = board.getPieces(Side.BLACK);
		int whiteScore = 0;
        int blackScore = 0;
        
        for (Piece p : whitePieces){
            whiteScore += p.getValue();
            int r = p.getSquare().getRow();
            int c = p.getSquare().getCol();
            
            if(p.toString() == "P")whiteScore += pawnBoard[r][c];
            if(p.toString() == "R")whiteScore += rookBoard[r][c];
            if(p.toString() == "N")whiteScore += knightBoard[r][c];
            if(p.toString() == "B")whiteScore += bishopBoard[r][c];
            if(p.toString() == "Q")whiteScore += queenBoard[r][c];
            if(p.toString() == "K" && (whitePieces.size() > 9 
            		&& blackPieces.size() > 9))whiteScore += kingMidBoard[r][c];
            if(p.toString() == "K" && (whitePieces.size() <= 9 
            		&& blackPieces.size() <= 9))whiteScore += kingEndBoard[r][c];
        }

        for (Piece p : blackPieces){
            blackScore += p.getValue();
            int r = 7 - p.getSquare().getRow();
            int c = 7 - p.getSquare().getCol();
            
            if(p.toString() == "p")blackScore += pawnBoard[r][c];
            if(p.toString() == "r")blackScore += rookBoard[r][c];
            if(p.toString() == "n")blackScore += knightBoard[r][c];
            if(p.toString() == "b")blackScore += bishopBoard[r][c];
            if(p.toString() == "q")blackScore += queenBoard[r][c];
            if(p.toString() == "k" && (whitePieces.size() > 9 
            		&& blackPieces.size() > 9))blackScore += kingMidBoard[r][c];
            if(p.toString() == "k" && (whitePieces.size() <= 9 
            		&& blackPieces.size() <= 9))blackScore += kingEndBoard[r][c];
        }
        
        return blackScore - whiteScore;
	}
	
	public static int rating(ThreadBoard chessBoard,int player) {
//		System.out.println("Calculating Rating!");
//		chessBoard.displayPieces();
		int score = calcRating(chessBoard,player);
        chessBoard.flipBoard();
        score+=calcRating(chessBoard,1-player);
        chessBoard.flipBoard();
        
//        System.out.println("Rating Calculated!");
//		chessBoard.displayPieces();
//    	System.out.println("Score: " + score);
        return score;
	}
	
	private static int calcRating(ThreadBoard chessBoard,int player) {
		int score = 0;
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessBoard.getSquare(i,j).equals("P"))score+=100+pawnBoard[i][j];
				if(chessBoard.getSquare(i,j).equals("Q"))score+=900+queenBoard[i][j];
				if(chessBoard.getSquare(i,j).equals("N"))score+=320+knightBoard[i][j];
				if(chessBoard.getSquare(i,j).equals("B"))score+=330+bishopBoard[i][j];
				if(chessBoard.getSquare(i,j).equals("R"))score+=500+rookBoard[i][j];
				if(chessBoard.getSquare(i,j).equals("K"))score+=20000+kingMidBoard[i][j];
			}
		}
		
		if(player == 1)
			score*=-1;
		return score;
	}
}
