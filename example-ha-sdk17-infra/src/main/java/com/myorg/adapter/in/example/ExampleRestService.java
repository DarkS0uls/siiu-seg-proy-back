package com.myorg.adapter.in.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.out.redis.RedisAdapter;
import com.myorg.handler.example.ExampleHandler;
import com.myorg.kernel.domain.out.dynamo.TransactionDto;
import com.myorg.kernel.domain.out.redis.RedisDto;
import com.myorg.ports.RedisPort;
import com.myorg.ports.TransactionPort;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/test")
@CrossOrigin(origins = "*") //Review CORS and XHR
@RequiredArgsConstructor
@Slf4j
@Validated
public class ExampleRestService {
    // Current Service Path :/example

    private final ExampleHandler handler;

    private final TransactionPort transactionPort;

    private final RedisPort redisPort;

    @GetMapping("/get-iso-date")
    public Mono<ResponseEntity<String>> getIsoDate(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid")  String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId
    ) {
        log.info("ExampleRestService.getIsoDate, get iso date");
        //proceso temporal
        try {
          //Cosntruimos el objeto a crear
            RedisDto redisDto = RedisDto.builder()
                    .id("12345")
                    .name("Kevin")
                    .email("kevin@gmail.com")
                    .build();
            //Guardamos el objeto en redis
            RedisDto response= redisPort.save(redisDto).block();

            //Obtenemos el objeto guardado en redis
            RedisDto response2= redisPort.findById("12345").block();

            log.info("ExampleRestService.getIsoDate, response: {}", new ObjectMapper().writeValueAsString(response2));

        }catch (Exception e){
          log.error("Error in ExampleRestService.getIsoDate, error: {}", e.getMessage());
        }
        // end proceso temporal
        //return handler.execute(messageUuid, requestAppId);
        return Mono.just(ResponseEntity.ok("Hello World"));

    }
}
