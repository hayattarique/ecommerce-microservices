CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL
);

-- Insert default values
INSERT INTO roles (id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_MANAGER'),
       (4, 'ROLE_VENDOR');


