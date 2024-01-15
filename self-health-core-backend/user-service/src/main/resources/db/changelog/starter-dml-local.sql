-- liquibase formatted sql

-- changeset abdullah:1664189561-1
INSERT INTO user(
	id, name, email, date_of_birth, external_id, created_at, updated_at)
	VALUES ('user-1', 'User # 1', 'user1@emailprovider.com', '2023-12-03', 'user-1', '2022-09-07 19:10:25', '2022-09-07 22:10:25')
