--liquibase formatted SQL

--changeset davis:2

CREATE TABLE flights
(
    id              INT PRIMARY KEY NOT NULL,
    airport_id_from VARCHAR(10)     NOT NULL,
    airport_id_to   VARCHAR(10)     NOT NULL,
    carrier         VARCHAR(255)    NOT NULL,
    departure_time  TIMESTAMP       NOT NULL,
    arrival_time    TIMESTAMP       NOT NULL,
    CONSTRAINT fk_airport_from
        FOREIGN KEY (airport_id_from)
            REFERENCES airports (id),
    CONSTRAINT fk_airport_to
        FOREIGN KEY (airport_id_to)
            REFERENCES airports (id)
);