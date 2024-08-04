use nhsdb;
drop table if exists user_table;
drop table if exists roles_table;
drop table if exists users_roles;
drop table if exists treatment_plans;
drop table if exists exercises;
drop table if exists videos;
drop table if exists photos;
drop table if exists treatment_plan_exercises;
drop table if exists user_treatment_plans;
drop table if exists diary_entries;
drop table if exists diary_entry_exercises;
drop table if exists lymphoedema_types;
drop table if exists user_lymphoedema_types;
drop table if exists diary_entry_measurements;
drop table if exists measurement_types;
drop table if exists lymphoedema_type_measurements;
drop table if exists diary_entry_photos;
drop table if exists preappointment_questionnaire_responses;
drop table if exists qol_activity_score_key_table;
drop table if exists qol_questionnaire_responses;
drop table if exists cellulitis_incident_responses;
drop table if exists preappointment_cellulitis_incident_responses;

create table if not exists user_table
(
    user_id  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(50) NOT NULL,
    firstname VARCHAR(50)  NOT NULL,
    lastname VARCHAR(50)  NOT NULL,
    password VARCHAR(500) NOT NULL,
    enabled  boolean      NOT NULL,
    address1  VARCHAR(500),
    address2  VARCHAR(500),
    city     VARCHAR(50),
    postcode  VARCHAR(10)
) engine = InnoDB;

create table if not exists roles_table
(
    role_id BIGINT      NOT NULL,
    name    VARCHAR(45) NOT NULL
) engine = InnoDB;

create table if not exists users_roles
(
    id       BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT      NOT NULL,
    role_id  BIGINT      NOT NULL
) engine = InnoDB;

create view if not exists user_authorities as
select u.user_id as user_id, CONCAT('ROLE_', r.name) as authority
from user_table u
         inner join users_roles ur on u.user_id = ur.user_id
         inner join roles_table r on ur.role_id = r.role_id;

create table if not exists user_treatment_plans
(
    user_id  BIGINT      NOT NULL,
    treatment_plan_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists treatment_plans
(
    treatment_plan_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500)
) engine = InnoDB;

create table if not exists treatment_plan_exercises
(
    treatment_plan_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists exercises
(
    exercise_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500),
    video_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists videos
(
    video_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    video_link VARCHAR(500) NOT NULL
) engine = InnoDB;

create table if not exists photos
(
    photo_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    photo BLOB NOT NULL
) engine = InnoDB;


create table if not exists diary_entries (
   diary_entry_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_id BIGINT NOT NULL,
   createdAt DATE NOT NULL,
   weight INT,
   cellulitisDetails VARCHAR(500),
   mobilityDetails VARCHAR(500),
   discomfortDetails VARCHAR(500),
   wellnessScore INT,
   qualityOfLifeScore INT

) engine = InnoDB;

create table if not exists diary_entry_photos (
    diary_entry_id BIGINT NOT NULL,
    photo_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists diary_entry_exercises (
    diary_entry_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists lymphoedema_types (
    lymphoedema_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500) NOT NULL,
    description VARCHAR(500)
) engine = InnoDB;

create table if not exists user_lymphoedema_types (
    lymphoedema_type_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
) engine = InnoDB;

create table if not exists lymphoedema_type_measurements (
    lymphoedema_type_id BIGINT NOT NULL,
    measurement_type_id BIGINT NOT NULL

) engine = InnoDB;

create table if not exists measurement_types (
    measurement_type_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(500) NOT NULL,
    description VARCHAR(500),
    unit VARCHAR(10)
) engine = InnoDB;

create table if not exists diary_entry_measurements (
    measurement_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    diary_entry_id BIGINT NOT NULL,
    measurement_type_id BIGINT NOT NULL,
    measurement_value BIGINT
) engine = InnoDB;

create table if not exists preappointment_questionnaire_responses
(
    preappointment_questionnaire_response_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    created_at DATE NOT NULL,
    medications TEXT NOT NULL,
    changes_to_health TEXT NOT NULL,
    swelling_concerns TEXT NOT NULL,
    hosiery_concerns TEXT NOT NULL,
    cellulitis_episodes INT
) engine = InnoDB;

create table if not exists qol_activity_score_key_table
(
    choice TEXT,
    score INT
) engine = InnoDB;

create table if not exists qol_questionnaire_responses
(
    qol_questionnaire_response_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT NOT NULL,
    created_at          DATE NOT NULL,
    walking             INT NOT NULL,
    bending             INT NOT NULL,
    standing            INT NOT NULL,
    getting_up          INT NOT NULL,
    occupation          INT NOT NULL,
    housework           INT NOT NULL,
    leisure_activities  INT NOT NULL,
    leisure_examples    TEXT,
    dependency          INT NOT NULL,
    appearance          INT NOT NULL,
    clothes_fit_difficulty  INT NOT NULL,
    clothes_preference_difficulty   INT NOT NULL,
    shoes_fit_difficulty    INT NOT NULL,
    socks_fit_difficulty    INT NOT NULL,
    self_perception         INT NOT NULL,
    relationship_impact     INT NOT NULL,
    pain                INT NOT NULL,
    numbness            INT NOT NULL,
    pins_needles        INT NOT NULL,
    leg_weakness        INT NOT NULL,
    leg_heaviness       INT NOT NULL,
    sleep_trouble       INT NOT NULL,
    difficulty_concentrating INT NOT NULL,
    feeling_tense       INT NOT NULL,
    feeling_worried     INT NOT NULL,
    feeling_irritable   INT NOT NULL,
    feeling_depressed   INT NOT NULL,
    quality_of_life     INT NOT NULL
) engine = InnoDB;

create table if not exists cellulitis_incident_responses
(
    cellulitis_incident_response_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_of_cellulitis TEXT NOT NULL,
    area_affected TEXT NOT NULL,
    redness TEXT NOT NULL,
    pain_discomfort TEXT NOT NULL,
    warm_touch TEXT NOT NULL,
    swelling_worsen TEXT NOT NULL,
    blisters TEXT NOT NULL,
    raised_temperature TEXT NOT NULL,
    flu_symptoms TEXT NOT NULL,
    advice_visit TEXT NOT NULL,
    oral_antibiotics TEXT NOT NULL,
    course_duration TEXT NOT NULL,
    iv_antibiotics TEXT NOT NULL,
    hospital_admission TEXT NOT NULL,
    lymphoedema_clinic_contact TEXT NOT NULL,
    comments TEXT NOT NULL
) engine = InnoDB;

create table if not exists preappointment_cellulitis_incident_responses
(
    preappointment_questionnaire_response_id BIGINT NOT NULL,
    cellulitis_incident_response_id BIGINT NOT NULL
) engine = InnoDB;