BEGIN TRANSACTION;

DROP TABLE IF EXISTS timesheet;
DROP TABLE IF EXISTS user_project;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
   user_id SERIAL,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   first_name varchar,
   last_name varchar,
   email varchar,
   avatar varchar,
   manager_id int,
   manager_first_name varchar,
   manager_last_name varchar,

  CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE projects (
  project_id SERIAL,
  project_name varchar,
  project_desc varchar,
  project_img varchar,
  end_date varchar,
  shared boolean DEFAULT 'false',
  CONSTRAINT pk_project PRIMARY KEY (project_id)
);

CREATE TABLE user_project (
  project_id int,
  user_id int,
  PRIMARY KEY(project_id, user_id),
  CONSTRAINT FK_user_id2 FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT FK_project_id2 FOREIGN KEY (project_id) REFERENCES projects (project_id)
);

CREATE TABLE timesheet (
  time_id SERIAL,
  project_id int NOT NULL,
  user_id int NOT NULL,
  time_desc varchar,
  beginning_time timestamp NOT NULL DEFAULT now(),
  ending_time timestamp,
  CONSTRAINT pk_time_id PRIMARY KEY (time_id),
  CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT FK_project_id FOREIGN KEY (project_id) REFERENCES projects (project_id)
);

COMMIT TRANSACTION;