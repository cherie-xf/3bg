package ca.esystem.bridges.exception;

public class DuplicateDataException extends RuntimeException {
    private static final long serialVersionUID = -787475829792558517L;

    public DuplicateDataException(String message) {
        super(message);
    }

    public DuplicateDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
