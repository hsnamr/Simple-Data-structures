package ics202;

public class ContainerEmptyException extends DomainException {

    public ContainerEmptyException() {
    }

    public ContainerEmptyException(String message) {
        super(message);
    }
}
