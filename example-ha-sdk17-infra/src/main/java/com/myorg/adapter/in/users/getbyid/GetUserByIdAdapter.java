package com.myorg.adapter.in.users.getbyid;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.users.getbyid.GetUserByIdHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") //Review CORS and XHR
@RequiredArgsConstructor
@Slf4j
@Validated
public class GetUserByIdAdapter {

    private final GetUserByIdHandler handler;

    @GetMapping("/users/{uuid}")
    public Mono<ResponseEntity<GenericResponse>> getUserById(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid") String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId,
            @Valid
            @PathVariable(value = "uuid") String uuid) {
        return handler.execute(messageUuid, requestAppId, uuid);
    }

}
