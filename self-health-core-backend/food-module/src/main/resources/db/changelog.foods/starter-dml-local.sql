-- liquibase formatted sql

-- changeset abdullah:1687205859-1
INSERT INTO food_item (id, nutrition_bank_number, description, carbohydrates, cholesterol, protein, sugar, mono_saturated_fat, poly_saturated_fat, saturated_fat, type, quantity, unit, image, category_id, created_at, updated_at)
VALUES ('1', '123456789', 'Apple Red', 10.5, 5, 20.2, 15.3, 3.4, 2.1, 7.8, 'CUSTOM', 1, 'grams', 'image1.jpg', 'category1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1687205859-2
INSERT INTO food_item (id, nutrition_bank_number, description, carbohydrates, cholesterol, protein, sugar, mono_saturated_fat, poly_saturated_fat, saturated_fat, type, quantity, unit, image, category_id, created_at, updated_at)
VALUES ('2', '987654321', 'Tomato Red', 15.7, 8, 18.9, 12.4, 2.3, 1.9, 6.5, 'GENERAL', 2, 'grams', 'image2.jpg', 'category2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1687205859-3
INSERT INTO category (id, name, created_at, updated_at)
VALUES ('category1', 'Fruits', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1687205859-4
INSERT INTO category (id, name, created_at, updated_at)
VALUES ('category2', 'Vegetables', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- changeset abdullah:1687205859-5
INSERT INTO food_user (id, user_id, food_item_id, created_at, updated_at)
VALUES ('user1_item1', 'user-1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




