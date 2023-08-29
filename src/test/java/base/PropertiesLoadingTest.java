package base;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = com.daanta.camp.Main.class)
public class PropertiesLoadingTest {

    @NonNull
    private final String nanji_type;

    // Null 방지 때문에 울며 겨자먹기로 넣었다
    @Autowired
    public PropertiesLoadingTest(@NonNull String nanjiType) {
        this.nanji_type = nanjiType;
    }

    @Test
    public void propsVerification() {
        log.debug("{}", nanji_type);
    }

}
