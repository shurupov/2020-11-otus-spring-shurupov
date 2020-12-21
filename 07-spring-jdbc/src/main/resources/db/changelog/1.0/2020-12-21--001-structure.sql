drop table if exists genre;
create table genre
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

drop table if exists author;
create table author
(
    id         bigint primary key auto_increment,
    first_name varchar(255),
    last_name  varchar(255)
);

drop table if exists book;
create table book
(
    id        bigint primary key auto_increment,
    author_id bigint,
    genre_id  bigint,
    name      varchar(255),
    foreign key (author_id) REFERENCES author (id),
    foreign key (genre_id) REFERENCES genre (id)
);



