update customer set roles = '["ROLE_USER"]' where id in (1,2);
update customer set roles = '["ROLE_USER", "ROLE_ADMIN"]' where id = 3;