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



INSERT INTO qol_activity_score_key_table (choice, score)
VALUES ('not_at_all', 1),
       ('a_little', 2),
       ('quite_a_bit', 3),
       ('a_lot', 4),
       ('n_a', 0);

-- Set SQL mode to allow local variables
SET SQL_MODE = 'ALLOW_INVALID_DATES';

-- Insert lymphoedema types
INSERT INTO lymphoedema_types (name, description)
VALUES ('Leg Lymphoedema', 'Lymphoedema affecting the legs'),
       ('Arm Lymphoedema', 'Lymphoedema affecting the arms'),
       ('Breast Lymphoedema', 'Lymphoedema affecting the breast area'),
       ('Head and Neck Lymphoedema', 'Lymphoedema affecting the head and neck region'),
       ('Midline and Genitals Lymphoedema', 'Lymphoedema affecting the midline and genital area');

-- Set variables for lymphoedema type IDs
SET @leg_lymphoedema_id = LAST_INSERT_ID();
SET @arm_lymphoedema_id = @leg_lymphoedema_id + 1;
SET @breast_lymphoedema_id = @leg_lymphoedema_id + 2;
SET @head_neck_lymphoedema_id = @leg_lymphoedema_id + 3;
SET @midline_genitals_lymphoedema_id = @leg_lymphoedema_id + 4;

-- Insert measurement types
INSERT INTO measurement_types (name, description, unit)
VALUES
-- Leg Lymphoedema Measurements
('(Left) Circumference around the joint at the base of the toes', 'Measurement for left leg', 'cm'),
('(Left) Circumference above the ankle bone', 'Measurement for left leg', 'cm'),
('(Left) Circumference around widest part of the calf', 'Measurement for left leg', 'cm'),
('(Left) Circumference two finger widths below the knee', 'Measurement for left leg', 'cm'),
('(Left) Circumference around the knee bend', 'Measurement for left leg', 'cm'),
('(Left) Circumference at the mid thigh', 'Measurement for left leg', 'cm'),
('(Left) Circumference at the top of the thigh', 'Measurement for left leg', 'cm'),
('(Left) Length from heel to longest toe', 'Measurement for left leg', 'cm'),
('(Right) Circumference around the joint at the base of the toes', 'Measurement for right leg', 'cm'),
('(Right) Circumference above the ankle bone', 'Measurement for right leg', 'cm'),
('(Right) Circumference around widest part of the calf', 'Measurement for right leg', 'cm'),
('(Right) Circumference two finger widths below the knee', 'Measurement for right leg', 'cm'),
('(Right) Circumference around the knee bend', 'Measurement for right leg', 'cm'),
('(Right) Circumference at the mid thigh', 'Measurement for right leg', 'cm'),
('(Right) Circumference at the top of the thigh', 'Measurement for right leg', 'cm'),
('(Right) Length from heel to longest toe', 'Measurement for right leg', 'cm'),

-- Arm Lymphoedema Measurements
('(Left) Circumference at base of fingers', 'Measurement for left arm', 'cm'),
('(Left) Circumference of hand at web of thumb', 'Measurement for left arm', 'cm'),
('(Left) Circumference at wrist', 'Measurement for left arm', 'cm'),
('(Left) Circumference at mid point of wrist and elbow', 'Measurement for left arm', 'cm'),
('(Left) Circumference at elbow (slightly bent)', 'Measurement for left arm', 'cm'),
('(Left) Circumference at mid-point of elbow and armpit', 'Measurement for left arm', 'cm'),
('(Left) Circumference at top of arm', 'Measurement for left arm', 'cm'),
('(Left) Length from wrist to mid point of wrist and elbow', 'Measurement for left arm', 'cm'),
('(Left) Length from wrist to elbow (slightly bent)', 'Measurement for left arm', 'cm'),
('(Left) Length from wrist to mid-point of elbow and armpit', 'Measurement for left arm', 'cm'),
('(Left) Length from wrist to top of arm', 'Measurement for left arm', 'cm'),
('(Right) Circumference at base of fingers', 'Measurement for right arm', 'cm'),
('(Right) Circumference of hand at web of thumb', 'Measurement for right arm', 'cm'),
('(Right) Circumference at wrist', 'Measurement for right arm', 'cm'),
('(Right) Circumference at mid point of wrist and elbow', 'Measurement for right arm', 'cm'),
('(Right) Circumference at elbow (slightly bent)', 'Measurement for right arm', 'cm'),
('(Right) Circumference at mid-point of elbow and armpit', 'Measurement for right arm', 'cm'),
('(Right) Circumference at top of arm', 'Measurement for right arm', 'cm'),
('(Right) Length from wrist to mid point of wrist and elbow', 'Measurement for right arm', 'cm'),
('(Right) Length from wrist to elbow (slightly bent)', 'Measurement for right arm', 'cm'),
('(Right) Length from wrist to mid-point of elbow and armpit', 'Measurement for right arm', 'cm'),
('(Right) Length from wrist to top of arm', 'Measurement for right arm', 'cm'),

