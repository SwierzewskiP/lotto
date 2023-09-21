package pl.swierzewski.domain.resultannouncer;

public class ResultResponseNotFoundException extends RuntimeException {

    ResultResponseNotFoundException(String message) {
        super(message);
    }
}
