CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "description" VARCHAR(500) NOT NULL
);

CREATE SEQUENCE review_id_sequence OWNED BY review.id;
ALTER SEQUENCE review_id_sequence INCREMENT BY 50;

ALTER TABLE review ADD COLUMN "movie_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE review ADD COLUMN "user_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id);
