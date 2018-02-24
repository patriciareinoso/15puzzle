package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import tools.InvalidMovementException;
import tools.InvariantBrokenException;

import models.Board;
import models.SolvingSequence;
import models.SolvingSequence.Direction;

/**
 * Unit testing for {@link models.Board} class.
 * @author patty
 *
 */
public class BoardTest {
	
	/**
	 * Test for the {@link models.Board#Board()} no argument constructor.
	 */
	@Test
	public void testBoardCreation(){
		Board board = new Board();
	}
	
	/**
	 * Test for the {@link models.Board#Board()} no argument constructor.
	 * The length of the board after creation should match
	 * the size established in {@link Board.SIZE}
	 */
	@Test
	public void testBoardLenghtAfterCreation(){
		Board board = new Board();
		assertTrue(board.getTiles().length == Board.SIZE);
		
	}
	
	/**
	 * Test for the {@link models.Board#Board()} no argument constructor.
	 * The board should be solved after creation.
	 */
	@Test
	public void testBoardIsSolvedAfterCreation(){
		Board board = new Board();
		assertTrue(board.isSolved());
	}
	
	/**
	 * Test for the {@link models.Board#Board()} no argument constructor.
	 * The blank space should be on the last position after creation.
	 */
	@Test
	public void testSpaceIsInTheLastPositionAfterCreation(){
		Board board = new Board();
		assertTrue(board.getSpacePosition() == 15);
	}
	
	/**
	 * Test for the {@link models.Board#moveEmptyTile(models.SolvingSequence.Direction)}.
	 * The board is not solved after moving the blank space up.
	 */
	@Test
	public void testBoardIsNotSolvedAfterOneValidMovement(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.moveEmptyTile(SolvingSequence.Direction.UP);
		assertFalse(board.isSolved());
	}
	
	/**
	 * Test for the {@link models.Board#moveEmptyTile(models.SolvingSequence.Direction)}.
	 * The board is solved after moving 2 opposite movements.
	 * For example: UP and then DOWN.
	 */
	@Test
	public void testBoardIsSolvedAfterApplyingTwoOppositeMovements(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.moveEmptyTile(SolvingSequence.Direction.UP);
		board.moveEmptyTile(SolvingSequence.Direction.DOWN);
		assertTrue(board.isSolved());
	}
	
	/**
	 * Test for the {@link models.Board#moveEmptyTile(models.SolvingSequence.Direction)}.
	 * The board is solved after moving 2 opposite movements.
	 * For example: LEFT and then RIGHT.
	 */
	@Test
	public void testBoardIsSolvedAfterApplyingTwoOppositeMovements2(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.moveEmptyTile(SolvingSequence.Direction.LEFT);
		board.moveEmptyTile(SolvingSequence.Direction.RIGHT);
		assertTrue(board.isSolved());
	}
	
	/**
	 * Test for the {@link models.Board#moveEmptyTile(models.SolvingSequence.Direction)}.
	 * The current position of the blank space should be updated.
	 */
	@Test
	public void testSpacePositionIsUpdated(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.moveEmptyTile(SolvingSequence.Direction.UP);
		assertTrue(board.getSpacePosition() == 11);
	}
	
	/**
	 * Test for the {@link models.Board#moveEmptyTile(models.SolvingSequence.Direction)}.
	 * Try to apply an invalid first movement (DOWN).
	 */
	@Test (expected=InvalidMovementException.class)
	public void testInvalidFirstMovement(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.moveEmptyTile(SolvingSequence.Direction.DOWN);
	}
	
	/**
	 * Test for the {@link models.Board#applySolvingSequence(SolvingSequence)}.
	 * Try to apply an invalid sequence of UP movements (more than 3 should not be possible).
	 */
	@Test (expected=InvalidMovementException.class)
	public void testInvalidSequenceUP(){
		Board board = new Board();
		assertTrue(board.isSolved());
		SolvingSequence seq = new SolvingSequence();
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.UP);
		board.applySolvingSequence(seq);
	}
	
	/**
	 * Test for the {@link models.Board#applySolvingSequence(SolvingSequence)}.
	 * Try to apply an invalid sequence of LEFT movements (more than 3 should not be possible).
	 */
	@Test (expected=InvalidMovementException.class)
	public void testInvalidSequenceLEFT(){
		Board board = new Board();
		assertTrue(board.isSolved());
		SolvingSequence seq = new SolvingSequence();
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.LEFT);
		board.applySolvingSequence(seq);
	}
	
	/**
	 * Test for the {@link models.Board#applySolvingSequence(SolvingSequence)}.
	 * Get the board to a state where the blank space is on the left border.
	 * Try to apply an invalid sequence of RIGHT movements (more than 3 should not be possible).
	 */
	@Test (expected=InvalidMovementException.class)
	public void testInvalidSequenceRIGHT(){
		Board board = new Board();
		assertTrue(board.isSolved());
		SolvingSequence seq = new SolvingSequence();
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.RIGHT);
		seq.addMovement(Direction.RIGHT);
		seq.addMovement(Direction.RIGHT);
		seq.addMovement(Direction.RIGHT);
		board.applySolvingSequence(seq);
	}
	
	
	/**
	 * Test for the {@link models.Board#applySolvingSequence(SolvingSequence)}.
	 * Get the board to a state where the blank space is on the top border.
	 * Try to apply an invalid sequence of DOWN movements (more than 3 should not be possible).
	 */
	@Test (expected=InvalidMovementException.class)
	public void testInvalidSequenceDOWN(){
		Board board = new Board();
		assertTrue(board.isSolved());
		SolvingSequence seq = new SolvingSequence();
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.DOWN);
		board.applySolvingSequence(seq);
	}
	
	/**
	 * Test for the {@link models.Board#setSpacePosition(int)}.
	 * Change the current position of the blank space without moving
	 * the tile should break the invariant.
	 */
	@Test(expected=InvariantBrokenException.class)
	public void testUpdateSpacePositionWithoutMoving(){
		Board board = new Board();
		assertTrue(board.isSolved());
		board.setSpacePosition(2);
	}
}
