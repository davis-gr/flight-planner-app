--liquibase formatted SQL

--changeset davis:1

CREATE TABLE airports
(
    id      VARCHAR(10) PRIMARY KEY NOT NULL,
    city    VARCHAR(255)            NOT NULL,
    country VARCHAR(255)            NOT NULL
);