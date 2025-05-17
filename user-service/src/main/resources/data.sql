-- Sample data for the 'users' table
-- Assuming the table structure:
-- CREATE TABLE users (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(255) NOT NULL UNIQUE,
--     email VARCHAR(255) NOT NULL UNIQUE,
--     password_hash VARCHAR(255) NOT NULL, -- Store hashed passwords only!
--     first_name VARCHAR(100),
--     last_name VARCHAR(100),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );


INSERT INTO users (username, email, password_hash, first_name, last_name) VALUES
('johndoe', 'john.doe@example.com', 'hashed_password_123', 'John', 'Doe'),
('janedoe', 'jane.doe@example.com', 'hashed_password_456', 'Jane', 'Doe'),
('alice_smith', 'alice.smith@example.com', 'hashed_password_789', 'Alice', 'Smith'),
('bob_j', 'bob.johnson@example.com', 'hashed_password_abc', 'Bob', 'Johnson'),
('charlie_b', 'charlie.brown@example.com', 'hashed_password_def', 'Charlie', 'Brown'),
('diana_prince', 'diana.prince@example.com', 'hashed_password_ghi', 'Diana', 'Prince'),
('ed_stark', 'ed.stark@example.com', 'hashed_password_jkl', 'Eddard', 'Stark'),
('f_baggins', 'frodo.baggins@example.com', 'hashed_password_mno', 'Frodo', 'Baggins'),
('g_weasley', 'ginny.weasley@example.com', 'hashed_password_pqr', 'Ginny', 'Weasley'),
('h_potter', 'harry.potter@example.com', 'hashed_password_stu', 'Harry', 'Potter');
