package ua.ubs.schedule.exaption.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ua.ubs.schedule.exaption.response.AccessDeniedExceptionResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class RestAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");

        String tokenInvalid = (String) httpServletRequest.getAttribute("token_invalid");
        String tokenExpired = (String) httpServletRequest.getAttribute("token_expired");
        String tokenEmpty = (String) httpServletRequest.getAttribute("token_empty");

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        AccessDeniedExceptionResponse errorResponse = new AccessDeniedExceptionResponse();

        if (tokenInvalid != null) {
            setResponse(errorResponse, tokenInvalid, badRequest, httpServletResponse);
        }
        if (tokenExpired != null) {
            setResponse(errorResponse, tokenExpired, forbidden, httpServletResponse);
        }
        if (tokenEmpty != null) {
            setResponse(errorResponse, tokenEmpty, badRequest, httpServletResponse);
        }


        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorResponse);
        out.flush();
        out.close();
    }

    private void setResponse(AccessDeniedExceptionResponse errorResponse,
                             String message,
                             HttpStatus httpStatus,
                             HttpServletResponse response) {
        errorResponse.setMessage(message);
        errorResponse.setHttpStatus(httpStatus.name());
        response.setStatus(httpStatus.value());
    }

}
