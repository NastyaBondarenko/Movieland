CREATE TABLE users(
    "id" INTEGER PRIMARY KEY,
    "nickname" VARCHAR(45) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "password" VARCHAR(100) NOT NULL
);

CREATE TABLE "genre"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE TABLE "movie"(
    "id" INTEGER PRIMARY KEY,
    "name_russian" VARCHAR(100) NOT NULL,
    "name_native" VARCHAR(100) NOT NULL,
    "year_of_release" DATE NOT NULL,
    "description" VARCHAR(1000) NOT NULL,
    "rating" DECIMAL(10, 1)  NOT NULL,
    "price" DECIMAL(10, 2)  NOT NULL,
    "picture_path" VARCHAR(255) NOT NULL,
    "votes" INTEGER NOT NULL
);

CREATE TABLE "movie_genre"(
    "movie_id" INTEGER NOT NULL,
    "genre_id" INTEGER NOT NULL
);

CREATE TABLE "country"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE TABLE "movie_country"(
    "movie_id" INTEGER NOT NULL,
    "country_id" INTEGER NOT NULL
);

CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "description" VARCHAR(500) NOT NULL
);

CREATE TABLE "rating"(
    "user_id" INTEGER NOT NULL,
    "movie_id" INTEGER NOT NULL,
    "rating" DECIMAL(10, 1) NOT NULL
);

CREATE SEQUENCE user_id_sequence OWNED BY users.id;
ALTER SEQUENCE user_id_sequence INCREMENT BY 50;

CREATE SEQUENCE genres_id_sequence OWNED BY genre.id;
ALTER SEQUENCE genres_id_sequence INCREMENT BY 50;

CREATE SEQUENCE movies_id_sequence OWNED BY movie.id;
ALTER SEQUENCE movies_id_sequence INCREMENT BY 50;

ALTER TABLE "movie_genre" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
ALTER TABLE "movie_genre" add CONSTRAINT "genre_id" FOREIGN KEY("genre_id") REFERENCES "genre"("id");

CREATE SEQUENCE country_id_sequence OWNED BY country.id;
ALTER SEQUENCE country_id_sequence INCREMENT BY 50;

ALTER TABLE "movie_country" add CONSTRAINT "country_id" FOREIGN KEY("country_id") REFERENCES "country"("id");
ALTER TABLE "movie_country" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");

CREATE SEQUENCE review_id_sequence OWNED BY review.id;
ALTER SEQUENCE review_id_sequence INCREMENT BY 50;

ALTER TABLE review ADD COLUMN "movie_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE review ADD COLUMN "user_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE "rating" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE "rating" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");

ALTER TABLE users ADD COLUMN "role" VARCHAR(255);
