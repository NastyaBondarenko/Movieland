CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "movie_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "description" VARCHAR(500) NOT NULL
);

ALTER TABLE "review" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE "review" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
