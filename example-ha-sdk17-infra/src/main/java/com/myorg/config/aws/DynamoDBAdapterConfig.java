package com.myorg.config.aws;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * Configuration Class to provide DynamoDb Client
 */
@Configuration
@Slf4j
@Import({AwsConfig.class})
public class DynamoDBAdapterConfig {

    @Value("${cloud.aws.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${cloud.aws.region.static}")
    private Region amazonRegion;

    @Value("${cloud.aws.accounts.digital.dynamodb-role}")
    private String dynamoRoleArn;

    @Value("${cloud.aws.accounts.digital.dynamodb-session-name}")
    private String dynamoRoleSessionName;


    //@Autowired
    //private AwsStsAdapter awsStsAdapter;

    @Bean
    public DynamoDbClient amazonDynamoDB() {

        DynamoDbClient dynamoDbClient =
                DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .build();
        return dynamoDbClient;
        /*
        DynamoDbClient clientResponse;
        final StsAssumeRoleCredentialsProvider credentials = awsStsAdapter.getCredentialsProvider(dynamoRoleArn, dynamoRoleSessionName);
        try {

            if (credentials.resolveCredentials() != null) {
                clientResponse = DynamoDbClient
                        .builder()
                        .credentialsProvider(credentials)
                        .endpointOverride(URI.create(amazonDynamoDBEndpoint))
                        .region(amazonRegion)
                        .build();
                log.info("Credentials Was set!!");
                log.info("Credentials: {}", credentials.resolveCredentials().accessKeyId());
                log.info("Credentials: {}", credentials.resolveCredentials().secretAccessKey());
            } else {
                log.info("Credentials not set.");
                clientResponse = DynamoDbClient.builder()
                        .endpointOverride(URI.create(amazonDynamoDBEndpoint))
                        .region(amazonRegion)
                        .build();
            }

        } catch (Exception e) {
            log.error("Error Getting credentials: {}", e.getMessage());
            clientResponse = DynamoDbClient.builder()
                    .endpointOverride(URI.create(amazonDynamoDBEndpoint))
                    .region(amazonRegion)
                    .build();
        }
        return clientResponse;
        */
    }


    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(final DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }


}
