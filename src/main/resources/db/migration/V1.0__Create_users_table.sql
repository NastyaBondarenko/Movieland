CREATE TABLE users(
    "id" INTEGER PRIMARY KEY,
    "nickname" VARCHAR(45) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "password" VARCHAR(100) NOT NULL
);

CREATE SEQUENCE user_id_sequence OWNED BY users.id;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('user_id_sequence');
ALTER SEQUENCE user_id_sequence INCREMENT BY 50;