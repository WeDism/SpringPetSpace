-- CREATE DATABASE pet_space;

DROP TABLE IF EXISTS status_essence CASCADE;
DROP TABLE IF EXISTS role_essence CASCADE;
DROP TABLE IF EXISTS user_essence CASCADE;
DROP TABLE IF EXISTS state_friend CASCADE;
DROP TABLE IF EXISTS friends CASCADE;
DROP TABLE IF EXISTS genus_pet CASCADE;
DROP TABLE IF EXISTS pet CASCADE;
DROP TABLE IF EXISTS follow_pets CASCADE;
DROP TABLE IF EXISTS date_of_message CASCADE;
DROP TABLE IF EXISTS message CASCADE;
DROP TABLE IF EXISTS message_of_user CASCADE;
DROP TABLE IF EXISTS message_state CASCADE;

CREATE TABLE status_essence (
  status VARCHAR(100) PRIMARY KEY
);

CREATE TABLE role_essence (
  role VARCHAR(100) PRIMARY KEY
);

CREATE TABLE user_essence (
  user_essence_id UUID PRIMARY KEY,
  nickname        VARCHAR(300)   NOT NULL UNIQUE,
  name            VARCHAR(200)   NOT NULL,
  surname         VARCHAR(200)   NOT NULL,
  patronymic      VARCHAR(200),
  password        VARCHAR(32)  NOT NULL,
  email           VARCHAR(255) NOT NULL,
  phone           VARCHAR(15),
  birthday        TIMESTAMP,
  about_of_self   TEXT,
  role            VARCHAR(100)   NOT NULL REFERENCES role_essence (role),
  status          VARCHAR(100)   NOT NULL REFERENCES status_essence (status),
  CONSTRAINT numbers CHECK (phone ~* '^[0-9]')
);

CREATE TABLE state_friend (
  state VARCHAR(100) PRIMARY KEY
);

CREATE TABLE friends (
  user_essence_id UUID REFERENCES user_essence (user_essence_id),
  friend_id       UUID REFERENCES user_essence (user_essence_id),
  state          VARCHAR(100) NOT NULL REFERENCES state_friend (state),
  CONSTRAINT pk_friends_id PRIMARY KEY (user_essence_id, friend_id)
);

CREATE TABLE genus_pet (
  name VARCHAR(200) PRIMARY KEY
);

CREATE TABLE pet (
  pet_id          UUID PRIMARY KEY,
  name            VARCHAR(200) NOT NULL,
  weight          REAL,
  birthday        TIMESTAMP,
  user_essence_id UUID         NOT NULL REFERENCES user_essence (user_essence_id),
  genus         VARCHAR(200) NOT NULL REFERENCES genus_pet (name),
  CONSTRAINT pk_pet_name_per_user UNIQUE (name, user_essence_id)
);

CREATE TABLE follow_pets (
  pet_id          UUID REFERENCES pet (pet_id),
  user_essence_id UUID REFERENCES user_essence (user_essence_id),
  CONSTRAINT pk_follow_pets_id PRIMARY KEY (pet_id, user_essence_id)
);

CREATE TABLE message (
  message_id UUID PRIMARY KEY,
  text       TEXT,
  date       TIMESTAMP DEFAULT now(),
  author_id  UUID NULL REFERENCES user_essence (user_essence_id)
);

CREATE TABLE message_state (
  state VARCHAR(100) PRIMARY KEY
);

CREATE TABLE message_of_user (
  message_id UUID REFERENCES message (message_id),
  owner_id   UUID REFERENCES user_essence (user_essence_id),
  state VARCHAR(100) REFERENCES message_state (state),
  CONSTRAINT pk_message_of_user_id PRIMARY KEY (message_id, owner_id)
);

INSERT INTO message_state VALUES ('NEW'), ('VIEWED');
INSERT INTO role_essence VALUES ('ROOT'), ('ADMIN'), ('USER');
INSERT INTO status_essence VALUES ('ACTIVE'), ('INACTIVE'), ('DELETED');
INSERT INTO state_friend VALUES ('REQUESTED'), ('REJECTED'), ('APPROVED');
INSERT INTO user_essence (user_essence_id, nickname, password, name, surname, email, role, status)
VALUES
  (uuid('8ae453ef-4a97-46e9-803d-8502a446e6dc'), 'root', 'root', 'Carl', 'Stanley', 'root@root', 'ROOT', 'ACTIVE');
INSERT INTO user_essence (user_essence_id, nickname, password, name, surname, email, role, status, about_of_self)
VALUES
  (uuid('14c88e00-a325-4ac7-8c04-a43bc72cdc4a'), 'user1', 'user1', 'Mark', 'Zero', 'user1@user1', 'USER', 'ACTIVE',
   'I know some of us like really small text. (cough cough me) Because honestly text this big does looks odd in most layouts.');
INSERT INTO user_essence (user_essence_id, nickname, password, name, surname, email, role, status, about_of_self)
VALUES
  (uuid('7c9499cd-7a66-497b-9557-fb6b059c82d4'), 'user2', 'user2', 'Steve', 'Johnson', 'user2@user2', 'USER', 'ACTIVE',
   'Some text should be');
INSERT INTO user_essence (user_essence_id, nickname, password, name, surname, email, role, status, about_of_self)
VALUES
  (uuid('7c20a4d7-5f9b-416f-a910-b13a816ba90b'), 'admin', 'admin', 'Petr', 'Stevenson', 'USER@USER', 'ADMIN', 'ACTIVE',
   ' Sometimes I''d see really cute layouts, with words so tiny that you wouldn''t get that from just clicking ''8'' in the size bar.');
--TODO create trigger for root on update and delete
