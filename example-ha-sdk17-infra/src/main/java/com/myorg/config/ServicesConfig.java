package com.myorg.config;

import com.myorg.ports.UsersPort;
import com.myorg.service.time.TimeManagerService;
import com.myorg.service.users.UsersMangerService;
import com.myorg.service.uuid.UuidManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public TimeManagerService getTimeManagerService() {
        return new TimeManagerService();
    }

    @Bean
    public UuidManagerService idService() {
        return new UuidManagerService();
    }

    @Bean
    public UsersMangerService usersMangerService(final UsersPort usersPort) {
        return new UsersMangerService(usersPort);
    }


}
