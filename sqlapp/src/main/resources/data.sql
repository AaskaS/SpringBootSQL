INSERT INTO demo.roles (role_id, role)
SELECT * FROM (SELECT 1, 'ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT role FROM demo.roles WHERE role = 'ADMIN'
) LIMIT 1;

INSERT INTO demo.roles (role_id, role)
SELECT * FROM (SELECT 2, 'USER') AS tmp
WHERE NOT EXISTS (
    SELECT role FROM demo.roles WHERE role = 'USER'
) LIMIT 1;