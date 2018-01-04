package tools;

/**
 * <code>InvariantBrokenException</code> is raised when the invariant of a class is not true in
 * the current state of the instance (corresponding to an invalid or unsafe state)
 * @author jpaulgibson
 * @version 1
 */
public class InvariantBrokenException extends RuntimeException {

private static final long serialVersionUID = 1L;

/**
   * Constructor of the exception raised when invariants are broken
   * @param message is used to explain which part of the invariant has been broken
   */
  public InvariantBrokenException(String message) {
    super(message);
  }
}