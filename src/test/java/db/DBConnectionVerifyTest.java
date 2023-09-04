package db;

import com.daanta.camp.Main;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@EnableConfigurationProperties
@SpringBootTest(classes = Main.class)
public class DBConnectionVerifyTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void dbConnection() {
        jdbcTemplate.execute("SELECT '으엑으엑으엑으엑'"); // Replace with an appropriate SQL query for your database
    }

    @Test
    public void makeTable() {
        jdbcTemplate.execute("CREATE TABLE "); // Replace with an appropriate SQL query for your database
    }

    @Test
    public void dropTable() {
        jdbcTemplate.execute("SELECT '으엑으엑으엑으엑'"); // Replace with an appropriate SQL query for your database
    }

}
