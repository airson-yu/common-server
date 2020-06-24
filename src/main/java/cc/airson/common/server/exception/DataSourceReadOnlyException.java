package cc.airson.common.server.exception;

public class DataSourceReadOnlyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataSourceReadOnlyException() {
        super();
    }

    public DataSourceReadOnlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceReadOnlyException(String message) {
        super(message);
    }

    public DataSourceReadOnlyException(Throwable cause) {
        super(cause);
    }

}
