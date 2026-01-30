package ics202;

/**
 * Thrown when a method is not implemented (e.g. placeholder for student code).
 * Prefer {@link UnsupportedOperationException} for new code.
 */
public class MethodNotImplemented extends UnsupportedOperationException {

    public MethodNotImplemented() {
        super("Method not implemented");
    }

    public MethodNotImplemented(String message) {
        super(message);
    }
}
