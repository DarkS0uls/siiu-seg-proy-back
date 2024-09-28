package com.myorg.handler;

import com.myorg.adapter.in.error.ErrorResponse;
import com.myorg.adapter.in.util.GenericResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@NoArgsConstructor
@Slf4j
public class ResponseBuilder {

    public ResponseEntity<GenericResponse> mapResponseBuilder(GenericResponse genericResponse, HttpStatus successCode) {
        log.info("ResponseBuilder.mapResponseBuilder, response: {}", genericResponse);
        if (genericResponse instanceof ErrorResponse) {
            log.info("Error Instance");
            ErrorResponse response = Optional.of((ErrorResponse) genericResponse).get();
            return new ResponseEntity<GenericResponse>(response, HttpStatus.valueOf(response.getHeaders().getHttpStatusCode()));
        } else {
            log.info("Generic Success Instance");
            return new ResponseEntity<GenericResponse>(genericResponse, successCode);
        }
    }


}