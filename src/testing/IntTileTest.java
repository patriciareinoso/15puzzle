package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import tools.InvalidMovementException;
import tools.InvariantBrokenException;

import models.IntTile;
import models.SolvingSequence;
import models.SolvingSequence.Direction;

/**
 * Unit testing for {@link models.IntTile} class.
 * @author patty
 *
 */
public class IntTileTest {
	
	/**
	 * Test for the {@link models.IntTile#IntTile(Integer, boolean)()} constructor.
	 */
	@Test
	public void testTileCreation(){
		IntTile tile = new IntTile(5, false);
	}
	
	/**
	 * Test for the {@link models.IntTile#IntTile(Integer, boolean)()} constructor.
	 * Tile values should be greater than {@link models.IntTile#MIN}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testTileUnderZero(){
		IntTile tile = new IntTile(-5,false);
	}
	
	/**
	 * Test for the {@link models.IntTile#IntTile(Integer, boolean)()} constructor.
	 * Tile values should be smaller than {@link models.IntTile#MAX}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testTileOver15(){
		IntTile tile = new IntTile(16,false);
	}
	
	/**
	 * Test for the {@link models.IntTile#setValue(Object)} constructor.
	 * Tile values should be greater than {@link models.IntTile#MIN}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetValueUnderZero(){
		IntTile tile = new IntTile(5,false);
		tile.setValue(-1);
	}
	
	/**
	 * Test for the {@link models.IntTile#setValue(Object)} constructor.
	 * Tile values should be smaller than {@link models.IntTile#MAX}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetValueOver15(){
		IntTile tile = new IntTile(5,false);
		tile.setValue(16);
	}
	
	/**
	 * Test for the {@link models.IntTile#setValue(Object)} constructor.
	 * Tile values should be integers between {@link models.IntTile#MIN} .. {@link models.IntTile#MAX}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetValueAsString(){
		IntTile tile = new IntTile(6,false);
		tile.setValue("A");
	}
}
