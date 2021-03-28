-- Test Table.
DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');



-- State Table.
DROP TABLE if EXISTS states;

CREATE TABLE states (
    id INT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

INSERT INTO states (id, name) VALUES
    (0, 'New York'),
    (1, 'Florida'),
    (2, 'Texas');


-- Job Table.
DROP TABLE if EXISTS jobs;

CREATE TABLE jobs (
    id INT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    mggg_param_id INT
);

INSERT INTO jobs (id, name, mggg_param_id) VALUES
    (0, 'test1', 0),
    (1, 'test2', 1),
    (2, 'job1', 0),
    (3, 'job2', 1);
