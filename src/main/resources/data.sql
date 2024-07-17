use nhsdb;
insert into roles_table (role_id, name)
values
    (1, 'ADMIN'),
    (2, 'USER');

-- add default admin account admin admin
insert into user_table (email, firstname, lastname, password, enabled)
values ('admin@admin.com','Default','Admin', '$2a$12$zKMHlbOC7UXOLCdq5ZibC.ANCzcxgpoGbode97Dc2Fi1zakG2fP6O', true);
insert into users_roles (user_id, role_id)
values (1, 1);

-- add default user account test test
insert into user_table (email, firstname, lastname, password, enabled, address1, address2, city, postcode)
values ('test@test.com','Default','User', '$2a$10$UnsqVzZTipaKE77lf6Bfme/nvIqwIRSzf2lqPgUr.9LuLjdvT1WkW', true, '21 Test Street', 'Test Avenue', 'Cardiff', 'CF23 1OX');
insert into users_roles (user_id, role_id)
values (2, 2);

-- add default treatment plan
INSERT INTO treatment_plans (name, description) VALUES ('Default Treatment Plan', 'This is the default treatment plan.');

-- Inserting videos
INSERT INTO videos (video_link) VALUES ('http://example.com/video1');
INSERT INTO videos (video_link) VALUES ('http://example.com/video2');
INSERT INTO videos (video_link) VALUES ('http://example.com/video3');

-- Inserting exercises
INSERT INTO exercises (name, description, video_id) VALUES ('Exercise 1', 'Description for Exercise 1', 1);
INSERT INTO exercises (name, description, video_id) VALUES ('Exercise 2', 'Description for Exercise 2', 2);
INSERT INTO exercises (name, description, video_id) VALUES ('Exercise 3', 'Description for Exercise 3', 3);

-- Linking exercises to the treatment plan
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id) VALUES (1, 1);
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id) VALUES (1, 2);
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id) VALUES (1, 3);