package Exception;

public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(Throwable cause, String reason) {
        super(reason, cause);

    }

    public DBException(String reason) {
        super(reason);

    }
}
