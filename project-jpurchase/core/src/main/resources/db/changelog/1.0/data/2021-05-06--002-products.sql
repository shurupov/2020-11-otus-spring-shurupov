insert into c_product (purchase_id, name, description, properties, option_name, options, price, created_at) VALUES
    (1, 'Sneakers', 'Cool Sneakers', '{"color":"red", "sex":"male"}', 'size', '["36", "37", "38"]', 25000, now()),
    (1, 'T-Short', 'Cool T-Short', '{"color":"red", "sex":"female"}', 'size', '["XS", "S", "M", "L", "XL"]', 30000, now()),
    (1, 'Pants', 'Cool Pants', '{"color":"white", "sex":"male"}', 'size', '["M", "L", "XL"]', 29000, now()),
    (2, 'Sneakers', 'Another Sneakers', '{"color":"red", "sex":"male"}', 'size', '["36", "37", "38"]', 25000, now()),
    (2, 'T-Short', 'Another T-Short', '{"color":"red", "sex":"female"}', 'size', '["XS", "S", "M", "L", "XL"]', 30000, now()),
    (2, 'Pants', 'Another Pants', '{"color":"white", "sex":"male"}', 'size', '["M", "L", "XL"]', 29000, now());