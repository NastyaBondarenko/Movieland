ALTER TABLE genre ADD COLUMN "movie_id" INTEGER;
ALTER TABLE genre ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE country ADD COLUMN "movie_id" INTEGER;
ALTER TABLE country ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);
--
ALTER TABLE review ADD COLUMN "movie_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE review ADD COLUMN "user_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id);

--ALTER TABLE review ADD COLUMN "user_id" INTEGER;
--ALTER TABLE review ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "user" (id);

--
--CREATE TABLE movies_countries
--(
--    movie_id   INTEGER NOT NULL,
--    country_id INTEGER NOT NULL,
--    CONSTRAINT pk_movies_countries PRIMARY KEY (movie_id, country_id)
--);
--
--CREATE TABLE movies_genres
--(
--    movie_id INTEGER NOT NULL,
--    genre_id INTEGER NOT NULL,
--    CONSTRAINT pk_movies_genres PRIMARY KEY (movie_id, genre_id)
--);
--
--ALTER TABLE movies_countries
--    ADD CONSTRAINT FK_MOVIES_COUNTRIES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES country (id);
--
--ALTER TABLE movies_countries
--    ADD CONSTRAINT FK_MOVIES_COUNTRIES_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);