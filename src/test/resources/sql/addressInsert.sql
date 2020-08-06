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


CREATE TABLE IF NOT EXISTS address (
	id BIGSERIAL primary key,
  apartment_number varchar(10),
  city varchar(50),
  description text,
  district varchar(50),
  house_number varchar(10),
  region varchar(50),
  street varchar(50),
  zipcode varchar(15),
  country_iso3166 varchar(3),
  FOREIGN KEY (country_iso3166)
      REFERENCES country (iso3166)
);

INSERT INTO address (apartment_number, city, district, house_number, region, street, zipcode, description, country_iso3166)
 VALUES ('1', 'Kiev', 'Holosiiv district', '5a', 'Holosiiv region', 'Petra Stusa', '01032', 'description address 1', '804');
INSERT INTO address (apartment_number, city, district, house_number, region, street, zipcode, description, country_iso3166)
 VALUES ('4', 'Kiev', 'Podil district', '4a', 'Podil region', 'Sagaidachnogo', '01032', 'description address 2', '804');
INSERT INTO address (apartment_number, city, district, house_number, region, street, zipcode, description, country_iso3166)
 VALUES ('3', 'Bermuda', 'Bermuda district', '4a', 'Bermuda region', 'Bermuda street', 'HM 12', 'description address Bermuda', '060');
INSERT INTO address (apartment_number, city, district, house_number, region, street, zipcode, description, country_iso3166)
 VALUES ('5', 'Azerbaijan', 'Azerbaijan district', '8b', 'Azerbaijan region', 'Azerbaijan street', 'AZ0400', 'description address Azerbaijan', '031');
