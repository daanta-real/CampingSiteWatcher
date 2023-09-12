package db;

import com.daanta.camp.Main;
import com.daanta.camp.dao.SiteDAO;
import com.daanta.camp.domain.Site;
import domain.SiteTest;
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

    @Test
    public void insertOne() {

        // Make a new instance of Site
        Site nanji = SiteTest.makeSiteNanji3();

        // Set a new seq.key
        int idx = siteDAO.selectKey();
        nanji.setIdx(idx);

        // Confirm
        log.debug("A SITE INSTANCE:\n\n{}\n", nanji);

        // Add a record to site table
        siteDAO.insertOne(nanji);

    }

    /* I'm gonna test later because making other tables are not completed */
    @Test
    public void selectList() {
        ;
    }

}