-- Breast Lymphoedema Measurements
('Circumference of the chest at the inframammary fold', 'Measurement for breast', 'cm'),
('Circumference around the fullest part of the breast', 'Measurement for breast', 'cm'),
('Circumference at the upper chest, just below the armpits', 'Measurement for breast', 'cm'),
('Circumference around the mid-breast area', 'Measurement for breast', 'cm'),
('Length from the base of the breast to the nipple', 'Measurement for breast', 'cm'),
('Length from the collarbone to the base of the breast', 'Measurement for breast', 'cm'),

-- Head and Neck Lymphoedema Measurements
('Circumference around the neck at the Adams apple', 'Measurement for head and neck', 'cm'),
('Circumference around the jawline', 'Measurement for head and neck', 'cm'),
('Circumference around the base of the skull', 'Measurement for head and neck', 'cm'),
('Distance from the base of the ear to the corner of the mouth', 'Measurement for head and neck', 'cm'),
('Distance from the tip of the chin to the base of the ear', 'Measurement for head and neck', 'cm'),

-- Midline and Genitals Lymphoedema Measurements
('Circumference at the waist', 'Measurement for midline and genitals', 'cm'),
('Circumference at the hips', 'Measurement for midline and genitals', 'cm'),
('Circumference at the upper thighs', 'Measurement for midline and genitals', 'cm'),
('Length from the waist to the top of the thigh', 'Measurement for midline and genitals', 'cm'),
('Length from the waist to the knee', 'Measurement for midline and genitals', 'cm'),
('Circumference around the genital area', 'Measurement for midline and genitals', 'cm');

-- Set variable for the first measurement type ID
SET @first_measurement_type_id = LAST_INSERT_ID();

-- Link lymphoedema types with measurement types
INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
SELECT @leg_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id AND @first_measurement_type_id + 15;

INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
SELECT @arm_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id + 16 AND @first_measurement_type_id + 37;

INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
SELECT @breast_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id + 38 AND @first_measurement_type_id + 43;

INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
SELECT @head_neck_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id + 44 AND @first_measurement_type_id + 48;

INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id)
SELECT @midline_genitals_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id + 49 AND @first_measurement_type_id + 54;

-- Insert treatment plans
INSERT INTO treatment_plans (name, description)
VALUES ('Leg Lymphoedema Treatment Plan', 'Treatment plan for leg lymphoedema'),
       ('Arm Lymphoedema Treatment Plan', 'Treatment plan for arm lymphoedema'),
       ('Breast Lymphoedema Treatment Plan', 'Treatment plan for breast lymphoedema'),
       ('Head and Neck Lymphoedema Treatment Plan', 'Treatment plan for head and neck lymphoedema'),
       ('Midline and Genitals Lymphoedema Treatment Plan', 'Treatment plan for midline and genitals lymphoedema');

