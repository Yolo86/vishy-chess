package Pieces;

import java.util.ArrayList;
import java.util.List;

import Players.Side;

public class AllPieces {
	private final List<Piece> alivePieces;

    public AllPieces(){
        alivePieces = new ArrayList<Piece>();
    }

    public List<Piece> getAlivePieces(){
        return alivePieces;
    }

    public void addPiece(Piece piece){
        alivePieces.add(piece);
    }

    public void removePiece(Piece piece){
        alivePieces.remove(piece);
    }

    public void revivePiece(Piece piece){
        alivePieces.add(piece);
    }
}
