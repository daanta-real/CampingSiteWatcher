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
  , lastval VARCHAR(255)
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


/* 데이터부 */

/* GRD
운영에 필요한 최소 회원은, 운영자, 정회원 둘 뿐이다.
나머지는 사이트 규모가 커지면 필요한 것들이다.
하지만, 후일을 위해 미리 추가해 놓겠다.
*/
SELECT SETVAL('grd_idx_seq', 1);
INSERT INTO grd
    (idx, id, nm)
VALUES
    (0, 'admin'  , '운영자'),
    (1, 'coadmin', '부운영자'),
    (2, 'senior' , '성실회원'),
    (3, 'member' , '정회원'),
    (4, 'novice' , '준회원'),
    (5, 'guest'  , '비회원');

/* USR */
SELECT SETVAL('usr_idx_seq', 4);
INSERT Into usr
    (idx, grd_idx, id, mail, pw, nm)
VALUES
    (0, 0, 'asdf3', '1@naver.com'  , '1234', 'p1'),
    (1, 0, 'qf3r3', '2@daum.net'   , '2345', 'p2'),
    (2, 1, 'wg44g', '3@hotmail.com', '3456', 'p2'),
    (3, 1, 'dfp93', '4@naver.com'  , '4567', 'p4'),
    (4, 1, 'dd24f', '5@nate.com'   , '5678', 'p5');

/* SIT */
SELECT SETVAL('site_idx_seq', 8);
INSERT INTO site
    (idx, nm, "type", url, query, lastval, reg_idx, reg_dt, chg_idx, chg_dt, del_dt)
VALUES
    (0, '네이버'    , 'nanji', 'https://www.naver.com/'   , 'div', NULL, 0, '2023-01-01', 0, '2023-01-12', NULL),
    (1, '다음'      , 'nanji', 'https://www.daum.net/'    , 's', '123', 0, '2020-01-11', NULL, '2023-02-12', NULL),
    (2, 'KK박물관'  , 'nanji', 'https://www.kkmuseum.net/', 'i', 'recent value', 1, '2023-03-12', 1, '2023-03-12', NULL),
    (3, '싸이월드'  , 'nanji', 'https://www.cyworld.com/'  , 'span', 'recent', 1, '2023-04-12', NULL, '2023-04-12', NULL),
    (4, '티스토리'  , 'nanji', 'https://www.tistory.com/'  , 'p', 're value', 2, '2023-05-12', NULL, '2023-05-12', NULL),
    (5, '뽐뿌'      , 'nanji', 'https://www.ppomppu.co.kr/', 'h3', 'val', 2, '2023-09-12', 3, '2023-09-12', TO_DATE('2023-09-12 13:00:00', 'YYYY-MM-DD HH24:MI:SS')),
    (6, '루리웹'    , 'nanji', 'https://www.ruliweb.com/'  , 'font', 'v', 3, '2023-06-12', NULL, '2023-06-12', NULL),
    (7, '네이버카페', 'nanji', 'https://cafe.naver.com/'    , 'u', 'valueee', 3, '2023-07-12', 4, '2023-07-12', NULL),
    (8, '유튜브'    , 'nanji', 'https://youtube.com/'      , 'img', 'ree', 4, '2023-08-12', 5, '2023-08-12', NULL);

/* HEA */
SELECT SETVAL('header_idx_seq', 8);
INSERT INTO header
    (idx, site_idx, key, val)
VALUES
    (0, 0, 'name', '이름이름'),
    (1, 0, 'page', '2'),
    (2, 0, 'date', '230901'),
    (3, 1, 'battery', '100'),
    (4, 1, 'referer', 'https://tickets.interpark.com/'),
    (5, 2, 'user-agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36'),
    (6, 2, 'accept', 'application/json, text/plain, */*'),
    (7, 2, 'Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

/* BDY */
SELECT SETVAL('formbody_idx_seq', 6);
INSERT INTO formbody
    (idx, site_idx, key, val)
VALUES
    (0, 2, 'rsv_svc_id', '17054781362'),
    (1, 2, 'sltYear', '2023'),
    (2, 3, 'sltMonth', '02'),
    (3, 3, 'y', '2023'),
    (4, 4, 'm', '09'),
    (5, 4, 'd', '02');
