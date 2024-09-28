package com.myorg.config;

import com.myorg.handler.example.ExampleHandler;
import com.myorg.handler.niveles.getallniveles.GetAllNivelesHandler;
import com.myorg.handler.users.create.CreateUserHandler;
import com.myorg.handler.users.getall.GetAllUsersHandler;
import com.myorg.handler.users.getbyid.GetUserByIdHandler;
import com.myorg.usecase.ExampleUseCase;
import com.myorg.usecase.niveles.getallniveles.GetAllNivelesUseCase;
import com.myorg.usecase.users.create.CreateUserUseCase;
import com.myorg.usecase.users.getall.GetAllUsersUseCase;
import com.myorg.usecase.users.getbyid.GetUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public ExampleHandler exampleHandler(final ExampleUseCase useCase) {
        return new ExampleHandler(useCase);
    }

    @Bean
    public CreateUserHandler createUserHandler(final CreateUserUseCase useCase) {
        return new CreateUserHandler(useCase);
    }

    @Bean
    public GetUserByIdHandler getUserByIdHandler(final GetUserByIdUseCase useCase) {
        return new GetUserByIdHandler(useCase);
    }

    @Bean
    public GetAllUsersHandler getAllUsersHandler(final GetAllUsersUseCase useCase) {
        return new GetAllUsersHandler(useCase);
    }
    //Niveles
    @Bean
    public GetAllNivelesHandler getAllNivelesHandler(final GetAllNivelesUseCase useCase) {
        return new GetAllNivelesHandler(useCase);
    }

}
