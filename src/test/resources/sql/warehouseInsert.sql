INSERT INTO country (alpha3code, name) VALUES ('AUS', 'Australia');
INSERT INTO country (alpha3code, name) VALUES ('ASS', 'Austria');
INSERT INTO country (alpha3code, name) VALUES ('AZE', 'Azerbaijan');
INSERT INTO country (alpha3code, name) VALUES ('BER', 'Bermuda');
INSERT INTO country (alpha3code, name) VALUES ('UKR', 'Ukraine');

INSERT INTO address (id, apartment_number, city, district, house_number, region, street, zipcode, description, country_alpha3code)
 VALUES (-1, '1', 'Kiev', 'Holosiiv district', '5a', 'Holosiiv region', 'Petra Stusa', '01032', 'description address 1', 'UKR');
INSERT INTO address (id, apartment_number, city, district, house_number, region, street, zipcode, description, country_alpha3code)
 VALUES (-2, '4', 'Kiev', 'Podil district', '4a', 'Podil region', 'Sagaidachnogo', '01032', 'description address 2', 'UKR');
INSERT INTO address (id, apartment_number, city, district, house_number, region, street, zipcode, description, country_alpha3code)
 VALUES (-3, '3', 'Bermuda', 'Bermuda district', '4a', 'Bermuda region', 'Bermuda street', 'HM 12', 'description address Bermuda', 'BER');
INSERT INTO address (id, apartment_number, city, district, house_number, region, street, zipcode, description, country_alpha3code)
 VALUES (-4, '5', 'Azerbaijan', 'Azerbaijan district', '8b', 'Azerbaijan region', 'Azerbaijan street', 'AZ0400', 'description address Azerbaijan', 'AZE');



INSERT INTO warehouse (id, name, warehouse_type, price_type, description, address_id)
 VALUES (-1, 'warehouse_1', 'RETAIL', 'RETAIL', 'description_1', '-2');
INSERT INTO warehouse (id, name, warehouse_type, price_type, description, address_id)
 VALUES (-2, 'warehouse_2', 'WHOLESALE', 'WHOLESALE', 'description_2', '-1');
 INSERT INTO warehouse (id, name, warehouse_type, price_type, description, address_id)
 VALUES (-3, 'warehouse_3', 'RETAIL', 'RETAIL', 'description_3', '-4');
INSERT INTO warehouse (id, name, warehouse_type, price_type, description, address_id)
 VALUES (-4, 'warehouse_4', 'WHOLESALE', 'WHOLESALE', 'description_4', '-3');
INSERT INTO warehouse (id, name, warehouse_type, price_type, description, address_id)
 VALUES (-5, 'warehouse_5', 'RETAIL', 'RETAIL', 'description_5', '-3');