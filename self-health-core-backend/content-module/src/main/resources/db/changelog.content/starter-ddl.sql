-- liquibase formatted sql

-- changeset abdullah:1688156871-1
CREATE TABLE content (
    id VARCHAR(200) NOT NULL,
    title VARCHAR(300) NOT NULL,
    image VARCHAR(100) NOT NULL,
    link VARCHAR(100) NOT NULL,
    status VARCHAR(15) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);