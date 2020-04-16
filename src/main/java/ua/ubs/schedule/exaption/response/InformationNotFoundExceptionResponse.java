package ua.ubs.schedule.exaption.response;

public class InformationNotFoundExceptionResponse {

    private String message;
    private String HttpStatus;

    public InformationNotFoundExceptionResponse() {
    }

    public InformationNotFoundExceptionResponse(String message, String httpStatus) {
        this.message = message;
        HttpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        HttpStatus = httpStatus;
    }
}
