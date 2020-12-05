alter table if exists warehouse drop constraint if exists FK_warehouse_address_id;
drop sequence if exists warehouse_sequence;
drop table if exists warehouse cascade;

create sequence warehouse_sequence start 1 increment 1;

create table warehouse (
id int8 NOT NULL,
name varchar(255),
warehouse_type varchar(255),
price_type varchar(255),
description TEXT,
address_id int8 NOT NULL,
primary key (id)
);

alter table if exists warehouse add constraint FK_warehouse_address_id foreign key (address_id) references address;