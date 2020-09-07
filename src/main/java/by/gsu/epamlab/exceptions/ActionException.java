package by.gsu.epamlab.exceptions;

public class ActionException extends Exception {

    private String exceptionPage;

    public ActionException(String message, Throwable cause, String exceptionPage) {
        super(message, cause);
        this.exceptionPage = exceptionPage;
    }

    public ActionException(String message, String exceptionPage) {
        super(message);
        this.exceptionPage = exceptionPage;
    }

    public ActionException(Throwable cause, String exceptionPage) {
        super(cause);
        this.exceptionPage = exceptionPage;
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }

    public String getExceptionPage() {
        return exceptionPage;
    }
}
