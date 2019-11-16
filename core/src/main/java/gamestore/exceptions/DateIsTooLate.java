package gamestore.exceptions;

public class DateIsTooLate extends Exception{
    public DateIsTooLate() {
    }

    public DateIsTooLate(String message) {
        super(message);
    }
}
