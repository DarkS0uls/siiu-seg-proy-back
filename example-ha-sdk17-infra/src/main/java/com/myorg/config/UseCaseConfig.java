package com.myorg.config;

import com.myorg.service.time.TimeManagerService;
import com.myorg.service.users.UsersMangerService;
import com.myorg.service.uuid.UuidManagerService;
import com.myorg.usecase.ExampleUseCase;
import com.myorg.usecase.niveles.getallniveles.GetAllNivelesUseCase;
import com.myorg.usecase.users.create.CreateUserUseCase;
import com.myorg.usecase.users.getall.GetAllUsersUseCase;
import com.myorg.usecase.users.getbyid.GetUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ExampleUseCase exampleUseCase(final TimeManagerService timeManagerService) {
        return new ExampleUseCase(timeManagerService);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(
            final UsersMangerService usersMangerService,
            final TimeManagerService timeManagerService,
            final UuidManagerService uuidManagerService) {

        return new CreateUserUseCase(usersMangerService, timeManagerService, uuidManagerService);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(final UsersMangerService usersMangerService) {
        return new GetUserByIdUseCase(usersMangerService);
    }

    @Bean
    public GetAllUsersUseCase getAllUsersUseCase(
            final TimeManagerService timeManagerService,
            final UsersMangerService usersMangerService) {
        return new GetAllUsersUseCase(timeManagerService, usersMangerService);
    }
    //Niveles
    @Bean
    public GetAllNivelesUseCase getAllNivelesUseCase() {
        return new GetAllNivelesUseCase();
    }


}
