package io.github.cocodx.util;

import io.minio.MinioClient;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author amazfit
 * @date 2022-07-02 下午5:32
 * 注入 minioClient
 **/
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(HttpUrl.parse(minioProperties.getEndPoint()))
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return minioClient;
    }
}
