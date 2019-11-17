package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;
	private static final int MAX_DISTANCE = 2;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		int distance = origin.diagonalDistance(target);
		if (distance > getMaxDistance()) {
			return Error.BAD_DISTANCE;
		}
		return isCorrectEating(origin, target, pieceProvider);
		
	}

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

	int getMaxDistance() {
		return Piece.MAX_DISTANCE;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

}