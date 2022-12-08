CREATE TABLE "movie_country"(
    "movie_id" INTEGER NOT NULL,
    "country_id" INTEGER NOT NULL
);

ALTER TABLE "movie_country" add CONSTRAINT "country_id" FOREIGN KEY("country_id") REFERENCES "country"("id");
ALTER TABLE "movie_country" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");