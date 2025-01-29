package hello;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080", value = "helloFeign")
public interface HelloInternalFeign {

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    String hello();
}
