/*
Spring runt dit SQL-bestand automatisch. Spring zoekt namelijk naar een bestand dat data.sql heet (in deze folder) en
voert de SQL commando's voor je uit. Dit is een van de manier om de database te vullen. Om dit te laten werken, is het
volgende aan application.properties toegevoegd:

spring.datasource.initialization-mode=always

Zoals gezegd, dit is een van de manieren. De huidige opzet avn deze applicatie heeft een vast aantal user-rollen. Deze
kunnen niet door eindgebruikers, moderators of admins toegevoegd worden. Alleen de programmeur kan user-rollen
toevoegen. Daarom is er ook geen Service & repo voor de user-rollen geprogrammeerd. De enige manier om dan iets in de
database te krijgen is via SQL statements in dit bestand.

 */
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_MODERATOR');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- INSERT INTO app_user (username, password) VALUES ('user@fc.nl', '$2y$12$M.CRkE0cEBMqxlESoygFGeFBtcwbh/ZYF8hM1d4NcNsC5s3uJar5a');
-- INSERT INTO app_user (username, password) VALUES ('teacher@fc.nl', '$2a$12$aj2Te5Qguo9nJfgcF8Ob2uqq4GfFf.0PEfhxJtpQWHE/mVirA/pUq');
-- INSERT INTO app_user (username, password) VALUES ('admin@fc.nl', '$2y$12$z0URDv1FPzbapk0MbpaRf.SxXU5AFFe8hBKNreHlv05j5tS5SUV8G');
--
-- INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
-- INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
-- INSERT INTO user_role (user_id, role_id) VALUES (3, 3);

