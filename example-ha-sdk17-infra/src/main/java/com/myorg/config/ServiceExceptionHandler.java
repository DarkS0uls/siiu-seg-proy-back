package com.myorg.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myorg.adapter.in.error.ErrorResponse;
import com.myorg.adapter.in.error.ErrorResponseError;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.service.time.TimeManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private final TimeManagerService timeManagerService;
    @Autowired
    private HttpServletRequest servletRequest;
    private static final String MESSAGE_UUID = "message-uuid";
    private static final String REQUEST_APP_ID = "request-app-id";

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.FIELD_ID_NOT_NULL,
                handleNoResourceFoundExceptionlist(ex));
    }

    private List<ErrorResponseError> handleNoResourceFoundExceptionlist(NoResourceFoundException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(String.valueOf(HttpStatus.BAD_REQUEST.name()))
                .build());
        return responseErrors;

    }

    //--------------------------------------------------------------------------------------------------------------------

    @ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<Object> jsonProcessingException(
            JsonProcessingException ex) {
        return responseBuilder(HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                simpleErrorToList(ex.getCause().toString(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ArgumentConversionException.class)
    protected ResponseEntity<Object> argumentConversionException(
            ArgumentConversionException ex) {
        return responseBuilder(HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                simpleErrorToList(ex.getCause().toString(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> numericViolationException(
            MethodArgumentTypeMismatchException ex) {
        return responseBuilder(HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                simpleErrorToList(ex.getCause().toString(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> constrainViolationException(
            ConstraintViolationException ex) {
        log.info("ConstraintViolationException: {}", ex.getMessage());
        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                constrainErrorToList(ex.getConstraintViolations(), HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException argumentException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                argumentErrorToList(argumentException, status));
    }


    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException bindingException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS, bindingErrorTolist(bindingException));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS, noReadableErrorToList(ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS, noHandlerFountErrorToList(ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return responseBuilder(
                HttpStatus.METHOD_NOT_ALLOWED,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS, handlerHttpMethodNotSupportedToList(ex));

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return responseBuilder(
                HttpStatus.BAD_REQUEST,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.INCONSISTENCIA_DATOS,
                handleMissingRequestParameterList(ex));

    }

    private String getHeader(String headerName) {
        try {
            return servletRequest.getHeader(headerName);
        } catch (Exception e) {
            log.info("Some error in get headers: {}", e.getMessage());
        }
        return null;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return responseBuilder(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                getHeader(MESSAGE_UUID),
                getHeader(REQUEST_APP_ID),
                GenericResponseCodes.UNSOPORTED_MEDIA_TYPE,
                httpMediaTypeNotSupportedErrorTolist(ex));

    }


    private List<ErrorResponseError> httpMediaTypeNotSupportedErrorTolist(HttpMediaTypeNotSupportedException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;

    }

    private List<ErrorResponseError> handleMissingRequestParameterList(MissingServletRequestParameterException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private List<ErrorResponseError> handlerHttpMethodNotSupportedToList(HttpRequestMethodNotSupportedException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;

    }

    private List<ErrorResponseError> noHandlerFountErrorToList(NoHandlerFoundException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private List<ErrorResponseError> noReadableErrorToList(HttpMessageNotReadableException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private List<ErrorResponseError> bindingErrorTolist(ServletRequestBindingException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;

    }

    private List<ErrorResponseError> constrainErrorToList(Set<ConstraintViolation<?>> violations, HttpStatus status) {
        return violations
                .stream()
                .map(violation -> ErrorResponseError
                        .builder()
                        .errorCode(String.valueOf(status.value()))
                        .errorDetail(violation.getMessage())
                        .build())
                .toList();
    }


    private List<ErrorResponseError> argumentErrorToList(MethodArgumentNotValidException ex, HttpStatusCode status) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(defaultMessageSourceResolvable -> ErrorResponseError
                        .builder()
                        .errorCode(String.valueOf(status.value()))
                        .errorDetail(defaultMessageSourceResolvable.getDefaultMessage())
                        .build())
                .toList();
    }


    protected List<ErrorResponseError> simpleErrorToList(String errorMessage, HttpStatus httpStatus) {
        return new ArrayList<>(
                Arrays.asList(ErrorResponseError
                        .builder()
                        .errorCode(String.valueOf(httpStatus.value()))
                        .errorDetail(errorMessage)
                        .build()));
    }

    protected ResponseEntity<Object> responseBuilder(HttpStatus status, String messageUuid, String requestAppId, GenericResponseCodes genericCode, List<ErrorResponseError> errorList) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .headers(
                                HeaderObjectResponse
                                        .builder()
                                        .messageUuid(messageUuid)
                                        .requestAppId(requestAppId)
                                        .httpStatusCode(status.value())
                                        .httpStatusDesc(status.name())
                                        .requestDatetime(timeManagerService.getInstantIsoFormat())
                                        .build()
                        )
                        .messageResponse(
                                MessageObjectResponse
                                        .builder()
                                        .responseCode(genericCode.getValue())
                                        .responseMessage(genericCode.getDescription())
                                        .responseDetails(status.getReasonPhrase())
                                        .build()
                        )
                        .errors(
                                errorList
                        )
                        .build()
                , status);
    }


}