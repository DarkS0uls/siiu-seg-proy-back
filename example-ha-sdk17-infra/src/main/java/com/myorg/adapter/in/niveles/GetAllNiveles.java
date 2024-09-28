package com.myorg.adapter.in.niveles;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.niveles.getallniveles.GetAllNivelesHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
@Validated
public class GetAllNiveles {

    private final GetAllNivelesHandler handler;

    @GetMapping("/niveles")
    public Mono<ResponseEntity<GenericResponse>> getNiveles(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid") String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId,
            @RequestParam(value = "pageNumber", required = true)
            Integer pageNumber,
            @RequestParam(value = "pageSize", required = true)
            @NotNull(message = "pageSize cannot be null")
            Integer pageSize
    ) {
       return handler.execute(messageUuid, requestAppId, pageNumber, pageSize);
    }
}
