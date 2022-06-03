/*  db script for scando */

/* ------- Application Registration -------*/


CREATE TABLE tbl_application (
    app_id	BIGSERIAL PRIMARY KEY,
    device_id	VARCHAR(50),
    os_info	VARCHAR(30),
    device_info	TEXT,
    device_token TEXT,
    app_version VARCHAR(30),
    count	INTEGER,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*--------------------------------------------*/


/*--------------- User Login Info-------------*/

CREATE TABLE tbl_user_login_info (
    login_id BIGSERIAL PRIMARY KEY,
    user_id	BIGINT NOT NULL,
    app_id BIGINT NOT NULL,
    token	TEXT,
    refresh_token TEXT,
    expiry_time	DOUBLE PRECISION,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*----------------------------------------------*/


/*------------------Archived User Login Info-----*/

CREATE TABLE tbl_archive_user_login_info (
    archive_id BIGSERIAL PRIMARY KEY,
    login_id BIGINT,
    app_id BIGINT,
    user_id	BIGINT,
    token	TEXT,
    refresh_token TEXT,
    expiry_time	DOUBLE PRECISION,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION,
    archived_date DOUBLE PRECISION
);

/*------------------------------------------------*/


/*--------------- User--------------------------*/

CREATE TABLE tbl_user (
    user_id	BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(13) NOT NULL UNIQUE,
    user_type SMALLINT,
    name VARCHAR(50),
    user_status	SMALLINT,
    profile_pic_url	VARCHAR(100),
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*----------------------------------------------*/


/*--------------Subject--------------------------*/

CREATE TABLE tbl_subject (
    subject_id	BIGSERIAL PRIMARY KEY,
    subject_code VARCHAR(10),
    name VARCHAR(30),
    description	TEXT,
    created_by	VARCHAR(50),
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*------------------------------------------------*/


/*------------- User Core Subject ----------------*/

CREATE TABLE tbl_user_core_subject (
    core_sub_id	BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subject_id	BIGINT NOT NULL,
    subject_code VARCHAR(10),
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*---------------------------------------------------*/


/*--------------- Class ------------------------------*/

CREATE TABLE tbl_class (
    class_id BIGINT PRIMARY KEY,
    teacher_id	BIGINT NOT NULL,
    class_description TEXT,
    class_name	VARCHAR(50),
    subject_name VARCHAR(50),
    status	SMALLINT,
    class_type SMALLINT,
    is_scheduled SMALLINT,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

ALTER TABLE tbl_class ALTER COLUMN class_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "class_id_9_digit"
    START WITH 100000001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

/*------------------------------------------------------*/


/*---------------- Class Enroll -------------------------*/

CREATE TABLE tbl_class_enroll (
    enroll_id BIGSERIAL PRIMARY KEY,
    class_id BIGINT NOT NULL,
    student_id	BIGINT NOT NULL,
    enroll_status	SMALLINT,
    teacher_id	BIGINT NOT NULL,
    enroll_time	DOUBLE PRECISION,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*----------------------------------------------------------*/


/*---------------- Archived Class Enroll -------------------------*/

CREATE TABLE tbl_archive_class_enroll (
    archive_id BIGSERIAL PRIMARY KEY,
    enroll_id BIGINT NOT NULL,
    class_id BIGINT NOT NULL,
    student_id	BIGINT NOT NULL,
    enroll_status	SMALLINT,
    teacher_id	BIGINT NOT NULL,
    enroll_time	DOUBLE PRECISION,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION,
    archived_date DOUBLE PRECISION
);

/*----------------------------------------------------------*/


/*--------------------- Class Doubt ---------------------------*/

CREATE TABLE tbl_class_doubt (
    doubt_id BIGSERIAL PRIMARY KEY,
    class_id	BIGINT NOT NULL,
    student_id	BIGINT NOT NULL,
    teacher_id	BIGINT NOT NULL,
    doubt_code	VARCHAR(10),
    doubt_description TEXT,
    doubt_answer TEXT,
    doubt_status SMALLINT,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION 
);

/*-----------------------------------------------------------------*/


/*-------------------- Study Material ------------------------------*/

CREATE TABLE tbl_study_material (
    material_id	BIGSERIAL PRIMARY KEY,
    class_id BIGINT NOT NULL,
    teacher_id	BIGINT NOT NULL,
    scheduled_date	DATE,
    material_code	VARCHAR(10),
    material_type	SMALLINT,
    material_url	TEXT,
    created	DOUBLE PRECISION,
    updated	DOUBLE PRECISION
);

/*---------------------------------------------------------------------*/


/*--------------------- Class Video --------------------------------------*/

CREATE TABLE tbl_class_video (
  video_id	BIGSERIAL PRIMARY KEY,
  class_id	BIGINT NOT NULL,
  teacher_id BIGINT NOT NULL,
  day DOUBLE PRECISION,
  start_time	DOUBLE PRECISION,
  end_time	DOUBLE PRECISION,
  duration	DOUBLE PRECISION,
  url TEXT,
  created	DOUBLE PRECISION,
  updated	DOUBLE PRECISION
);

/*------------------------------------------------------------------------*/


/*-------------------------Class Scheduled-------------------------------------*/

CREATE TABLE tbl_class_scheduled (
   schedule_id	BIGSERIAL PRIMARY KEY,
   class_id	BIGINT NOT NULL,
   teacher_id	BIGINT NOT NULL,
   day VARCHAR(2),
   sl_no SMALLINT,
   s_hr VARCHAR(2),
   s_min VARCHAR(2),
   e_hr VARCHAR(2),
   e_min VARCHAR(2),
   repeat SMALLINT,
   created	DOUBLE PRECISION,
   updated	DOUBLE PRECISION 
);

/*--------------------------------------------------------------------------------*/


/*----------------------------- OTP ---------------------------------------------*/

CREATE TABLE tbl_otp (
 otp_id	BIGSERIAL PRIMARY KEY,
 phone_number VARCHAR(13),
 otp_generated	INTEGER,
 status	SMALLINT,
 expiry_time DOUBLE PRECISION,
 regenerated_status	INTEGER,
 created	DOUBLE PRECISION,
 updated	DOUBLE PRECISION
);

/*-----------------------------------------------------------------------------------*/


/*----------------------------- Archived OTP ---------------------------------------------*/

CREATE TABLE tbl_archive_otp (
 archive_id	BIGSERIAL PRIMARY KEY,
 otp_id BIGINT,
 phone_number VARCHAR(13),
 otp_generated	INTEGER,
 status	SMALLINT,
 expiry_time DOUBLE PRECISION,
 regenerated_status	INTEGER,
 created	DOUBLE PRECISION,
 updated	DOUBLE PRECISION,
 archived_date DOUBLE PRECISION  
);

/*-----------------------------------------------------------------------------------*/


/*--------------------------- S3 Request Table --------------------------------------*/

CREATE TABLE tbl_s3_request (
 request_id	BIGSERIAL PRIMARY KEY,
 url TEXT,
 created DOUBLE PRECISION,
 updated DOUBLE PRECISION 
);

/*------------------------------------------------------------------------------------*/

/*------------------Foregin Key Constraints-------------------------------------------*/

/* user login info */
ALTER TABLE tbl_user_login_info 
ADD CONSTRAINT user_user_login_info_FOR_KEY
FOREIGN KEY(user_id)
REFERENCES tbl_user (user_id);

ALTER TABLE tbl_user_login_info
ADD CONSTRAINT app_user_login_info_FOR_KEY
FOREIGN KEY(app_id)
REFERENCES tbl_application(app_id);


/* class */
ALTER TABLE tbl_class
ADD CONSTRAINT user_class_FOR_KEY
FOREIGN KEY(teacher_id)
REFERENCES tbl_user(user_id);


/* user core subject */
ALTER TABLE tbl_user_core_subject
ADD CONSTRAINT user_core_subject_id_FOR_KEY
FOREIGN KEY(subject_id)
REFERENCES tbl_subject(subject_id);


ALTER TABLE tbl_user_core_subject
ADD CONSTRAINT user_core_subject_user_FOR_KEY
FOREIGN KEY(user_id)
REFERENCES tbl_user(user_id);


/* class doubt */
ALTER TABLE tbl_class_doubt
ADD CONSTRAINT user_class_doubt_student_FOR_KEY
FOREIGN KEY(student_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_doubt
ADD CONSTRAINT user_class_doubt_teacher_FOR_KEY
FOREIGN KEY (teacher_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_doubt
ADD CONSTRAINT class_tbl_class_doubt_FOR_KEY
FOREIGN KEY(class_id)
REFERENCES tbl_class(class_id);



/* class enroll */
ALTER TABLE tbl_class_enroll
ADD CONSTRAINT user_class_enroll_student_FOR_KEY
FOREIGN KEY(student_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_enroll
ADD CONSTRAINT user_class_enroll_teacher_FOR_KEY
FOREIGN KEY (teacher_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_enroll
ADD CONSTRAINT class_class_enroll_FOR_KEY
FOREIGN KEY(class_id)
REFERENCES tbl_class(class_id);


/* study material */

ALTER TABLE tbl_study_material
ADD CONSTRAINT user_class_material_teacher_FOR_KEY
FOREIGN KEY (teacher_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_study_material
ADD CONSTRAINT class_class_material_FOR_KEY
FOREIGN KEY (class_id)
REFERENCES tbl_class (class_id);


/*  class video */
ALTER TABLE tbl_class_video
ADD CONSTRAINT user_class_video_teacher_FOR_KEY
FOREIGN KEY (teacher_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_video
ADD CONSTRAINT class_class_video_FOR_KEY
FOREIGN KEY(class_id)
REFERENCES tbl_class(class_id);


/*  class scheduled */
ALTER TABLE tbl_class_scheduled
ADD CONSTRAINT user_class_scheduled_teacher_FOR_KEY
FOREIGN KEY (teacher_id)
REFERENCES tbl_user(user_id);

ALTER TABLE tbl_class_scheduled
ADD CONSTRAINT class_class_scheduled_FOR_KEY
FOREIGN KEY(class_id)
REFERENCES tbl_class(class_id);
