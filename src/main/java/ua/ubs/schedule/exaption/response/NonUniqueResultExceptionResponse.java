package ua.ubs.schedule.exaption.response;

public class NonUniqueResultExceptionResponse {

    private String message;
    private String HttpStatus;

    public NonUniqueResultExceptionResponse() {
    }

    public NonUniqueResultExceptionResponse(String message, String httpStatus) {
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

    @Override
    public String toString() {
        return "NonUniqueResultExceptionResponse{" +
                "message='" + message + '\'' +
                ", HttpStatus='" + HttpStatus + '\'' +
                '}';
    }

}
