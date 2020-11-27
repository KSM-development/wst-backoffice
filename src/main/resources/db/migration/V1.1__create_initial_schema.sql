alter table if exists address drop constraint if exists FK_address_country_alpha3code;
drop table if exists address cascade;
drop table if exists country cascade;
drop sequence if exists address_sequence;

create sequence address_sequence start 1 increment 1;

create table address (
    id int8 not null,
    apartment_number VARCHAR(10),
    city VARCHAR(50),
    description TEXT,
    district VARCHAR(50),
    house_number VARCHAR(10),
    region VARCHAR(50),
    street VARCHAR(50),
    zipcode VARCHAR(15),
    country_alpha3code VARCHAR(3),
    primary key (id)
);

create table country (
    alpha3code VARCHAR(3) not null,
    alpha2code VARCHAR(3) not null,
    name VARCHAR(255),
    primary key (alpha3code)
);

alter table if exists address add constraint FK_address_country_alpha3code foreign key (country_alpha3code) references country;