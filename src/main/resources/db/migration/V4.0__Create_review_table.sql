CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "movie_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "description" VARCHAR(500) NOT NULL
);
