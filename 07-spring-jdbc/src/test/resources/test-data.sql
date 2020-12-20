insert into genre (id, name) values
    (1, 'Crime and Detective'),
    (2, 'Fairy Tale'),
    (3, 'Drama');

insert into author (id, first_name, last_name) values
    (1, 'Arthur', 'Conan Doyle'),
    (2, 'Agatha', 'Christie'),
    (3, 'Alexander', 'Pushkin'),
    (4, 'Fedor', 'Dostoevsky');

insert into book (id, author_id, genre_id, name) values
    (1, 1, 1, 'Sherlock Holmes. A Study in Scarlet'),
    (2, 2, 1, 'Hercule Poirot. The Mysterious Affair at Styles'),
    (3, 3, 2, 'The Tale about a Fisherman and a Fish'),
    (4, 4, 3, 'Crime and Punishment');
