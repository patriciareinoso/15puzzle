package tools;

/**
 * <code>InvalidMovementException</code> is raised when an invalid movement is tried.
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