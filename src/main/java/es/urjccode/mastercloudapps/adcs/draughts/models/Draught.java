package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

class Draught extends Piece {

    private static final int MAX_DISTANCE = 7;

	Draught(Color color) {
        super(color);
    }
    
    @Override
    boolean isAdvanced(Coordinate origin, Coordinate target) {
		return true;
	}

    @Override
	int getMaxDistance() {
		return Draught.MAX_DISTANCE;
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
					if(piece.getColor()==Color.WHITE) {
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
}