package db;

import com.daanta.camp.Main;
import com.daanta.camp.dao.SiteDAO;
import com.daanta.camp.domain.Site;
import com.daanta.camp.domain.SiteNanji;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = Main.class)
public class SiteDAOTest {

    @Autowired
    SiteDAO siteDAO;

    @Test
    public void selectKey() {
        int result = siteDAO.selectKey();
        log.debug("I GOT THE NEW SEQ KEY:\n\n{}\n", result);
    }

    /* I'm gonna test later because making other tables are not completed */
    @Test
    public void selectList() {
        ;
    }

    @Test
    public void insertOne() {
        Site nanji = SiteNanji.builder()
                .url("url")
                .header(null)
                .formBody(null)
                .query(null)
                .build();
        log.debug("nanji: {}", nanji);
    }

}
