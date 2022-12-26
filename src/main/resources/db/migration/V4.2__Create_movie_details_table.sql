
--ALTER TABLE country ADD COLUMN "movie_id" INTEGER;
--ALTER TABLE country ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);
--
ALTER TABLE review ADD COLUMN "movie_id" INTEGER;
ALTER TABLE review ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);

--ALTER TABLE genre ADD COLUMN "movie_id" INTEGER;
--ALTER TABLE genre ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movie (id);


--ALTER TABLE review ADD COLUMN "user_id" INTEGER;
--ALTER TABLE review ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id);

