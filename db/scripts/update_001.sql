DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description text,
   created text,
   visible boolean,
   city_id int references city (id)
);