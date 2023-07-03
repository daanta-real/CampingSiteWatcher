package CampIfnoes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Slf4j
public class D230702_01_Noel_DateSeqCaculation {

    public int calc(String orgDateStr, String trgDateStr, String orgSeqStr) {

        LocalDate orgDate = LocalDate.parse(orgDateStr, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        log.debug("DATE: {}", orgDate);

        int orgSeq = Integer.parseInt(orgSeqStr);
        LocalDate resultDate = orgDate.plusDays(orgSeq);
        int year = resultDate.getYear();
        int month = resultDate.getMonthValue();
        int day = resultDate.getDayOfMonth();
        log.debug("[ORG] Y:{} / M: {} / D: {}", year, month, day);

        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        int daysDiff = firstDayOfYear.until(resultDate).getDays();
        log.debug("결과 일자 차이: {}", daysDiff);

        int result = orgSeq + daysDiff;
        log.debug("{}일의 일련번호가 {}입니까? 그럼 {}일의 일련번호는 {}으로 예상됩니다.",
                orgDateStr, orgSeq, trgDateStr, result);
        System.out.println(orgSeq + "번째 날은 " + year + "년의 " + (daysDiff + 1) + "번째 날입니다.");

        return result;

    }

    @Test
    public void test1() {
        String dateOrg = "20230702";
        String dateTarget = "20230702";
        String currSeq = "296";
        int result = calc(dateOrg, dateTarget, currSeq);
        log.debug("결과 반환값: {}", result);

    }

}
