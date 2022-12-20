CREATE TABLE "country"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE SEQUENCE country_id_sequence OWNED BY country.id;
ALTER TABLE country ALTER COLUMN id SET DEFAULT nextval('country_id_sequence');
ALTER SEQUENCE country_id_sequence INCREMENT BY 50;