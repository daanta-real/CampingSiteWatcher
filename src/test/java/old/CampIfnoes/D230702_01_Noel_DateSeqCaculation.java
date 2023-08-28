package old.CampIfnoes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
public class D230702_01_Noel_DateSeqCaculation {

    String orgSeqStr = "296";

    public int calc(String orgDateStr, String trgDateStr, String orgSeqStr) {

        LocalDate orgDate = LocalDate.parse(orgDateStr, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        log.debug("ORGDATE LocalDate: {}", orgDate);

        LocalDate trgDate = LocalDate.parse(trgDateStr, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        log.debug("TRGDATE LocalDate: {}", trgDate);

        long dateDiff = ChronoUnit.DAYS.between(orgDate, trgDate);
        log.debug("DATE DIFF RESULT OF TRG - ORG = {} - {} = {}", trgDate, orgDate, dateDiff);

        int orgSeq = Integer.parseInt(orgSeqStr);
        int trgSeq = orgSeq + (int)dateDiff;
        log.debug("DATE DIFF CALC: {}, {}, {}", orgSeq, dateDiff, trgSeq);

        log.debug("{}일의 일련번호가 {}입니까? 그럼 {}일의 일련번호는 {}으로 예상됩니다.",
                orgDateStr, orgSeq, trgDateStr, trgSeq);

        return trgSeq;

    }

    // TODO
    public int calc_autoParam(String orgDateStr, String trgDateStr) {

        LocalDate orgDate = LocalDate.parse(orgDateStr, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        log.debug("ORGDATE LocalDate: {}", orgDate);

        LocalDate trgDate = LocalDate.parse(trgDateStr, java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        log.debug("TRGDATE LocalDate: {}", trgDate);

        long dateDiff = ChronoUnit.DAYS.between(orgDate, trgDate);
        log.debug("DATE DIFF RESULT OF TRG - ORG = {} - {} = {}", trgDate, orgDate, dateDiff);

        int orgSeq = Integer.parseInt(orgSeqStr);
        int trgSeq = orgSeq + (int)dateDiff;
        log.debug("DATE DIFF CALC: {}, {}, {}", orgSeq, dateDiff, trgSeq);

        log.debug("{}일의 일련번호가 {}입니까? 그럼 {}일의 일련번호는 {}으로 예상됩니다.",
                orgDateStr, orgSeq, trgDateStr, trgSeq);

        return trgSeq;

    }

    // Re-arranged for productional use
    public int calcArranged() {
        return 0;
    }

    @Test
    public void test1() {
        String dateOrg = "20230702";
        String dateTarget = "20230715";
        String currSeq = "296";
        int result = calc(dateOrg, dateTarget, currSeq);
        log.debug("결과 반환값: {}", result);
    }

}
