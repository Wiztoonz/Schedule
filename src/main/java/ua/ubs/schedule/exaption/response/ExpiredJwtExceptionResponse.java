package ua.ubs.schedule.exaption.response;

public class ExpiredJwtExceptionResponse {

    private String message;

    public ExpiredJwtExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
