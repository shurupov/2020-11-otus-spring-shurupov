insert into genre (id, name) values
    (1, 'Action and Adventure'),
    (2, 'Crime and Detective'),
    (3, 'Fairy Tale'),
    (4, 'Fantasy'),
    (5, 'Satire'),
    (6, 'Science Fiction (Sci-Fi)'),
    (7, 'Poem'),
    (8, 'Drama');

insert into author (id, first_name, last_name) values
    (1, 'Arthur', 'Conan Doyle'),
    (2, 'Agatha', 'Christie'),
    (3, 'Alexander', 'Pushkin'),
    (4, 'Fedor', 'Dostoevsky');

insert into book (id, author_id, genre_id, name) values
    (1, 1, 2, 'Sherlock Holmes. A Study in Scarlet'),
    (2, 1, 6, 'The lost world'),
    (3, 2, 2, 'Hercule Poirot. The Mysterious Affair at Styles'),
    (4, 3, 3, 'The Tale about a Fisherman and a Fish'),
    (5, 3, 3, 'Ruslan and Ludmila'),
    (6, 4, 8, 'Crime and Punishment'),
    (7, 4, 8, 'Player');
