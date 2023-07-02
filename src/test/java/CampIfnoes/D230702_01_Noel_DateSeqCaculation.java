package CampIfnoes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Slf4j
public class D230702_01_Noel_DateSeqCaculation {

    public int calc(int org) {
        String dateString = "20230702";

        LocalDate date = LocalDate.parse(dateString, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);

        LocalDate resultDate = date.plusDays(org);
        int year = resultDate.getYear();

        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        int daysDifference = (int) firstDayOfYear.until(resultDate).getDays();

        System.out.println(org + "(of the year " + year + ") = " + (daysDifference + 1) + "");

        return daysDifference;
    }

    @Test
    public void test1() {
        String dayStr = "296";
        int day = Integer.valueOf(dayStr);



    }

}
