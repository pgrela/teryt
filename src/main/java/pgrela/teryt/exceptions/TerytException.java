package pgrela.teryt.exceptions;

public class TerytException extends RuntimeException {
    public TerytException() {
    }

    public TerytException(String message) {
        super(message);
    }

    public TerytException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerytException(Throwable cause) {
        super(cause);
    }

    public TerytException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
