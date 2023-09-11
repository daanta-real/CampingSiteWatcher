package db;

import com.daanta.camp.Main;
import com.daanta.camp.dao.SiteDAO;
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
        log.debug("키얻었다: {}", siteDAO.selectKey());
    }

}
