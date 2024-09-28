package com.myorg.adapter.in.niveles;

import com.myorg.adapter.in.util.GenericResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*") //Review CORS and XHR
@RequiredArgsConstructor
@Slf4j
@Validated
public class GetNivelById {

    @GetMapping("/niveles/{id}")
    public Mono<ResponseEntity<GenericResponse>> getNivelById(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid") String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId,
            @PathVariable(value = "id") String id
    ) {
        return null;
    }
}
