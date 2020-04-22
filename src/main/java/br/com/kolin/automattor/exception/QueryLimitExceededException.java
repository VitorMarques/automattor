package br.com.kolin.automattor.exception;

public class QueryLimitExceededException extends RuntimeException {

    private static final long serialVersionUID = -7280209225723855022L;

    public QueryLimitExceededException() {
    }

    public QueryLimitExceededException(String message) {
        super(message);
    }
}
