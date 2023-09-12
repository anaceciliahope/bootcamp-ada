package tech.ada.avanade.bootcampada.exception;

public class AvanadeException extends RuntimeException {


    public AvanadeException() {
    }

    public AvanadeException(String message) {
        super(message);
    }

    public AvanadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AvanadeException(Throwable cause) {
        super(cause);
    }

    public AvanadeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
