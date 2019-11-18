package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.Controller;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.King;
import es.urjccode.mastercloudapps.adcs.draughts.models.Man;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;

class GameView extends SubView {

    private static final String[] COLORS = new String[]{"b", "n", " "};

	void write(Controller controller) {
        assert controller != null;
        final int DIMENSION = controller.getDimension();
        this.writeNumbersLine(DIMENSION);
        for(int i=0; i<DIMENSION; i++){
            this.console.write((i+1)+"");
            for(int j=0; j<DIMENSION; j++){
                Piece piece = controller.getPiece(new Coordinate(i,j));
                if (piece == null){
                    this.console.write(GameView.COLORS[2]);
                } else {
                    this.console.write(getPieceColor(piece));
                }
            }
            this.console.writeln((i+1)+"");
        }
        this.writeNumbersLine(DIMENSION);
	}

	String getPieceColor(Piece piece) {
		String color = GameView.COLORS[piece.getColor().ordinal()];
		if(piece instanceof King) {
			color = color.toUpperCase();
		}
		return color;
	}
	


    private void writeNumbersLine(final int DIMENSION) {
        assert DIMENSION > 0;
        this.console.write(" ");
        for(int i=0; i<DIMENSION; i++){
            this.console.write((i+1)+"");
        }
        this.console.writeln();
    }

}
