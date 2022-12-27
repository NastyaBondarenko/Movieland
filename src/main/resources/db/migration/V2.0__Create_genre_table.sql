CREATE TABLE "genre"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE SEQUENCE genres_id_sequence OWNED BY genre.id;
ALTER SEQUENCE genres_id_sequence INCREMENT BY 50;