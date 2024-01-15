-- liquibase formatted sql

-- changeset abdullah:1688156871-1
CREATE TABLE meal (
    id VARCHAR(200) NOT NULL,
    total_calories DECIMAL(15) NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

-- changeset abdullah:1688156871-2
CREATE TABLE meal_item (
    id VARCHAR(200) NOT NULL,
    calories DECIMAL(15) NOT NULL,
    carbohydrates FLOAT(20) NOT NULL,
    protein FLOAT(20) NOT NULL,
    fat FLOAT(20) NOT NULL,
    quantity INT(10) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    meal_id VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

-- changeset abdullah:1688156871-3
ALTER TABLE meal
ADD localized_time_string varchar(10) DEFAULT '' AFTER user_id;

-- changeset abdullah:1688156871-4
ALTER TABLE meal
MODIFY COLUMN localized_time_string varchar(60)