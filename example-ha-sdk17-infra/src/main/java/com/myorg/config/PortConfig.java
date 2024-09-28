package com.myorg.config;


import com.myorg.adapter.out.dynamo.TransactionAdapter;
import com.myorg.adapter.out.postgres.users.UsersAdapter;
import com.myorg.adapter.out.redis.RedisAdapter;
import com.myorg.ports.RedisPort;
import com.myorg.ports.TransactionPort;
import com.myorg.ports.UsersPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Configuration
@Component
public class PortConfig {

    @Bean
    public UsersPort getUserPort() {
        return new UsersAdapter();
    }

    @Bean
    public TransactionPort getTransactionPort(final DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return new TransactionAdapter(dynamoDbEnhancedClient);
    }

    @Bean
    public RedisPort getRedisPort() {
        return new RedisAdapter();
    }

}
