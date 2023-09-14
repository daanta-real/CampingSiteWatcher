package db;

import com.daanta.camp.Main;
import com.daanta.camp.domain.Site;
import com.daanta.camp.domain.SiteNanji;
import com.daanta.camp.service.SiteService;
import domain.SiteTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = Main.class)
public class SiteServiceTest {

    @Autowired
    SiteService siteService;

    @Test
    public void selectKey() {
        int result = siteService.selectKey();
        log.debug("I GOT THE NEW SEQ KEY:\n\n{}\n", result);
    }

    @Test
    public void insertOne() {

        // Make a new instance of Site
        Site nanji = SiteTest.makeSiteNanji3();

        // Set a new seq.key
        int idx = siteService.selectKey();
        nanji.setIdx(idx);

        // Confirm
        log.debug("A SITE INSTANCE:\n\n{}\n", nanji);

        // Add a record to site table
        siteService.insertOne(nanji);

    }

    @Test
    public void selectList_noSearch() {
        Site siteSearch = SiteNanji.builder()
                .type("nanji")
                .build();
        List<Site> result = siteService.selectList(siteSearch);
        log.debug("Result List: {}", result);
    }

    /* I'm gonna test later because making other tables are not completed */
    @Test
    public void selectList_search() {

    }

}
