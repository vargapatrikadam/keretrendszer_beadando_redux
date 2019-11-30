package gamestore.controllers.exceptions;

public class FilterIsMissingException extends Exception {
    public FilterIsMissingException() {
    }

    public FilterIsMissingException(String message) {
        super(message);
    }
}
