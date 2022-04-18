CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description text,
   created text,
   visible boolean,
   city_id int references city (id)
);