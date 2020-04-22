package br.com.kolin.automattor.exception;

public class NoResultsForSearchException extends RuntimeException {

    private static final long serialVersionUID = -7280209225723855022L;

    public NoResultsForSearchException() {
    }

    public NoResultsForSearchException(String message) {
        super(message);
    }
}
