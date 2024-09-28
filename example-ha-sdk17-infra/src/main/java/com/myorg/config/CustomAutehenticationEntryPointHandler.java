package com.myorg.config;

import com.myorg.adapter.in.error.ErrorResponse;
import com.myorg.adapter.in.error.ErrorResponseError;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.service.time.TimeManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class CustomAutehenticationEntryPointHandler implements AuthenticationEntryPoint {

    public static final Logger LOG = Logger.getLogger(CustomAutehenticationEntryPointHandler.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse responseBody = responseBuilder(HttpStatus.UNAUTHORIZED, accesDeniedErrorToList(authException));
        writeCustomResponse(response, responseBody, authException);
    }

    private void writeCustomResponse(HttpServletResponse response, ErrorResponse error, AuthenticationException authException) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(error));
            } catch (IOException e) {
                throw authException;
            }
        }
    }

    private List<ErrorResponseError> accesDeniedErrorToList(AuthenticationException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private ErrorResponse responseBuilder(HttpStatus status, List<ErrorResponseError> errorList) {
        return ErrorResponse
                .builder()
                .headers(
                        HeaderObjectResponse
                                .builder()
                                .httpStatusCode(status.value())
                                .httpStatusDesc(status.getReasonPhrase())
                                .requestDatetime(new TimeManagerService().getInstantIsoFormat())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(GenericResponseCodes.TRANSACCION_FALLIDA.getValue())
                                .responseMessage(GenericResponseCodes.TRANSACCION_FALLIDA.getDescription())
                                .responseDetails("UNAUTHORIZED")
                                .build()
                )
                .errors(
                        errorList
                )
                .build();
    }


}