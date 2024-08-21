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
insert into user_table (email, firstname, lastname, password, phone, enabled, address1, address2, city, postcode)
values ('anniefresco@test.com', 'Annie', 'Fresco', '$2a$10$UnsqVzZTipaKE77lf6Bfme/nvIqwIRSzf2lqPgUr.9LuLjdvT1WkW', '07123456789', true,
        '21 Knox Road', 'Madison Avenue', 'Cardiff', 'CF23 1OX');
insert into users_roles (user_id, role_id)
values (2, 2);
insert into user_treatment_plans (user_id, treatment_plan_id)
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
       ('Midline and Genitals Lymphoedema', 'Lymphoedema affecting the midline and genital area'),
       ('Left Leg Lymphoedema', 'Lymphoedema affecting the left leg');


-- Set variables for lymphoedema type IDs
SET @leg_lymphoedema_id = LAST_INSERT_ID();
SET @arm_lymphoedema_id = @leg_lymphoedema_id + 1;
SET @breast_lymphoedema_id = @leg_lymphoedema_id + 2;
SET @head_neck_lymphoedema_id = @leg_lymphoedema_id + 3;
SET @midline_genitals_lymphoedema_id = @leg_lymphoedema_id + 4;
SET @left_leg_lymphoedema_id = @leg_lymphoedema_id + 5;

-- assign default lymphoedema type
insert into user_lymphoedema_types (user_id, lymphoedema_type_id)
values (2, @left_leg_lymphoedema_id);

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
SELECT @left_leg_lymphoedema_id, measurement_type_id
FROM measurement_types
WHERE measurement_type_id BETWEEN @first_measurement_type_id AND @first_measurement_type_id + 7;

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
INSERT INTO admin_goal_setting (user_id, goal_description, goal_deadline)
VALUES (2, 'Remember to apply wraps daily', '2024-09-10'),
       (2, 'Aim to reduce weight', '2024-10-10');

-- add appointment
INSERT INTO user_appointments (user_id, date, type, description)
VALUES (2, '2023-08-01', 'Initial appointment', 'First checkup appointment for left leg');

-- add diary entries
INSERT INTO diary_entries (user_id, createdAt, weight, cellulitisDetails, mobilityDetails, discomfortDetails, wellnessScore, qualityOfLifeScore)
VALUES
    (2, '2024-07-01', 70, 'No cellulitis symptoms', 'Able to walk with minimal discomfort', 'Mild discomfort in the morning',3, 6),
    (2, '2024-07-08', 69, 'No cellulitis symptoms', 'Limited mobility due to knee pain', 'Discomfort in the knee',3, 6),
    (2, '2024-07-15', 66, 'Redness and mild swelling observed', 'Slightly difficult to walk', 'Moderate discomfort',5, 7),
    (2, '2024-07-22', 66, 'Minor redness in the lower leg', 'Mobility slightly improved', 'Mild discomfort',6, 7),
    (2, '2024-07-29', 65, 'Cellulitis symptoms subsided', 'Mobility back to normal', 'Minimal discomfort',6, 8),
    (2, '2024-08-05', 64, 'No cellulitis symptoms', 'Able to walk longer distances', 'No discomfort',9, 8);

INSERT INTO diary_entry_exercises (diary_entry_id, exercise_id)
VALUES
    (1, 1),
    (1, 3),
    (2, 1),
    (2, 4),
    (3, 1),
    (3, 4),
    (3, 4),
    (4, 1),
    (4, 3),
    (4, 2),
    (5, 3),
    (5, 2),
    (6, 2),
    (6, 1);

INSERT INTO diary_entry_measurements (diary_entry_id, measurement_type_id, measurement_value)
VALUES
    (1, 1, 30),
    (1, 2, 30),
    (1, 3, 30),
    (1, 4, 30),
    (1, 5, 30),
    (1, 6, 30),
    (1, 7, 30),
    (1, 8, 30),
    (1, 9, 30),
    (1, 10, 30),
    (1, 11, 30),
    (1, 12, 25),
    (1, 13, 25),
    (1, 14, 25),
    (1, 15, 22),
    (1, 16, 22),
    (1, 17, 22),
    (1, 18, 22),
    (1, 19, 22),
    (1, 20, 22),
    (1, 21, 22),
    (1, 22, 22),

    (2, 1, 25),
    (2, 2, 25),
    (2, 3, 25),
    (2, 4, 25),
    (2, 5, 25),
    (2, 6, 25),
    (2, 7, 25),
    (2, 8, 25),
    (2, 9, 25),
    (2, 10, 22),
    (2, 11, 22),
    (2, 12, 22),
    (2, 13, 22),
    (2, 14, 22),
    (2, 15, 22),
    (2, 16, 22),
    (2, 17, 22),
    (2, 18, 22),
    (2, 19, 22),
    (2, 20, 22),
    (2, 21, 22),
    (2, 22, 22),

    (3, 1, 20),
    (3, 2, 20),
    (3, 3, 20),
    (3, 4, 20),
    (3, 5, 20),
    (3, 6, 20),
    (3, 7, 20),
    (3, 8, 20),
    (3, 9, 20),
    (3, 10, 18),
    (3, 11, 18),
    (3, 12, 18),
    (3, 13, 18),
    (3, 14, 18),
    (3, 15, 18),
    (3, 16, 18),
    (3, 17, 18),
    (3, 18, 18),
    (3, 19, 18),
    (3, 20, 18),
    (3, 21, 18),
    (3, 22, 18);

