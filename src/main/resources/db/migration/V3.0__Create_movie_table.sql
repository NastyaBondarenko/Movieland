CREATE TABLE "movie"(
    "id" INTEGER PRIMARY KEY,
    "name_russian" VARCHAR(100) NOT NULL,
    "name_native" VARCHAR(100) NOT NULL,
    "year_of_release" DATE NOT NULL,
    "description" VARCHAR(1000) NOT NULL,
    "rating" DECIMAL(8, 1)  NOT NULL,
    "price" DECIMAL(8, 2)  NOT NULL,
    "picture_path" VARCHAR(255) NOT NULL,
    "votes" INTEGER NOT NULL
);

CREATE SEQUENCE movies_id_sequence OWNED BY movie.id;
ALTER SEQUENCE movies_id_sequence INCREMENT BY 50;