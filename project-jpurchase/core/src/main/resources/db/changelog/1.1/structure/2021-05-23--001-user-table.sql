create table if not exists c_user (
    id serial PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    roles json,
    created_at TIMESTAMP
);
