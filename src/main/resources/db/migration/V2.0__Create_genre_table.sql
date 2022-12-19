CREATE TABLE "genre"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE SEQUENCE genres_id_sequence OWNED BY genre.id;
ALTER TABLE genre ALTER COLUMN id SET DEFAULT nextval('genres_id_sequence');
ALTER SEQUENCE genres_id_sequence INCREMENT BY 50;