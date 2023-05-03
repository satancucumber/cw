CREATE SCHEMA IF NOT EXISTS shopadmin;

create table if not exists shopadmin.seller (
    id serial primary key,
    name varchar(256),
    lastname varchar(256),
    fathername varchar(256),
    birth varchar(256),
    email varchar(256),
    phone varchar(256)
);

create table if not exists shopadmin.shop (
    id serial primary key,
    name varchar(256),
    address varchar(256),
    info varchar(256),
    director_id integer unique references shopadmin.seller (id)
);

create table if not exists shopadmin.product (
    id serial primary key,
    name varchar(256),
    article varchar(256),
    count integer,
    price integer
);
insert into shopadmin.seller (name, lastname, fathername, birth, email, phone, shop_id)
values
('Имя_1', 'Фамилия_1', 'Отчество_1', '01.01.1990', 'first@example.com', '+7(000)000-00-01', 1),
('Имя_2', 'Фамилия_2', 'Отчество_2', '02.01.1990', 'second@example.com', '+7(000)000-00-02', 1),
('Имя_3', 'Фамилия_3', 'Отчество_3', '03.01.1990', 'third@example.com', '+7(000)000-00-03', 1);
