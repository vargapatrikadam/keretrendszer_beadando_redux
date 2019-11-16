package gamestore.exceptions;

public class NoMatchingId extends Exception {
    public NoMatchingId() {
    }

    public NoMatchingId(String message) {
        super(message);
    }
}
