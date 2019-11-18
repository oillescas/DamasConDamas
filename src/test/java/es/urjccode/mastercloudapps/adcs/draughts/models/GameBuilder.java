package es.urjccode.mastercloudapps.adcs.draughts.models;

public class GameBuilder {
	
	String[] rows;
	
	GameBuilder(){
		this.rows = new String[8];
	}
	
	GameBuilder putRow(int i, String row) {
		this.rows[i] = row;
		
		return this;
	}
	
	Game build() {
		Board board = new Board();
		for (int i = 0; i < rows.length; i++) {
			String row = rows[i];
			if(row != null) {
				for (int j = 0; j < row.length(); j++) {
					char square = row.charAt(j);
					if(square=='b') board.put(new Coordinate(i, j), new Man(Color.WHITE));
					else if(square=='n') board.put(new Coordinate(i, j), new Man(Color.BLACK));
					else if(square=='B') board.put(new Coordinate(i, j), new King(Color.WHITE));
					else if(square=='N') board.put(new Coordinate(i, j), new King(Color.BLACK));
				}
			}
		}
		
		
		Game game = new Game();
		game.board = board;
		return game ;
	}

}
