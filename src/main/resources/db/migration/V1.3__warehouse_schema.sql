alter table if exists warehous drop constraint if exists FK_warehous_address_id;
drop sequence if exists warehous_sequence;
drop table if exists warehous cascade;

create sequence warehous_sequence start 1 increment 1;

create table warehous (
id int8 NOT NULL,
name varchar(255),
warehouse_type varchar(255),
price_type varchar(255),
description TEXT,
address_id int8 NOT NULL,
primary key (id)
);

alter table if exists warehous add constraint FK_warehous_address_id foreign key (address_id) references address;