create TABLE "movie_details"(
     "id" INTEGER PRIMARY KEY,
     "movie_id" INTEGER NOT NULL,
     "country_id" INTEGER NOT NULL,
     "genre_id" INTEGER NOT NULL,
     "review_id" INTEGER NOT NULL
);

ALTER TABLE genre ADD COLUMN "movie_details_id" INTEGER;
ALTER TABLE genre ADD CONSTRAINT movie_details_id FOREIGN KEY (movie_details_id) REFERENCES movie_details (id);

ALTER TABLE country ADD COLUMN "movie_details_id" INTEGER;
ALTER TABLE country ADD CONSTRAINT movie_details_id FOREIGN KEY (movie_details_id) REFERENCES movie_details (id);

ALTER TABLE review ADD COLUMN "movie_details_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT movie_details_id FOREIGN KEY (movie_details_id) REFERENCES movie_details (id);