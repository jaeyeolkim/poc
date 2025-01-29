package hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloApi {
    private final HelloFeign helloFeign;
    private final HelloInternalFeign helloInternalFeign;

//    @Cacheable("hello")
    @GetMapping("/hello/external")
    public String externalHello() {
        log.info("external hello");
        return helloFeign.hello();
    }

    @GetMapping("/hello")
    public String internalHello() {
        log.info("internalHello");
        return helloInternalFeign.hello();
    }

}
