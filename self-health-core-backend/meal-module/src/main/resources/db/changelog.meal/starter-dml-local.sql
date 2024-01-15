-- liquibase formatted sql

-- changeset abdullah:1688157088-1
INSERT INTO meal (id, total_calories, user_id, created_at, updated_at)
VALUES ('1', 500, 'user1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1688157088-2
INSERT INTO meal (id, total_calories, user_id, created_at, updated_at)
VALUES ('2', 750, 'user2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1688157088-3
INSERT INTO meal_item (id, calories, carbohydrates, protein, fat, quantity, unit, created_at, updated_at, meal_id)
VALUES ('1', 100, 20.5, 10.2, 5.3, 2, 'GRAMS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1');

-- changeset abdullah:1688157088-4
INSERT INTO meal_item (id, calories, carbohydrates, protein, fat, quantity, unit, created_at, updated_at, meal_id)
VALUES ('2', 150, 30.8, 15.6, 8.1, 1, 'GRAMS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1');

-- changeset abdullah:1688157088-5
INSERT INTO meal_item (id, calories, carbohydrates, protein, fat, quantity, unit, created_at, updated_at, meal_id)
VALUES ('3', 250, 40.3, 20.1, 12.7, 3, 'GRAMS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2');