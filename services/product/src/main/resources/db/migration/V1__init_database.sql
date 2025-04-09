CREATE TABLE if NOT exists category
(
    id INTEGER not NULL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE if NOT exists product
(
    id INTEGER not NULL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255),
    available_quantity DOUBLE precision NOT NULL,
    price NUMERIC(38,2),
    category_id INTEGER 
            constraint fk1fhjdjsjksksj REFERENCES category
);

CREATE SEQUENCE if NOT EXISTS category_seq INCREMENT by 50;
CREATE SEQUENCE if NOT EXISTS product_seq INCREMENT by 50;