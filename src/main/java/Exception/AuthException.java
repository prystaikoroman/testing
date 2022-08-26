package Exception;

public class AuthException extends Throwable  {
    public AuthException() {
        super();
    }

    public AuthException(Throwable cause, String reason) {
        super(reason, cause);

    }

    public AuthException(String reason) {
        super(reason);

    }

}
