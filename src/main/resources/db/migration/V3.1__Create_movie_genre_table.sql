CREATE TABLE "movie_genre"(
    "movie_id" INTEGER NOT NULL,
    "genre_id" INTEGER NOT NULL
);

ALTER TABLE "movie_genre" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
ALTER TABLE "movie_genre" add CONSTRAINT "genre_id" FOREIGN KEY("genre_id") REFERENCES "genre"("id");