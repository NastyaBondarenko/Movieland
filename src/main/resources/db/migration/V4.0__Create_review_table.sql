CREATE TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "description" VARCHAR(500) NOT NULL
);

CREATE SEQUENCE review_id_sequence OWNED BY review.id;
ALTER TABLE review ALTER COLUMN id SET DEFAULT nextval('review_id_sequence');
ALTER SEQUENCE review_id_sequence INCREMENT BY 50;

--CREATE TABLE review
--(
--    id       INTEGER      NOT NULL,
--    movie_id INTEGER      NOT NULL,
--    user_id  INTEGER      NOT NULL,
--    description     VARCHAR(500) NOT NULL,
--    CONSTRAINT pk_review PRIMARY KEY (id)
--);
--
--
--ALTER TABLE review
--    ADD CONSTRAINT FK_REVIEW_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);
--
--ALTER TABLE review
--    ADD CONSTRAINT FK_REVIEW_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
