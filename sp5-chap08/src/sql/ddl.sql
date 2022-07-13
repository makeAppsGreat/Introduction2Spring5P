create user 'spring5'@'192.168.1.%' identified by 'spring5';

create database spring5fs;

grant all privileges on spring5fs.* to 'spring5'@'192.168.1.%';

create table spring5fs.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key (EMAIL)
);