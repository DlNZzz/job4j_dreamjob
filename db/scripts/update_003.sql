DROP TABLE IF EXISTS candidate;

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   photo bytea,
   description text,
   created text
);