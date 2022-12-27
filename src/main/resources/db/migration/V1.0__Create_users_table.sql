CREATE TABLE users(
    "id" INTEGER PRIMARY KEY,
    "nickname" VARCHAR(45) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "password" VARCHAR(100) NOT NULL
);

CREATE SEQUENCE user_id_sequence OWNED BY users.id;
ALTER SEQUENCE user_id_sequence INCREMENT BY 50;