-- Set variables for treatment plan IDs
SET @leg_plan_id = LAST_INSERT_ID();
SET @arm_plan_id = @leg_plan_id + 1;
SET @breast_plan_id = @leg_plan_id + 2;
SET @head_neck_plan_id = @leg_plan_id + 3;
SET @midline_genitals_plan_id = @leg_plan_id + 4;

-- Insert exercises
INSERT INTO videos (video_link)
VALUES ('https://example.com/compression_bandaging'),
       ('https://example.com/skin_care_regime'),
       ('https://example.com/velcro_wraps'),
       ('https://example.com/leg_exercise_routine'),
       ('https://example.com/self_lymphatic_drainage'),
       ('https://example.com/arm_exercise_routine'),
       ('https://example.com/breast_massage'),
       ('https://example.com/breast_exercises'),
       ('https://example.com/neck_facial_exercises'),
       ('https://example.com/head_neck_compression'),
       ('https://example.com/midline_exercises');

SET @first_video_id = LAST_INSERT_ID();

INSERT INTO exercises (name, description, video_id)
VALUES ('Compression bandaging', 'Application of compression bandages', @first_video_id),
       ('Skin care regime', 'Proper skin care for lymphoedema management', @first_video_id + 1),
       ('Apply velcro wraps', 'Application of velcro wraps for compression', @first_video_id + 2),
       ('Daily leg exercise routine', 'Exercise routine for leg lymphoedema', @first_video_id + 3),
       ('Self-Lymphatic Drainage (SLD) / Manual Lymphatic Drainage (MLD)', 'Techniques for lymphatic drainage',
        @first_video_id + 4),
       ('Daily arm exercise routine', 'Exercise routine for arm lymphoedema', @first_video_id + 5),
       ('Gentle breast massage', 'Massage techniques for breast lymphoedema', @first_video_id + 6),
       ('Daily breast exercises', 'Exercises to improve lymphatic flow in breasts', @first_video_id + 7),
       ('Gentle neck and facial exercises', 'Exercises for head and neck lymphoedema', @first_video_id + 8),
       ('Compression garments for head and neck', 'Application of specialized head and neck garments',
        @first_video_id + 9),
       ('Gentle exercises for midline and genitals', 'Exercises to promote lymphatic flow in midline and genital area',
        @first_video_id + 10);

-- Set variable for the first exercise ID
SET @first_exercise_id = LAST_INSERT_ID();

-- Link treatment plans with exercises
INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id)
VALUES (@leg_plan_id, @first_exercise_id),
       (@leg_plan_id, @first_exercise_id + 1),
       (@leg_plan_id, @first_exercise_id + 2),
       (@leg_plan_id, @first_exercise_id + 3),
       (@leg_plan_id, @first_exercise_id + 4),
       (@arm_plan_id, @first_exercise_id + 1),
       (@arm_plan_id, @first_exercise_id),
       (@arm_plan_id, @first_exercise_id + 5),
       (@arm_plan_id, @first_exercise_id + 4),
       (@breast_plan_id, @first_exercise_id),
       (@breast_plan_id, @first_exercise_id + 6),
       (@breast_plan_id, @first_exercise_id + 7),
       (@breast_plan_id, @first_exercise_id + 1),
       (@breast_plan_id, @first_exercise_id + 4),
       (@head_neck_plan_id, @first_exercise_id + 8),
       (@head_neck_plan_id, @first_exercise_id + 1),
       (@head_neck_plan_id, @first_exercise_id + 9),
       (@head_neck_plan_id, @first_exercise_id + 4),
       (@midline_genitals_plan_id, @first_exercise_id),
       (@midline_genitals_plan_id, @first_exercise_id + 10),
       (@midline_genitals_plan_id, @first_exercise_id + 1),
       (@midline_genitals_plan_id, @first_exercise_id + 4);




-- add admin goal settings
INSERT INTO admin_goal_setting (goal_id, user_id, goal_part, goal_description, goal_measurement, goal_unit, goal_deadline)
VALUES (1, 2, 'Ankle', '', 0, 'cm', '2024-07-10');