insert into c_user (username, password, roles, created_at) VALUES
    ('user1', '$2y$10$1/TbSnlQEs6ZA10xgjMCzO5yoap4CK/E73t2v9i.95pa/vzJGVV4a', '["ROLE_USER"]', now()),-- !user1
    ('user2', '$2y$10$jrlQm93e2C8/.2EVanGaAu6rvHDakKvvwPlrTv3R.k94FbAVVuFfm', '["ROLE_USER"]', now()),-- !user2
    ('org1',  '$2a$10$a0J9.KcGATsRRrNn1pKVC.DWgkz8UpQKym6EnzQ4N1eX4R2WPsmLm', '["ROLE_USER", "ROLE_ORGANIZER"]', now()),-- !org1
    ('org2',  '$2a$10$iwKTz1W.G.qMRAUXtWxVOuBq4ac/PXzVCKgzzJUm.smcmtUFcGhha', '["ROLE_USER", "ROLE_ORGANIZER"]', now());-- !org2