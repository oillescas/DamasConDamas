package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Man extends Piece{

	private static final int MAX_DISTANCE = 2;

	Man(Color color) {
		super(color);
	}

	int getMaxDistance() {
		return Man.MAX_DISTANCE;
	}
	
	@Override
	Error isCorrectEating(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		int distance = origin.diagonalDistance(target);
		if (distance == getMaxDistance()) {
			Piece piece = pieceProvider.getPiece(origin.betweenDiagonal(target));
			if (piece == null) {
				return Error.EATING_EMPTY;
			} else if(piece.getColor().equals(pieceProvider.getPiece(origin).getColor())) {
				return Error.EATING_SAME_COLOR;
			}
		}
		return null;
	}
	
	@Override
	Error isCorrectSpecific(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {

		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		int distance = origin.diagonalDistance(target);
		if (distance > getMaxDistance()) {
			return Error.BAD_DISTANCE;
		}
		return isCorrectEating(origin, target, pieceProvider);
		
	}
	
	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (this.getColor() == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}
}