INSERT INTO preappointment_questionnaire_responses (
    user_id,
    created_at,
    medications,
    changes_to_health,
    swelling_concerns,
    hosiery_concerns,
    cellulitis_episodes
) VALUES
      (2, '2024-06-22', 'Ibuprofen', 'Mild leg pain, increased fatigue', 'Mild swelling in left ankle', 'No issues with current hosiery', 2);

INSERT INTO cellulitis_incident_responses (
    date_of_cellulitis,
    area_affected,
    redness,
    pain_discomfort,
    warm_touch,
    swelling_worsen,
    blisters,
    raised_temperature,
    flu_symptoms,
    advice_visit,
    oral_antibiotics,
    course_duration,
    iv_antibiotics,
    hospital_admission,
    lymphoedema_clinic_contact,
    comments
) VALUES
    (
        '01/06/2024',
        'Left Leg',
        'Yes',
        'Yes',
        'Yes',
        'Yes',
        'No',
        'Yes',
        'Yes',
        'Visited GP',
        'Yes',
        '7 days',
        'No',
        'No',
        'Yes',
        'First cellulitis episode after minor cut on leg.'
    );

INSERT INTO cellulitis_incident_responses (
    date_of_cellulitis,
    area_affected,
    redness,
    pain_discomfort,
    warm_touch,
    swelling_worsen,
    blisters,
    raised_temperature,
    flu_symptoms,
    advice_visit,
    oral_antibiotics,
    course_duration,
    iv_antibiotics,
    hospital_admission,
    lymphoedema_clinic_contact,
    comments
) VALUES
    (
        '18/06/2024',
        'Left Ankle',
        'Yes',
        'No',
        'Yes',
        'No',
        'Yes',
        'Yes',
        'No',
        'Visited Emergency Room',
        'Yes',
        '10 days',
        'No',
        'No',
        'Yes',
        'Swelling worsened, but no significant pain.'
    );

INSERT INTO preappointment_cellulitis_incident_responses (
    preappointment_questionnaire_response_id,
    cellulitis_incident_response_id
) VALUES
      (1, 1),
      (1, 2);

INSERT INTO qol_questionnaire_responses (
    user_id,
    created_at,
    walking,
    bending,
    standing,
    getting_up,
    occupation,
    housework,
    leisure_activities,
    leisure_examples,
    dependency,
    appearance,
    clothes_fit_difficulty,
    clothes_preference_difficulty,
    shoes_fit_difficulty,
    socks_fit_difficulty,
    self_perception,
    relationship_impact,
    pain,
    numbness,
    pins_needles,
    leg_weakness,
    leg_heaviness,
    sleep_trouble,
    difficulty_concentrating,
    feeling_tense,
    feeling_worried,
    feeling_irritable,
    feeling_depressed,
    quality_of_life
) VALUES
    (
        2,                    -- user_id
        '2024-06-22',         -- created_at
        3,                    -- Q1a) Quite a bit - Your walking
        2,                    -- Q1b) A little - Your ability to bend
        3,                    -- Q1c) Quite a bit - Your ability to stand
        2,                    -- Q1d) A little - Your ability to get up from a chair
        4,                    -- Q1e) A lot - Your occupation
        3,                    -- Q1f) Quite a bit - Your ability to do housework
        3,                    -- Q2) A lot - Your leisure activities/social life
        'I usually cant take part in meeting friends for casual walks since they are inconvenient and painful', -- For leisure activities
        3,                    -- Q3) Quite a bit - Dependence on others
        4,                    -- Q4) A lot - Swelling affecting appearance
        2,                    -- Q5) A little - Difficulty finding clothes to fit
        3,                    -- Q6) Quite a bit - Difficulty finding clothes you'd like to wear
        4,                    -- Q7) A lot - Difficulty finding shoes to fit
        2,                    -- Q8) A little - Difficulty finding socks/tights/stockings to fit
        3,                    -- Q9) Quite a bit - Swelling affecting self-perception
        4,                    -- Q10) A lot - Impact on relationships
        3,                    -- Q11) Quite a bit - Lymphoedema causing pain
        2,                    -- Q12) A little - Numbness in swollen leg(s)
        3,                    -- Q13) Quite a bit - Pins & needles in swollen leg(s)
        2,                    -- Q14) A little - Swollen leg(s) feel weak
        4,                    -- Q15) A lot - Swollen leg(s) feel heavy
        3,                    -- Q16) Quite a bit - Trouble sleeping in the past week
        2,                    -- Q17) A little - Difficulty concentrating
        3,                    -- Q18) Quite a bit - Feeling tense
        4,                    -- Q19) A lot - Feeling worried
        3,                    -- Q20) Quite a bit - Feeling irritable
        4,                    -- Q21) A lot - Feeling depressed
        7                     -- Q22) Quality of life rated 7 out of 10
    );