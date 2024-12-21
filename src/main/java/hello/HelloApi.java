package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloApi {

    @Cacheable("hello")
    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello World";
    }

}