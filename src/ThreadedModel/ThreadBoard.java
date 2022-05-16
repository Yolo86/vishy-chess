package ThreadedModel;

import java.util.ArrayList;
import java.util.List;

import ChessBoard.Board;
import ChessBoard.Move;
import ChessBoard.Rating;
import Pieces.Piece;

public class ThreadBoard implements Runnable {
	public static final int LENGTH = 8;
	private int maxDepth;
	private int alpha;
	private int beta;
	private int value;
	private Board board;
	private ThreadedMove move;
	public String chessBoard[][]={
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."},
	        {".",".",".",".",".",".",".","."}};
    
    public ThreadBoard(Board board,ThreadedMove move,int a,int b,int c){
    	this.board = board;
    	this.move = move;
    	maxDepth=a;
    	alpha=b;
    	beta=c;
    }
    
    public void setBoard() {
    	for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(board.getSquare(i, j).getPiece()!=null) {
        			chessBoard[i][j]=board.getSquare(i, j).getPiece().toString();
        		}
            }   
        }
    	
    	flipBoard();
    	makeMove(move);
    	flipBoard();
    }

    public String getSquare(int row, int col){
        return (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) ? null : chessBoard[row][col];
    }
    
    public int getValue() {
    	return value;
    }
    
    public void flipBoard() {
    	for(int i=0;i<4;i++) {
        	for(int j=0;j<8;j++) {
        		String tmp = chessBoard[i][j];
        		chessBoard[i][j] = chessBoard[7-i][7-j];
        		chessBoard[7-i][7-j] = tmp;
            }   
        }
    	
    	for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(chessBoard[i][j].equals("."))
        			continue;
        		if (Character.isUpperCase(chessBoard[i][j].charAt(0))) {
        			chessBoard[i][j]=chessBoard[i][j].toLowerCase();
                } else {
                	chessBoard[i][j]=chessBoard[i][j].toUpperCase();
                }
            }   
        }
    }
    
    public void displayPieces() {
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			System.out.print(getSquare(i, j));
    		}
    		System.out.println();
    	}
    }
    
    public void makeMove(ThreadedMove move) {
    	if(move==null)
    		return;
//    	System.out.println("Making Move: " + move);
    	int x1 = move.getInitialRow();
    	int y1 = move.getInitialCol();
    	int x2 = move.getTargetRow();
    	int y2 = move.getTargetCol();
    	chessBoard[x1][y1] = ".";
    	chessBoard[x2][y2] = move.getInitialPiece();
//    	System.out.println(x1+" "+y1+" "+x2+" "+y2);
    	if(chessBoard[x2][y2].equals("P") && x2==0) {
    		chessBoard[x2][y2] = "Q";
    	}
//    	System.out.println("Move done! " + move);
    }
       
    public void undoMove(ThreadedMove move) {
    	int x1 = move.getInitialRow();
    	int y1 = move.getInitialCol();
    	int x2 = move.getTargetRow();
    	int y2 = move.getTargetCol();
    	chessBoard[x1][y1] = move.getInitialPiece();
    	chessBoard[x2][y2] = move.getTargetPiece();
    }
    
    public List<Integer> pawnMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	if(i-1>=0 && chessBoard[i-1][j] == ".") {
    		legalMoves.add((i-1)*8+j);
    		if(i==6 && chessBoard[i-2][j] == ".") 
    			legalMoves.add((i-2)*8+j);    		
    	}
    	
    	if(i-1>=0 && j-1>=0 && chessBoard[i-1][j-1] != "." 
    			&& Character.isLowerCase(chessBoard[i-1][j-1].charAt(0))) {
    		legalMoves.add((i-1)*8+j-1);		
    	}
    	
    	if(i-1>=0 && j+1<LENGTH && chessBoard[i-1][j+1] != "." 
    			&& Character.isLowerCase(chessBoard[i-1][j+1].charAt(0))) {
    		legalMoves.add((i-1)*8+j+1);		
    	}
    	
    	return legalMoves;
    }
    
    public List<Integer> kingMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	if(i-1>=0 && (chessBoard[i-1][j] == "." 
    			|| Character.isLowerCase(chessBoard[i-1][j].charAt(0)))) {
    		legalMoves.add((i-1)*8+j);   		
    	}
    	if(i-1>=0 && j-1>=0 && (chessBoard[i-1][j-1] == "." 
    			|| Character.isLowerCase(chessBoard[i-1][j-1].charAt(0)))) {
    		legalMoves.add((i-1)*8+j-1);   		
    	}
    	if(i-1>=0 && j+1<LENGTH && (chessBoard[i-1][j+1] == "." 
    			|| Character.isLowerCase(chessBoard[i-1][j+1].charAt(0)))) {
    		legalMoves.add((i-1)*8+j+1);   		
    	}
    	if(j-1>=0 && (chessBoard[i][j-1] == "." 
    			|| Character.isLowerCase(chessBoard[i][j-1].charAt(0)))) {
    		legalMoves.add(i*8+j-1);   		
    	}
    	if(j+1<LENGTH && (chessBoard[i][j+1] == "." 
    			|| Character.isLowerCase(chessBoard[i][j+1].charAt(0)))) {
    		legalMoves.add(i*8+j+1);   		
    	}
    	if(i+1<LENGTH && (chessBoard[i+1][j] == "." 
    			|| Character.isLowerCase(chessBoard[i+1][j].charAt(0)))) {
    		legalMoves.add((i+1)*8+j);   		
    	}
    	if(i+1<LENGTH && j-1>=0 && (chessBoard[i+1][j-1] == "." 
    			|| Character.isLowerCase(chessBoard[i+1][j-1].charAt(0)))) {
    		legalMoves.add((i+1)*8+j-1);   		
    	}
    	if(i+1<LENGTH && j+1<LENGTH && (chessBoard[i+1][j+1] == "." 
    			|| Character.isLowerCase(chessBoard[i+1][j+1].charAt(0)))) {
    		legalMoves.add((i+1)*8+j+1);   		
    	}
    	
    	return legalMoves;
    }
    
    public List<Integer> knightMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	if(i-1>=0 && j+2<LENGTH && (chessBoard[i-1][j+2] == "." 
    			|| Character.isLowerCase(chessBoard[i-1][j+2].charAt(0)))) {
    		legalMoves.add((i-1)*8+j+2);   		
    	}
    	if(i-1>=0 && j-2>=0 && (chessBoard[i-1][j-2] == "." 
    			|| Character.isLowerCase(chessBoard[i-1][j-2].charAt(0)))) {
    		legalMoves.add((i-1)*8+j-2);   		
    	}
    	if(i-2>=0 && j+1<LENGTH && (chessBoard[i-2][j+1] == "." 
    			|| Character.isLowerCase(chessBoard[i-2][j+1].charAt(0)))) {
    		legalMoves.add((i-2)*8+j+1);   		
    	}
    	if(i-2>=0 && j-1>=0 && (chessBoard[i-2][j-1] == "." 
    			|| Character.isLowerCase(chessBoard[i-2][j-1].charAt(0)))) {
    		legalMoves.add((i-2)*8+j-1);   		
    	}
    	
    	if(i+1<LENGTH  && j+2<LENGTH && (chessBoard[i+1][j+2] == "." 
    			|| Character.isLowerCase(chessBoard[i+1][j+2].charAt(0)))) {
    		legalMoves.add((i+1)*8+j+2);   		
    	}
    	if(i+1<LENGTH  && j-2>=0 && (chessBoard[i+1][j-2] == "." 
    			|| Character.isLowerCase(chessBoard[i+1][j-2].charAt(0)))) {
    		legalMoves.add((i+1)*8+j-2);   		
    	}
    	if(i+2<LENGTH  && j+1<LENGTH && (chessBoard[i+2][j+1] == "." 
    			|| Character.isLowerCase(chessBoard[i+2][j+1].charAt(0)))) {
    		legalMoves.add((i+2)*8+j+1);   		
    	}
    	if(i+2<LENGTH  && j-1>=0 && (chessBoard[i+2][j-1] == "." 
    			|| Character.isLowerCase(chessBoard[i+2][j-1].charAt(0)))) {
    		legalMoves.add((i+2)*8+j-1);		
    	}
    	
    	return legalMoves;
    }
    
    public List<Integer> bishopMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	int a=i+1,b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;b++;
    	}
    	
    	a=i+1;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;b--;
    	}
    	
    	a=i-1;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;b--;
    	}
    	
    	a=i-1;b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;b++;
    	}
    	
    	return legalMoves;
    }
    
    public List<Integer> queenMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	int a=i+1,b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;b++;
    	}
    	
    	a=i+1;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;b--;
    	}
    	
    	a=i-1;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;b--;
    	}
    	
    	a=i-1;b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;b++;
    	}
    	
    	a=i;b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		b++;
    	}
    	
    	a=i;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		b--;
    	}
    	
    	a=i+1;b=j;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;
    	}
    	
    	a=i-1;b=j;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;
    	}
    	
    	return legalMoves;
    }
    
    public List<Integer> rookMoves(int i,int j){
    	List<Integer> legalMoves = new ArrayList<Integer>();
    	
    	int a=i,b=j+1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		b++;
    	}
    	
    	a=i;b=j-1;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		b--;
    	}
    	
    	a=i+1;b=j;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a++;
    	}
    	
    	a=i-1;b=j;
    	while(a>=0 && a<LENGTH && b>=0 && b<LENGTH) {
    		if(chessBoard[a][b]==".")legalMoves.add(a*8+b);
    		else if(Character.isLowerCase(chessBoard[a][b].charAt(0))) {
    			legalMoves.add(a*8+b);
    			break;
    		}
    		else break;
    		a--;
    	}
    	
    	return legalMoves;
    }
    
    public List<ThreadedMove> computeAllMoves(){
    	List<ThreadedMove> allMoves = new ArrayList<ThreadedMove>();
//    	System.out.println("Compute all moves!");
//    	displayPieces();
    	for(int i=0;i<LENGTH;i++) {
    		for(int j=0;j<LENGTH;j++) {
    			if(chessBoard[i][j]=="." || Character.isLowerCase(chessBoard[i][j].charAt(0)))
    				continue;
    			
    			List<Integer> moves = new ArrayList<Integer>();
    			if(chessBoard[i][j].equals("P"))moves.addAll(pawnMoves(i,j));    			
    			if(chessBoard[i][j].equals("K"))moves.addAll(kingMoves(i,j));
    			if(chessBoard[i][j].equals("N"))moves.addAll(knightMoves(i,j));
    			if(chessBoard[i][j].equals("Q"))moves.addAll(queenMoves(i,j));
    			if(chessBoard[i][j].equals("B"))moves.addAll(bishopMoves(i,j));
    			if(chessBoard[i][j].equals("R"))moves.addAll(rookMoves(i,j));
//    			System.out.println("Yo: "+i+" "+j+" " + chessBoard[i][j] + " " + moves.size());
    			for(int k=0;k<moves.size();k++) {
    				int a = moves.get(k)/8,b = moves.get(k)%8;
    				ThreadedMove move = new ThreadedMove(chessBoard[i][j],i,j,chessBoard[a][b],a,b);
    				allMoves.add(move);
    			}
    		}
    	}
    	return allMoves;
    }
    
    public List<ThreadedMove> computeAllLegalMoves(){
//    	System.out.println("Computing Moves!");
    	List<ThreadedMove> allMoves = computeAllMoves();
//    	System.out.println("Available Moves: ");
//    	for(ThreadedMove m:allMoves) {
//    		System.out.println(m);
//    	}
    	List<ThreadedMove> legalMoves = new ArrayList<ThreadedMove>();
    	
    	for(int k=0;k<allMoves.size();k++) {
    		if(isValid(allMoves.get(k))) 
    			legalMoves.add(allMoves.get(k));    			
    	}
    	
//    	displayPieces();
//    	
//    	System.out.println("Legal Moves: ");
//    	for(ThreadedMove m:legalMoves) {
//    		System.out.println(m);
//    	}
    	
    	return legalMoves;
    }
    
    public boolean inCheck() {
    	List<ThreadedMove> allMoves = computeAllMoves();
    	int a=-1,b=-1;
    	
    	for(int i=0;i<LENGTH;i++) {
    		for(int j=0;j<LENGTH;j++) {
    			if(chessBoard[i][j].equals("k")) {
    				a=i;b=j;
    				break;
    			}
    		}
    	}
    	
    	for(int k=0;k<allMoves.size();k++) {
    		if(allMoves.get(k).getTargetRow() == a && allMoves.get(k).getTargetCol() == b)
    			return true;
    	}
    	
    	return false;
    }
    
    public boolean isValid(ThreadedMove move) {
//    	System.out.println("Checking validity: " + move);
//    	displayPieces();
    	makeMove(move);
//    	System.out.println("After move:");
//    	displayPieces();
    	flipBoard();
//    	System.out.println("Flip!");
//    	displayPieces();
    	if(inCheck()) {
    		flipBoard();
    		undoMove(move);    		
    		return false;
    	}
    	flipBoard();
    	undoMove(move);    	
    	return true;
    }
    
    public boolean isCheckMate() {
    	List<ThreadedMove> legalMoves = computeAllLegalMoves();
    	return (legalMoves.size()>0);
    }
    
    public int maximizer(int depth,int alpha,int beta) {
    	if(depth == 0)
    		return computeRating(0);
    	
    	int mx = Integer.MIN_VALUE;
		List<ThreadedMove> moves =  computeAllLegalMoves();
		
		for(ThreadedMove move:moves) {
			makeMove(move);
			flipBoard();
			
			int value = minimizer(depth-1, alpha, beta);
			
			flipBoard();
			undoMove(move);
			mx = Integer.max(mx, value);
			alpha = Integer.max(alpha, value);
			if(beta <= alpha)
				return mx;
		}
		
		return mx;
    }
    
    public int minimizer(int depth, int alpha, int beta) {
//		System.out.println("Minimizer!");
//		displayPieces();
		if(depth == 0)
			return computeRating(1);
		
		int mn = Integer.MAX_VALUE;
		List<ThreadedMove> moves =  computeAllLegalMoves();
		
		for(ThreadedMove move:moves) {
			makeMove(move);
			flipBoard();
			
			int value = maximizer(depth-1, alpha, beta);
			
			flipBoard();
			undoMove(move);
			mn = Integer.min(mn, value);
			beta = Integer.min(beta,value);
			if(beta <= alpha)
				return mn;	
		}
		
		return mn;
	}
    
    public int computeRating(int player){
        return Rating.rating(this,player);
    }

	@Override
	public void run() {
		setBoard();
		this.value=minimizer(maxDepth-1,alpha,beta);
	}
}
