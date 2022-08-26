package Exception;

public class DBException extends Throwable  {
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
