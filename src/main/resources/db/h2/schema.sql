DROP TABLE user_roles IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restraunt IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;
CREATE SEQUENCE RESTRAUNT_SEQ AS INTEGER START WITH 1;
CREATE SEQUENCE MENU_SEQ AS INTEGER START WITH 1;
CREATE SEQUENCE DISHES_SEQ AS INTEGER START WITH 1;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOLEAN   DEFAULT TRUE  NOT NULL,
    calories_per_day INTEGER   DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restraunt
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE RESTRAUNT_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL
);

CREATE TABLE menu
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE MENU_SEQ PRIMARY KEY,
    user_id     INTEGER   NOT NULL,
    name             VARCHAR(255)            NOT NULL,
    restraunt_id             INTEGER            NOT NULL,
    FOREIGN KEY (restraunt_id) REFERENCES restraunt (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX menu_unique_restraunt_id_idx
    ON menu (id, restraunt_id);

CREATE TABLE dishes
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE DISHES_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    price     INTEGER      NOT NULL,
    menu_id             INTEGER            NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX dishes_unique_id_menu_idx
    ON dishes (id, menu_id);



