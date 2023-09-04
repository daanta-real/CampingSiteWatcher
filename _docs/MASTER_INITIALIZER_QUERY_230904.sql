-- 전체 테이블 삭제
DROP TABLE IF EXISTS formbody;
DROP TABLE IF EXISTS header;
DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS grd;

-- 회원등급 테이블의 생성
CREATE TABLE grd(
      idx   SERIAL      CONSTRAINT grd_pk PRIMARY KEY
    , nm    VARCHAR(50) CONSTRAINT grd_nm_not_null CHECK (nm IS NOT NULL)
);
COMMENT ON TABLE  grd     IS '회원-등급';
COMMENT ON COLUMN grd.idx IS '권한 idx';
COMMENT ON COLUMN grd.nm  IS '권한 명';

-- 회원 테이블의 생성
CREATE TABLE usr(
      idx     SERIAL        CONSTRAINT usr_pk       PRIMARY KEY
    , grd_idx INTEGER       CONSTRAINT usr_fk_grd   REFERENCES grd(idx) ON DELETE SET NULL
    , id      VARCHAR(50)   NOT NULL CONSTRAINT usr_id_not_null
    , mail    VARCHAR(100)
    , pw      VARCHAR(255)
    , nm      VARCHAR(50)
    , reg_dt  DATE          NOT NULL CONSTRAINT usr_reg_dt_not_null DEFAULT CURRENT_DATE
    , chg_dt  DATE          NOT NULL CONSTRAINT usr_chg_dt_not_null DEFAULT CURRENT_DATE
    , del_dt  DATE
);

-- "site" 테이블 생성
CREATE TABLE site (
    id      SERIAL                      CONSTRAINT site_pk      PRIMARY KEY
  , url     VARCHAR(255)    NOT NULL
  , query   VARCHAR(255)
  , lastval VARCHAR(255)    NOT NULL
  , reg_id  VARCHAR(50)
  , reg_dt  DATE            NOT NULL    DEFAULT CURRENT_DATE
  , chg_id  VARCHAR(50)
  , chg_dt  DATE            NOT NULL    DEFAULT CURRENT_DATE
  , del_dt  DATE

);

-- "header" 테이블 생성
CREATE TABLE header (
  id        SERIAL                      CONSTRAINT header_pk                PRIMARY KEY,
  site_id   INT                         CONSTRAINT header_site_fk           REFERENCES site(id),
  key       VARCHAR(255),
  val       VARCHAR(255)
);

-- "formbody" 테이블 생성
CREATE TABLE formbody (
  id        SERIAL                      CONSTRAINT formbody_pk              PRIMARY KEY,
  site_id   INT                         CONSTRAINT formbody_site_fk         REFERENCES site(id),
  key       VARCHAR(255),
  val       VARCHAR(255),

);
