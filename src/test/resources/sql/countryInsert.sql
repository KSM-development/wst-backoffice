CREATE TABLE IF NOT EXISTS country (
  iso3166 varchar(3),
  name varchar(255),
  PRIMARY KEY (iso3166)
);

INSERT INTO country (iso3166, name) VALUES ('036', 'Australia');
INSERT INTO country (iso3166, name) VALUES ('040', 'Austria');
INSERT INTO country (iso3166, name) VALUES ('031', 'Azerbaijan');
INSERT INTO country (iso3166, name) VALUES ('060', 'Bermuda');
INSERT INTO country (iso3166, name) VALUES ('804', 'Ukraine');
