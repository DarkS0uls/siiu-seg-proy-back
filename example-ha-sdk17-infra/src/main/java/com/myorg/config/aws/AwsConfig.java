package com.myorg.config.aws;

import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.sts.StsClient;


@Configuration
@Slf4j
public class AwsConfig {

    @Value("${cloud.aws.region.static}")
    private Region region;

    @Bean
    public SsmClient ssmClient(){
        log.info("Ssm Client Region:{} ", region.toString());
        return SsmClient.builder()
                .region(region)
                .build();
    }
    @Bean
    public StsClient stsClient(){
        log.info("Sts Client Region:{} ", region.toString());
        return StsClient
                .builder()
                .region(region)
                .build();
    }

}
