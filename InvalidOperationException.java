package ics202;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException() {
    }

    public InvalidOperationException(String s) {
		super(s);
    }
}
