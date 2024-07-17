use nhsdb;
drop table if exists user_table;
drop table if exists roles_table;
drop table if exists users_roles;
drop table if exists treatment_plans;
drop table if exists exercises;
drop table if exists videos;
drop table if exists treatment_plan_exercises;
drop table if exists user_authorities;
drop table if exists pre_appointment_questionnaire;

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

create table if not exists qol_responses
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    walking             BIGINT NOT NULL,
    bending             BIGINT NOT NULL,
    standing            BIGINT NOT NULL,
    getting_up          BIGINT NOT NULL,
    occupation          BIGINT NOT NULL,
    housework           BIGINT NOT NULL,
    leisure_activities  TEXT,
    dependency          BIGINT NOT NULL,
    appearance          BIGINT NOT NULL,
    clothes_fit_difficulty  BIGINT NOT NULL,
    clothes_preference_difficulty   BIGINT NOT NULL,
    shoes_fit_difficulty    BIGINT NOT NULL,
    socks_fit_difficulty    BIGINT NOT NULL,
    self_perception         BIGINT NOT NULL,
    relationship_impact     BIGINT NOT NULL,
    pain                BIGINT NOT NULL,
    numbness            BIGINT NOT NULL,
    pins_needles        BIGINT NOT NULL,
    leg_weakness        BIGINT NOT NULL,
    leg_heaviness       BIGINT NOT NULL,
    sleep_trouble       BIGINT NOT NULL,
    difficulty_concentrating BIGINT NOT NULL,
    feeling_tense       BIGINT NOT NULL,
    feeling_worried     BIGINT NOT NULL,
    feeling_irritable   BIGINT NOT NULL,
    feeling_depressed   BIGINT NOT NULL,
    quality_of_life     BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
