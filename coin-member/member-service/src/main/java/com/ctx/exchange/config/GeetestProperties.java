package com.ctx.exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "geetes")
public class GeetestProperties {
    /**
     * 极验的ID
     */
    private String geetestId ;

    /**
     * 极验的key
     */
    private String geetestKey ;
}
