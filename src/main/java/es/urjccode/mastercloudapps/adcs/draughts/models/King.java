package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public class King extends Piece {

	King(Color color) {
        super(color);
    }
  
    @Override
	Error isCorrectEating(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		int distance = origin.diagonalDistance(target);
		if (distance > 2) {
			int count = 0;
			List<Coordinate> betweens = origin.betweensDiagonal(target);
			for (Coordinate coordinate : betweens) {
				Piece piece = pieceProvider.getPiece(coordinate);
				if(piece!=null) {
					count++;
					if(piece.getColor()==pieceProvider.getPiece(origin).getColor()) {
						return Error.EATING_SAME_COLOR;
					}
				} 
			}
			if(count > 1){
				return Error.EATING_ERROR;
			}
		}
		return null;
	}
    
    @Override
	Error isCorrectSpecific(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		return isCorrectEating(origin, target, pieceProvider);
	}
}