package com.scando.learning.common.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonS3 s3Client() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
        return s3Client;
    }

    @Bean
    @Primary
    public AmazonSNSClient amazonSNSClient(){

        return  (AmazonSNSClient) AmazonSNSClientBuilder.defaultClient();
    }
}
