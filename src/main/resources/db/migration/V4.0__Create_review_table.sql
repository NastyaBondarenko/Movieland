CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "movie_id" INTEGER NOT NULL,
--    "user_id" INTEGER NOT NULL,
    "description" VARCHAR(500) NOT NULL
);

CREATE SEQUENCE review_id_sequence OWNED BY review.id;
ALTER TABLE review ALTER COLUMN id SET DEFAULT nextval('review_id_sequence');
ALTER SEQUENCE review_id_sequence INCREMENT BY 50;