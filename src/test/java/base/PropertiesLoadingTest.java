package base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/application.yml")
public class PropertiesLoadingTest {

    @Value("${spring.datasource.url}")
    private String url;

    // FAILED. I can't find out why. I would skip this.
    @Test
    public void propsVerification() {
        log.debug("테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트");
        log.debug("{}", url);
        log.debug("테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트");
    }

}
