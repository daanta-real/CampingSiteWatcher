package com.daanta.camp.domain;

import com.daanta.camp.utils.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

// Used for handling one User
@Getter
@Setter
@Builder
public class Usr {

    private int idx;
    private int grdIdx;
    private String id;
    private String mail;
    private String pw;
    private String nm;
    private Date regDt;
    private Date chgDt;
    private Date delDt;

    @Override
    public String toString() {
        Map<String, Object> m = new HashMap<>();
        m.put("idx"    , this.idx);
        m.put("grdIdx" , this.grdIdx);
        m.put("id"     , this.id);
        m.put("mail"   , this.mail);
        m.put("pw"     , this.pw);
        m.put("nm"     , this.nm);
        m.put("regDt"  , this.regDt);
        m.put("chgDt"  , this.chgDt);
        m.put("delDt"  , this.delDt);
        return Utils.getPrettyJson(m);
    }

}
