package hello;

import feign.RequestInterceptor;
import feign.Target;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Target<?> target = requestTemplate.feignTarget();
            if (AnnotationUtils.findAnnotation(target.type(), DisableHeaderValue.class) != null) {
                requestTemplate.header("x-disable-value", "ok");
            }

            requestTemplate.header("x-test", "ok");
        };
    }
}
