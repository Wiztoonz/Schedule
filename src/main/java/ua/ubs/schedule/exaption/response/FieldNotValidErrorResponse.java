package ua.ubs.schedule.exaption.response;

import java.util.List;

public class FieldNotValidErrorResponse {

    private String message;
    private List<String> details;

    public FieldNotValidErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
