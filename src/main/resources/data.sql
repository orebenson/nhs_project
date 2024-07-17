use nhsdb;
insert into roles_table (role_id, name)
values
    (1, 'ADMIN'),
    (2, 'USER');

-- add default admin account admin admin
insert into user_table (email, firstname, lastname, password, enabled)
values ('admin@admin.com','Default','Admin', '$2a$12$zKMHlbOC7UXOLCdq5ZibC.ANCzcxgpoGbode97Dc2Fi1zakG2fP6O', true);

-- admin user_id is 1 as it is the first created account
insert into users_roles (user_id, role_id)
values (1, 1);

-- add default user account test test
insert into user_table (email, firstname, lastname, password, enabled, address1, address2, city, postcode)
values ('test@test.com','Default','User', '$2a$10$UnsqVzZTipaKE77lf6Bfme/nvIqwIRSzf2lqPgUr.9LuLjdvT1WkW', true, '21 Test Street', 'Test Avenue', 'Cardiff', 'CF23 1OX');

insert into users_roles (user_id, role_id)
values (2, 2);