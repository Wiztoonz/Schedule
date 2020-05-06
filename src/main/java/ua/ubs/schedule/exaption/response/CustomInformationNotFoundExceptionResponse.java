package ua.ubs.schedule.exaption.response;

public class CustomInformationNotFoundExceptionResponse {

    private String message;

    public CustomInformationNotFoundExceptionResponse() {
    }

    public CustomInformationNotFoundExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
