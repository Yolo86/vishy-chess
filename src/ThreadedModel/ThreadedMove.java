package ThreadedModel;

public class ThreadedMove {
	private String initialPiece;
    private String targetPiece;
    public int rating;
    private int x1,y1,x2,y2;

    public ThreadedMove(String s,int a,int b,String u,int c,int d){
        this.x1=a;
        this.y1=b;
        this.x2=c;
        this.y2=d;
        this.initialPiece=s;
        this.targetPiece=u;
        rating = 0;
    }
    
    
    public int getInitialRow(){
        return x1;
    }
    
    public int getInitialCol(){
        return y1;
    }
    
    public int getTargetRow(){
        return x2;
    }
    
    public int getTargetCol(){
        return y2;
    }
    
    public String getInitialPiece(){
        return initialPiece;
    }

    public String getTargetPiece(){
        return targetPiece;
    }

    @Override
    public String toString(){
        return initialPiece + ": " + x1 + " " + y1 + " -> " + x2 + " " + y2 + ": " + targetPiece;
    }
}
