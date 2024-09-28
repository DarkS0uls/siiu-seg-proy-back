package com.myorg.adapter.in.users.getall;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.constrains.ValueOfDatetime;
import com.myorg.adapter.in.util.constrains.ValueOfEnum;
import com.myorg.adapter.out.postgres.users.entity.UsersStatusEnum;
import com.myorg.handler.users.getall.GetAllUsersHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
public class GetAllUsersAdapter {

    private final GetAllUsersHandler handler;

    @GetMapping("/users")
    public Mono<ResponseEntity<GenericResponse>> getAllUsers(
            @NotEmpty(message = "message-uuid cannot be empty")
            @RequestHeader("message-uuid") String messageUuid,
            @NotEmpty(message = "request-app-id cannot be empty")
            @RequestHeader("request-app-id") String requestAppId,
            @NotNull(message = "pageNumber cannot be null")
            @RequestParam(value = "pageNumber", required = true)
            Integer pageNumber,
            @RequestParam(value = "pageSize", required = true)
            @NotNull(message = "pageSize cannot be null")
            Integer pageSize,
            @RequestParam(value = "status", required = false)
            @ValueOfEnum(enumClass = UsersStatusEnum.class, message = "status must be one of 'ACTIVE', 'INACTIVE'")
            String status,
            @RequestParam(value = "userName", required = false)
            String userName,
            @RequestParam(value = "cellphone", required = false)
            @Size(min = 13, max = 13, message = "cellphone must be 13 characters")
            String cellphone,
            @RequestParam(value = "createdDt", required = false)
            @ValueOfDatetime(message = "createdDt must be in the format yyyy-MM-ddTHH:mm:ss")
            String createdDt) {
        log.info("GetAllUsersAdapter.getAllUsers, Getting all users with pageNumber: {}, pageSize: {}, status: {}, userName: {}, cellphone: {}, createdDt: {}", pageNumber, pageSize, status, userName, cellphone, createdDt);
        return handler.execute(messageUuid, requestAppId, pageNumber, pageSize, status, userName, cellphone, createdDt);
    }


}
