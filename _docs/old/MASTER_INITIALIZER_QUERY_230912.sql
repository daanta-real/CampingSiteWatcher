-- 전체 테이블 삭제
DROP TABLE IF EXISTS formbody;
DROP TABLE IF EXISTS header;
DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS grd;

-- 전체 시퀀스 삭제
DROP SEQUENCE IF EXISTS grd_idx_seq;
DROP SEQUENCE IF EXISTS usr_idx_seq;
DROP SEQUENCE IF EXISTS site_idx_seq;
DROP SEQUENCE IF EXISTS header_idx_seq;
DROP SEQUENCE IF EXISTS formbody_idx_seq;

-- 회원등급 테이블의 생성
CREATE SEQUENCE grd_idx_seq;
CREATE TABLE grd(
      idx   INTEGER     CONSTRAINT grd_pk           DEFAULT NEXTVAL('grd_idx_seq') PRIMARY KEY
    , nm    VARCHAR(50) CONSTRAINT grd_nm_not_null  CHECK (nm IS NOT NULL)
);
COMMENT ON TABLE  grd     IS '회원-등급';
COMMENT ON COLUMN grd.idx IS '권한 idx';
COMMENT ON COLUMN grd.nm  IS '권한 명';

-- 회원 테이블의 생성
CREATE SEQUENCE usr_idx_seq;
CREATE TABLE usr(
      idx     INTEGER       CONSTRAINT usr_pk               DEFAULT NEXTVAL('usr_idx_seq') PRIMARY KEY
    , grd_idx INTEGER       CONSTRAINT usr_fk_grd           REFERENCES grd(idx)         ON DELETE SET NULL
    , id      VARCHAR(50)   CONSTRAINT usr_id_not_null      CHECK (id IS NOT NULL)
    , mail    VARCHAR(100)
    , pw      VARCHAR(255)
    , nm      VARCHAR(50)
    , reg_dt  DATE          CONSTRAINT usr_reg_dt_not_null  CHECK (reg_dt IS NOT NULL)  DEFAULT CURRENT_DATE
    , chg_dt  DATE          CONSTRAINT usr_chg_dt_not_null  CHECK (chg_dt IS NOT NULL)  DEFAULT CURRENT_DATE
    , del_dt  DATE
);
COMMENT ON TABLE  usr         IS '회원';
COMMENT ON COLUMN usr.idx     IS '회원 식별번호';
COMMENT ON COLUMN usr.grd_idx IS '회원 등급';
COMMENT ON COLUMN usr.id      IS '회원 ID';
COMMENT ON COLUMN usr.mail    IS '회원 이메일';
COMMENT ON COLUMN usr.pw      IS '회원 비밀번호';
COMMENT ON COLUMN usr.nm      IS '회원 명';
COMMENT ON COLUMN usr.reg_dt  IS '회원 생성일';
COMMENT ON COLUMN usr.chg_dt  IS '회원 변경일';
COMMENT ON COLUMN usr.del_dt  IS '회원 삭제일(NULL 아닐 시 삭제된 레코드임)';

-- 웹사이트 테이블 생성
CREATE SEQUENCE site_idx_seq;
CREATE TABLE site(
    idx     INTEGER         CONSTRAINT site_pk                  DEFAULT NEXTVAL('site_idx_seq') PRIMARY KEY
  , nm      VARCHAR(255)    CONSTRAINT site_nm_not_null         CHECK (nm IS NOT NULL)
  , type    VARCHAR(255)    CONSTRAINT site_type_not_null       CHECK (type IS NOT NULL)
  , url     VARCHAR(255)    CONSTRAINT site_url_not_null        CHECK (url IS NOT NULL)
  , query   VARCHAR(255)
  , lastval VARCHAR(255)    CONSTRAINT site_lastval_not_null    CHECK (lastval IS NOT NULL)
  , reg_idx VARCHAR(50)
  , reg_dt  DATE            CONSTRAINT site_reg_dt_not_null     CHECK (reg_dt IS NOT NULL)  DEFAULT CURRENT_DATE
  , chg_idx VARCHAR(50)
  , chg_dt  DATE            CONSTRAINT site_chg_dt_not_null     CHECK (chg_dt IS NOT NULL)  DEFAULT CURRENT_DATE
  , del_dt  DATE
);
COMMENT ON TABLE  site          IS '웹사이트';
COMMENT ON COLUMN site.idx      IS '웹사이트 식별번호';
COMMENT ON COLUMN site.nm       IS '웹사이트 이름';
COMMENT ON COLUMN site.type     IS '웹사이트 유형';
COMMENT ON COLUMN site.url      IS '웹사이트 주소';
COMMENT ON COLUMN site.query    IS '웹사이트 탐색 쿼리';
COMMENT ON COLUMN site.lastval  IS '웹사이트 최신값';
COMMENT ON COLUMN site.reg_idx  IS '웹사이트 생성자';
COMMENT ON COLUMN site.reg_dt   IS '웹사이트 생성일';
COMMENT ON COLUMN site.chg_idx  IS '웹사이트 변경자';
COMMENT ON COLUMN site.chg_dt   IS '웹사이트 변경일';
COMMENT ON COLUMN site.del_dt   IS '웹사이트 삭제일(NULL 아닐 시 삭제된 레코드임)';

-- "header" 테이블 생성
CREATE SEQUENCE header_idx_seq;
CREATE TABLE header(
    idx       INTEGER         CONSTRAINT header_pk        DEFAULT NEXTVAL('header_idx_seq') PRIMARY KEY
  , site_idx  INTEGER         CONSTRAINT header_site_fk   REFERENCES site(idx) ON DELETE SET NULL
  , key       VARCHAR(255)
  , val       VARCHAR(255)
);
COMMENT ON TABLE  header            IS '웹사이트 헤더';
COMMENT ON COLUMN header.idx        IS '헤더 식별번호';
COMMENT ON COLUMN header.site_idx   IS '헤더 웹사이트 식별번호';
COMMENT ON COLUMN header.key        IS '헤더 키';
COMMENT ON COLUMN header.val        IS '헤더 값';

-- "formbody" 테이블 생성
CREATE SEQUENCE formbody_idx_seq;
CREATE TABLE formbody(
    idx       INTEGER         CONSTRAINT formbody_pk      DEFAULT NEXTVAL('formbody_idx_seq') PRIMARY KEY
  , site_idx  INTEGER         CONSTRAINT formbody_site_fk REFERENCES site(idx) ON DELETE SET NULL
  , key       VARCHAR(255)
  , val       VARCHAR(255)
);
COMMENT ON TABLE  formbody              IS '웹사이트 폼';
COMMENT ON COLUMN formbody.idx          IS '폼 식별번호';
COMMENT ON COLUMN formbody.site_idx     IS '폼 웹사이트 식별번호';
COMMENT ON COLUMN formbody.key          IS '폼 키';
COMMENT ON COLUMN formbody.val          IS '폼 값';
