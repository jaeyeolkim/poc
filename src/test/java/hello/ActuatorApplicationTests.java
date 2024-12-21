package hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void cache() {
        String hello = restTemplate.getForObject("/hello", String.class);
        Assertions.assertThat(hello).contains("Hello World");

    }

}
