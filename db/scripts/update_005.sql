CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  email varchar(127),
  password TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);