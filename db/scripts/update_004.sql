insert into city (id, name) VALUES (1, 'Москва'), (2, 'СПб'), (3, 'Екб');

select * from city;

DELETE FROM city;

INSERT INTO city DEFAULT VALUES;

DROP TABLE IF EXISTS city;

CREATE TABLE IF NOT EXISTS city (
                                    id SERIAL PRIMARY KEY,
                                    name TEXT
);