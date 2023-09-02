-- "formbody" 테이블 삭제
DROP TABLE IF EXISTS formbody;

-- "header" 테이블 삭제
DROP TABLE IF EXISTS header;

-- "site" 테이블 삭제
DROP TABLE IF EXISTS site;

-- "site" 테이블 생성
CREATE TABLE site (
  id SERIAL CONSTRAINT site_pk PRIMARY KEY,
  url VARCHAR(255) NOT NULL CONSTRAINT site_url_not_null CHECK (url IS NOT NULL),
  query VARCHAR(255),
  lateval VARCHAR(255) NOT NULL CONSTRAINT site_lateval_not_null CHECK (lateval IS NOT NULL)
);

-- "header" 테이블 생성
CREATE TABLE header (
  id SERIAL CONSTRAINT header_pk PRIMARY KEY,
  site_id INT,
  key VARCHAR(255),
  val VARCHAR(255),
  CONSTRAINT header_site_fk FOREIGN KEY (site_id) REFERENCES site(id)
);

-- "formbody" 테이블 생성
CREATE TABLE formbody (
  id SERIAL CONSTRAINT formbody_pk PRIMARY KEY,
  site_id INT,
  key VARCHAR(255),
  val VARCHAR(255),
  CONSTRAINT formbody_site_fk FOREIGN KEY (site_id) REFERENCES site(id)
);