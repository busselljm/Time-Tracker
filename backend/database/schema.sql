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
   is_manager boolean DEFAULT 'false',
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

INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('bross', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'Bob', 'Ross', 'bob.ross@kronotrakr.com', 'https://yt3.ggpht.com/ytc/AAUvwnhkZjfj3AhZNOvbxzIzVLTKZZHGLAlIHVstuYx1=s900-c-k-c0x00ffffff-no-rj');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('dbowie', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'David', 'Bowie', 'david.bowie@kronotrakr.com', 'https://i.pinimg.com/originals/31/70/5a/31705a0afbaee3a1673748f69481c9e1.jpg');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('lperg', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'Laura', 'Pergolizzi', 'laura.pergolizzi@kronotrakr.com', 'https://s.inyourpocket.com/img/figure/2018-06/lp3.jpg');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('ccorn', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'Chris', 'Cornell', 'chris.cornell@kronotrakr.com', 'https://media.npr.org/assets/img/2015/09/10/chriscornell_sq-e12d4f81f407d6d3cc412ed61f7d01233c2ed924-s800-c85.jpeg');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('ddraim', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'David', 'Draiman', 'david.draiman@kronotrakr.com', 'https://i.pinimg.com/736x/7a/75/95/7a75954e65ab1be01efe83560785f3c6.jpg');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('abaraz', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'Alina', 'Baraz', 'alina.baraz@kronotrakr.com', 'https://img.discogs.com/rjfM6x-OLtjW4-5uDB4XDddQlVI=/600x740/smart/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/A-3786675-1587919159-6933.jpeg.jpg');
INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar)
VALUES ('bbern', '$2a$10$gVXsoYqfWm5DZukoT5hNNuxH63JdK.z5Fa0FdvjWu28Do0o0DZUc2', 'ROLE_USER', 'BMO', 'Berning', 'bmo.berning@kronotrakr.com', 'https://i.ibb.co/vJpyQPZ/IMG-1192.jpg');


INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('Joy of Painting','Learn to paint with oil paints led by instructor Bob Ross','https://upload.wikimedia.org/wikipedia/en/thumb/8/86/The_Joy_of_Painting_title_screen.jpg/250px-The_Joy_of_Painting_title_screen.jpg', 'Ongoing');
INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('The Man Who Sold the World','The third ablum in the written by Bowie' ,'https://images-na.ssl-images-amazon.com/images/I/718coMCsbUL._SL1200_.jpg', 'Ongoing');
INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('Aladdin Sane','The sixth ablum in the written by Bowie' ,'https://centaur-wp.s3.eu-central-1.amazonaws.com/designweek/prod/content/uploads/2016/01/11163836/aladdin-sane-album-cover-david-bowie.jpg', 'Ongoing');
INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('Lost on You','The fourth ablum in the written by LP' ,'https://i1.sndcdn.com/artworks-000216747177-ofg9gh-t500x500.jpg', 'Ongoing');
INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('Hearth to Mouth','The fifth ablum in the written by LP' ,'https://upload.wikimedia.org/wikipedia/en/thumb/e/e5/LP_%E2%80%93_Heart_to_Mouth.png/220px-LP_%E2%80%93_Heart_to_Mouth.png', 'Ongoing');
INSERT INTO projects (project_name, project_desc, project_img, end_date) VALUES
('The One That You Love','A recent single written by LP' ,'https://images.genius.com/2642b1e0d091b808b3bd30da65bff65a.1000x1000x1.jpg', 'Ongoing');


INSERT INTO user_project(project_id, user_id)
VALUES (1,1);
INSERT INTO user_project(project_id, user_id)
VALUES (2,2);
INSERT INTO user_project(project_id, user_id)
VALUES (3,2);
INSERT INTO user_project(project_id, user_id)
VALUES (4,3);
INSERT INTO user_project(project_id, user_id)
VALUES (5,3);
INSERT INTO user_project(project_id, user_id)
VALUES (6,3);

COMMIT TRANSACTION;