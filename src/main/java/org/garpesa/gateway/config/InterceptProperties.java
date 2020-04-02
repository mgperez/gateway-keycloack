package org.garpesa.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * https://tuhrig.de/using-configurationproperties-to-separate-service-and-configuration
 * https://github.com/konrad-garus/so-yaml/blob/master/src/main/java/io/example/AvailableChannelsConfiguration.java
 *
 *
 * intercept.url.pattern=/api/**
 * intercept.url.active=true
 */
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="intercept-url")
public class InterceptProperties {
    private List<Url> urls;

    @Data
    public static class Url {
        @NotNull private String pattern;
        @NotNull private boolean access; // default!
    }
}