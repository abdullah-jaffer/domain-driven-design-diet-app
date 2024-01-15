-- liquibase formatted sql

-- changeset abdullah:1683495376-1
CREATE TABLE user (
    id VARCHAR(200) NOT NULL,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    date_of_birth VARCHAR(200) NOT NULL,
    external_id VARCHAR(200) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);