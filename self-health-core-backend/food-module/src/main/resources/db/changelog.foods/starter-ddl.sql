-- liquibase formatted sql

-- changeset abdullah:1687205649-1
CREATE TABLE food_item (
    id VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    nutrition_bank_number VARCHAR(200) NOT NULL,
    carbohydrates FLOAT(8) NOT NULL,
    cholesterol INT(10) NOT NULL,
    protein FLOAT(8) NOT NULL,
    sugar FLOAT(8) NOT NULL,
    mono_saturated_fat FLOAT(8) NOT NULL,
    poly_saturated_fat FLOAT(8) NOT NULL,
    saturated_fat FLOAT(8) NOT NULL,
    type VARCHAR(20) NOT NULL,
    quantity INT(10) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    image VARCHAR(200) NOT NULL,
    category_id VARCHAR(50) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);


-- changeset abdullah:1687205649-2
CREATE TABLE category (
    id VARCHAR(200) NOT NULL,
    name VARCHAR(200) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

-- changeset abdullah:1687205649-3
CREATE TABLE food_user (
    id VARCHAR(200) NOT NULL,
    user_id VARCHAR(200) NOT NULL,
    food_item_id VARCHAR(200) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
  );

-- changeset abdullah:1687205649-4
ALTER TABLE food_item
ADD status varchar(10) AFTER category_id;