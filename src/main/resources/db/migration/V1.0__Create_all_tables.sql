create TABLE "user"(
    "id" INTEGER PRIMARY KEY,
    "nickname" VARCHAR(45) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "password" VARCHAR(100) NOT NULL
);

create TABLE "movie"(
    "id" INTEGER PRIMARY KEY,
    "name_russian" VARCHAR(100) NOT NULL,
    "name_native" VARCHAR(100) NOT NULL,
    "year_of_release" DATE NOT NULL,
    "description" VARCHAR(1000) NOT NULL,
    "rating" DECIMAL(8, 1)  NOT NULL,
    "price" DECIMAL(8, 2)  NOT NULL,
    "picture_path" VARCHAR(255) NOT NULL,
    "votes" INTEGER NOT NULL
);

create TABLE "genre"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);


create TABLE "movie_genre"(
    "movie_id" INTEGER NOT NULL,
    "genre_id" INTEGER NOT NULL
);


create TABLE "review"(
    "id" INTEGER PRIMARY KEY,
    "movie_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "description" VARCHAR(500) NOT NULL
);

create TABLE "country"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

create TABLE "movie_country"(
    "movie_id" INTEGER NOT NULL,
    "country_id" INTEGER NOT NULL
);


create TABLE "rating"(
    "user_id" INTEGER NOT NULL,
    "movie_id" INTEGER NOT NULL,
    "rating" DECIMAL(8, 1) NOT NULL
);

alter table "movie_genre" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
alter table "movie_genre" add CONSTRAINT "genre_id" FOREIGN KEY("genre_id") REFERENCES "genre"("id");

alter table "review" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "user"("id");
alter table "review" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");

alter table "movie_country" add CONSTRAINT "country_id" FOREIGN KEY("country_id") REFERENCES "country"("id");
alter table "movie_country" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");

alter table "rating" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "user"("id");
alter table "rating" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
