package com.myorg.adapter.in.users.create;

import com.myorg.adapter.in.users.create.dto.CreateUserRequest;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.users.create.CreateUserHandler;
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
public class CreateUserAdapter {

    private final CreateUserHandler handler;

    @PostMapping("/users")
    public Mono<ResponseEntity<GenericResponse>> createUser(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid") String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId,
            @Valid
            @RequestBody CreateUserRequest request
    ) {


        log.info("CreateUserAdapter.createUser, create user with name: {}, and lastname:{}", request.getUserName(), request.getLastName());
        return handler.execute(messageUuid, requestAppId, request);
    }

}
