package io.github.cocodx.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author amazfit
 * @date 2022-07-02 下午5:33
 * minio 属性配置
 **/
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endPoint;

    private String accessKey;

    private String secretKey;


}
