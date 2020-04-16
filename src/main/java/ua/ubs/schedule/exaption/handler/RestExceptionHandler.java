package ua.ubs.schedule.exaption.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.exaption.response.BadCredentialsExceptionResponse;
import ua.ubs.schedule.exaption.response.FieldNotValidErrorResponse;
import ua.ubs.schedule.exaption.response.InformationNotFoundExceptionResponse;
import ua.ubs.schedule.exaption.response.RequestParameterNotFoundResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> details = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e ->
                details.add(e.getField() + ": " + e.getDefaultMessage())
        );
        Collections.sort(details);
        FieldNotValidErrorResponse errorResponse = new FieldNotValidErrorResponse("Validation Failed", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        RequestParameterNotFoundResponse errorResponse = new RequestParameterNotFoundResponse();
        errorResponse.setMessage("Missing some request parameters.");
        errorResponse.setHttpStatus(status.name());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InformationNotFoundException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public InformationNotFoundExceptionResponse handleInformationNotFoundException(InformationNotFoundException exception) {
        InformationNotFoundExceptionResponse errorResponse = new InformationNotFoundExceptionResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        return errorResponse;
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public BadCredentialsExceptionResponse handleBadCredentialsException(BadCredentialsException exception) {
        return new BadCredentialsExceptionResponse(exception.getMessage(), HttpStatus.FORBIDDEN.name());
    }


}