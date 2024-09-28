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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG = Logger.getLogger(CustomAccessDeniedHandler.class.getName());


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse responseBody = responseBuilder(HttpStatus.FORBIDDEN, accesDeniedErrorToList(accessDeniedException));
        writeCustomResponse(response, responseBody);

    }

    private void writeCustomResponse(HttpServletResponse response, ErrorResponse error) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(error));
            } catch (IOException e) {
                throw new AccessDeniedException(e.getMessage());
            }
        }
    }


    private List<ErrorResponseError> accesDeniedErrorToList(AccessDeniedException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.FORBIDDEN.value()))
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
                                .httpStatusDesc(status.name())
                                .requestDatetime(new TimeManagerService().getInstantIsoFormat())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(GenericResponseCodes.PERMISO_DENEGADO.getValue())
                                .responseMessage(GenericResponseCodes.PERMISO_DENEGADO.getDescription())
                                .responseDetails(status.getReasonPhrase())
                                .build()
                )
                .errors(
                        errorList
                )
                .build();
    }

}