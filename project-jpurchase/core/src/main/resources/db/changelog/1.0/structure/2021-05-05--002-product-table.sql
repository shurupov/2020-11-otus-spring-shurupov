create table if not exists c_product (
    id serial PRIMARY KEY,
    purchase_id int NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    properties json,
    option_name VARCHAR(255),
    options json,
    price int NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (purchase_id) references c_purchase (id)
);
