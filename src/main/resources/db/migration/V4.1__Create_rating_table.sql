CREATE TABLE "rating"(
    "user_id" INTEGER NOT NULL,
    "movie_id" INTEGER NOT NULL,
    "rating" DECIMAL(8, 1) NOT NULL
);

ALTER TABLE "rating" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE "rating" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");