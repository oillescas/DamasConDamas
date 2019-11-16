package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameWithDraughtsTest {


    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
    	Game game = new GameBuilder()
    			.putRow(1, "b       ")
    			.build();
    	
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        
        game.move(origin, target);
        Piece king = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(king instanceof Draught);

    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts(){
    	Game game = new GameBuilder()
    			.putRow(2, " b      ")
    			.putRow(1, "  n     ")
    			.build();
    	
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        game.move(origin, target);
        Piece king = game.getPiece(target);
        
        assertNull(game.getPiece(origin.betweenDiagonal(target)));
        assertNull(game.getPiece(origin));
        assertTrue(king instanceof Draught);
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
    	Game game = new GameBuilder()
    			.putRow(6, "   n b  ")
    			.build();
    	
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        game.move(new Coordinate(6, 5), new Coordinate(5,4));
        game.move(origin, target);
        Piece king = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(king instanceof Draught);
    }
    
    @Test
    public void testGivenGameWhenBlackPawnAtLimitAndEatingThenNewDraugts(){
    	Game game = new GameBuilder()
    			.putRow(5, "n       ")
    			.putRow(7, "b       ")
    			.build();
    	
        Coordinate origin = new Coordinate(5,0);
        Coordinate target = new Coordinate(7,2);
        game.move(new Coordinate(7, 0), new Coordinate(6, 1));
        game.move(origin, target);
        Piece king = game.getPiece(target);
        
        assertNull(game.getPiece(origin.betweenDiagonal(target)));
        assertNull(game.getPiece(origin));
        assertTrue(king instanceof Draught);
    }
    
    @Test
    public void testGivenGameWhenWhitePawnMoveBackThenOk() {
    	Game game = new GameBuilder()
    			.putRow(1, "  B     ")
    			.build();
    	
    	Coordinate origin = new Coordinate(1,2);
        Coordinate target = new Coordinate(2,3);
        
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target).getColor(), Color.WHITE);
    }
    
}