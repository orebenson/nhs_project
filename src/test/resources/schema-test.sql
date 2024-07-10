use nhsdb;
drop table if exists user_table;
drop table if exists roles_table;
drop table if exists users_roles;

create table if not exists user_table
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  boolean      NOT NULL,
    email    VARCHAR(50),
    address  VARCHAR(500),
    address2  VARCHAR(500),
    city     VARCHAR(50),
    zipCode  VARCHAR(500)
    ) engine = InnoDB;

create table if not exists roles_table
(
    role_id BIGINT      NOT NULL,
    name    VARCHAR(45) NOT NULL
    ) engine = InnoDB;

create table if not exists users_roles
(
    id       BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    role_id  BIGINT      NOT NULL
    ) engine = InnoDB;

create view if not exists user_authorities as
select u.username as username, CONCAT("ROLE_", r.name) as authority
from user_table u
         inner join users_roles ur on u.username = ur.username
         inner join roles_table r on ur.role_id = r.role_id;
