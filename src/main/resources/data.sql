use nhsdb;
insert into roles_table (role_id, name)
values (1, 'ADMIN'),
       (2, 'USER');

-- add default admin account admin admin
insert into user_table (email, firstname, lastname, password, enabled)
values ('admin@admin.com', 'Default', 'Admin', '$2a$12$zKMHlbOC7UXOLCdq5ZibC.ANCzcxgpoGbode97Dc2Fi1zakG2fP6O', true);
insert into users_roles (user_id, role_id)
values (1, 1);

-- add default user account test test
insert into user_table (email, firstname, lastname, password, enabled, address1, address2, city, postcode)
values ('test@test.com', 'Default', 'User', '$2a$10$UnsqVzZTipaKE77lf6Bfme/nvIqwIRSzf2lqPgUr.9LuLjdvT1WkW', true,
        '21 Test Street', 'Test Avenue', 'Cardiff', 'CF23 1OX');
insert into users_roles (user_id, role_id)
values (2, 2);
insert into user_treatment_plans (user_id, treatment_plan_id)
values (2, 1);
insert into user_lymphoedema_types (user_id, lymphoedema_type_id)
values (2, 1);

-- add default links
INSERT INTO videos (video_link)
VALUES ('http://example.com/skincare_video'),
       ('http://example.com/exercise_video'),
       ('http://example.com/sld_mld_video'),
       ('http://example.com/compression_video'),
       ('http://example.com/donning_doffing_video'),
       ('http://example.com/easy_wrap_video');

-- add default exercises
INSERT INTO exercises (name, description, video_id)
VALUES ('Skincare Routine', 'Daily moisturizing and skin inspection to prevent complications.', 1),
       ('Daily Exercise Routine', 'Exercise routine to enhance lymphatic flow and reduce swelling.', 2),
       ('Self-Lymphatic Drainage (SLD) / Manual Lymphatic Drainage (MLD)',
        'Techniques for promoting lymphatic drainage.', 3),
       ('Intermittent Pneumatic Compression', 'Using a pneumatic compression device to help reduce swelling.', 4),
       ('Donning and Doffing', 'How to properly put on and take off compression garments.', 5),
       ('Application of Easy Wraps', 'How to apply and adjust easy wraps or bandages.', 6);


-- add default treatment plan 1
INSERT INTO treatment_plans (name, description)
VALUES ('Default Leg Lymphoedema Treatment Plan',
        'This treatment plan includes daily practices for managing leg lymphoedema, including skincare, exercises, self-lymphatic drainage, and more.');
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6);
-- add default treatment plan 2
INSERT INTO treatment_plans (name, description)
VALUES ('Default Treatment Plan 2', 'This is the second default treatment plan.');
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id)
VALUES (2, 3),
       (2, 4),
       (2, 5);
-- add default treatment plan 3
INSERT INTO treatment_plans (name, description)
VALUES ('Default Treatment Plan 3', 'This is the third default treatment plan.');
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id)
VALUES (3, 1),
       (3, 2),
       (3, 3);


-- add default measurement types
INSERT INTO measurement_types (name, description, unit)
VALUES ('Ankle Circumference', 'around the narrowest part of the ankle', 'cm'),
       ('Calf Circumference', 'around the widest part of the calf', 'cm'),
       ('Knee Circumference', 'around the knee joint', 'cm'),
       ('Thigh Circumference', 'around the widest part of the thigh', 'cm'),
       ('Foot Length', 'from the heel to the tip of the longest toe', 'cm'),
       ('Foot Circumference', 'around the widest part of the foot', 'cm');


-- add default lymphoedema types
INSERT INTO lymphoedema_types (name, description)
VALUES ('Primary Leg Lymphoedema', 'Leg lymphoedema caused by congenital or genetic factors'),
       ('Secondary Leg Lymphoedema',
        'Leg lymphoedema caused by damage to the lymphatic system, often due to surgery or radiation');

-- link lymphoedema types to measurements
INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6);

-- add admin goal settings
INSERT INTO admin_goal_setting (goal_id, user_id, goal_weight, goal_measurement)
VALUES (1, 2, 100, 100);