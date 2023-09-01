package db;

import com.daanta.camp.Main;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@EnableConfigurationProperties
@SpringBootTest(classes = Main.class)
public class DBConnectionVerifyTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String id;

    @Value("${spring.datasource.password}")
    private String pw;

    @Test
    void verifyProperties() {
        log.error("adfadfadf?");
        log.debug("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        log.error("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        log.error("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        log.error("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        log.error("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        log.error("\nDatabase url: {}\nid: {}\npw: {}", url, id, pw);
        Assertions.assertNotNull(url);
        Assertions.assertNotNull(id);
        Assertions.assertNotNull(pw);
    }

}
