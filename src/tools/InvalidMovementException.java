package tools;

/**
 * <code>InvariantBrokenException</code> is raised when the invariant of a class is not true in
 * the current state of the instance (corresponding to an invalid or unsafe state)
 * @author Patricia
 */
public class InvalidMovementException extends RuntimeException {

private static final long serialVersionUID = 1L;

/**
   * Constructor of the exception raised when an invalid movement is tried.
   * A movement is considered invalid when it tries to move the empty space
   * out of the boundaries of the board.
   * @param message is used to explain.
   */
  public InvalidMovementException(String message) {
    super(message);
  }
}