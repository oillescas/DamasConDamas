package es.urjccode.mastercloudapps.adcs.draughts.models;

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
}