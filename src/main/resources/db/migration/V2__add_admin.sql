INSERT INTO users (id, archive, email, name, password, pole, bucket_id)
VALUES (1, false,'mail@mail.ru', 'admin', 'pass', 'ADMIN', NULL);
ALTER SEQUENCE user_seq RESTART WITH 2;