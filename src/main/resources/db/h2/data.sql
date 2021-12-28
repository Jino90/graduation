DELETE FROM user_roles;
/*DELETE FROM meals;*/
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restraunt (name)
VALUES ('Pikadili'),
       ('Zhiguli'),
       ('Griboedov');

INSERT INTO menu (user_id, restraunt_id, votes)
VALUES (100001, 1, 0),
       (100001, 2, 0),
       (100001, 3, 0);

INSERT INTO dish (name, price, menu_id)
VALUES ('Oduvanchiki pod sousom karri', 2, 1),
       ('Mozg karpa', 5, 1),
       ('Vodka s ogurchikami i chesnokom', 3, 2),
       ('Vodka s pivom i percem', 2, 2),
       ('Vodka s vassabi', 1, 2),
       ('Vodka s krabom', 6, 2),
       ('Rassol and trufeli', 15, 2),
       ('Zhulen', 6, 3),
       ('Lisi4ki v mayoneze', 4, 3),
       ('Shitaki s ovoschami', 12, 3);