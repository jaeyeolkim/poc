package hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {

    @Test
    void queryTest() throws URISyntaxException {
        String url = "http://example.com/path?param1=value1&param2=value2&param3=value3";
        Map<String, String> queryParams = parseQueryString(url);

        // 파싱 결과 출력
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        assertThat(queryParams.get("param1")).isEqualTo("value1");
    }

    public static Map<String, String> parseQueryString(String urlString) throws URISyntaxException {
        URI uri = new URI(urlString);
        String query = uri.getQuery();
        Map<String, String> queryPairs = new HashMap<>();

        if (query == null) {
            return queryPairs;
        }

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            queryPairs.put(key, value);
        }

        return queryPairs;
    }
}
