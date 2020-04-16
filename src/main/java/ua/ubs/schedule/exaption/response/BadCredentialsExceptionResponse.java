package ua.ubs.schedule.exaption.response;

public class BadCredentialsExceptionResponse {

    private String message;
    private String httpStatus;

    public BadCredentialsExceptionResponse(String message, String httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

}
