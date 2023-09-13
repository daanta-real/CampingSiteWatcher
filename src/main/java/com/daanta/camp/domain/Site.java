package com.daanta.camp.domain;

import com.daanta.camp.utils.Utils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;



// Used for handling one Site
@Getter
@Setter
@SuperBuilder
public abstract class Site {

    // TOTAL
    private int totalCount;

    // SITE TABLE
    private int idx;
    private String nm;
    @Builder.Default protected String type = "default";
    private String url;
    private String query;
    private String lastval;
    private int regIdx;
    private Date regDt;
    private int chgIdx;
    private Date chgDt;
    private Date delDt;

    // HEADER TABLE
    private String header;

    // BODY TABLE
    private String formBody;

    // USER TABLE
    private String regUsrNm;
    private String chgUsrNm;

    // GRADE TABLE
    private String regGrdNm;
    private String chgGrdNm;

    public Site(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        Map<String, Object> m = new HashMap<>();
        m.put("idx"    , this.idx);
        m.put("nm"     , this.nm);
        m.put("type"   , this.type);
        m.put("url"    , this.url);
        m.put("query"  , this.query);
        m.put("lastval", this.lastval);
        m.put("regIdx" , this.regIdx);
        m.put("regDt"  , this.regDt);
        m.put("chgIdx" , this.chgIdx);
        m.put("chgDt"  , this.chgDt);
        m.put("delDt"  , this.delDt);
        m.put("header" , this.header);
        m.put("formBody", this.formBody);
        m.put("regUsrNm"  , this.regUsrNm);
        m.put("chgUsrNm"  , this.chgUsrNm);
        m.put("regGrdNm"  , this.regGrdNm);
        m.put("chgGrdNm"  , this.chgGrdNm);
        return Utils.getPrettyJson(m);
    }

    public abstract void get();
    public abstract void getNewValue();

}
