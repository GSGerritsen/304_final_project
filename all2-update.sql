create database 304Project;
use 304Project;

CREATE TABLE RegularUser (
    uid INT(12) AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE,
    password CHAR(64),
    date_created DATETIME,
    email VARCHAR(30),
    ban BOOL,
    PRIMARY KEY (uid)
);

create trigger before_regularuser_insert 
before insert on regularuser
for each row  set New.date_created = Now();


CREATE TABLE Admin (
    uid INT(12) AUTO_INCREMENT,
    username VARCHAR(30),
    email VARCHAR(30),
    PRIMARY KEY (uid)
);

CREATE TABLE Creator (
    cid INT(12) AUTO_INCREMENT,
    name VARCHAR(255),
    type CHAR(20),
    bio VARCHAR(255),
    PRIMARY KEY (cid)
);

CREATE TABLE Media (
    mid INT(12) AUTO_INCREMENT,
    title VARCHAR(255),
    cid INT(12) NOT NULL,
    avg_rating FLOAT,
    PRIMARY KEY (mid),
    FOREIGN KEY (cid)
        REFERENCES Creator (cid)
);

CREATE TABLE Movie (
    mid INT(12),
    length FLOAT,
    PRIMARY KEY (mid),
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);

CREATE TABLE TVShow (
    mid INT(12),
    length FLOAT,
    PRIMARY KEY (mid),
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);


CREATE TABLE Episode (
    mid INT(12),
    season INTEGER,
    episode_number INTEGER,
    title VARCHAR(255),
    avg_rating FLOAT,
    PRIMARY KEY (mid , season , episode_number),
    FOREIGN KEY (mid)
        REFERENCES TVShow (mid)
);

CREATE TABLE Book (
    mid INT(12),
    num_pages INTEGER,
    PRIMARY KEY (mid),
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);

CREATE TABLE Comic (
    mid INT(12),
    num_pages INTEGER,
    PRIMARY KEY (mid),
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);

CREATE TABLE Volume (
    mid INT(12),
    title VARCHAR(255),
    vol_num INTEGER,
    avg_rating FLOAT,
    PRIMARY KEY (mid , vol_num , title),
    FOREIGN KEY (mid)
        REFERENCES Comic (mid)
);

CREATE TABLE ViewLater (
    uid INT(12),
    mid INT(12),
    PRIMARY KEY (uid , mid),
    FOREIGN KEY (uid)
        REFERENCES RegularUser (uid)
        ON DELETE CASCADE,
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);

CREATE TABLE History (
    uid INT(12),
    mid INT(12),
    PRIMARY KEY (uid , mid),
    FOREIGN KEY (uid)
        REFERENCES RegularUser (uid)
        ON DELETE CASCADE,
    FOREIGN KEY (mid)
        REFERENCES Media (mid)
        ON DELETE CASCADE
);

Insert into regularuser (username, email, ban) values ('James Smith', 'js@email.com', false);
Insert into regularuser (username, email, ban) values ('Mary Johnson', 'mj@email.com', false);
Insert into regularuser (username, email, ban) values ('John Williams', 'jw@email.com', false);
Insert into regularuser (username, email, ban) values ('Robert Brown', 'rb@email.com', false);
Insert into regularuser (username, email, ban) values ('Patricia Jones', 'pj@email.com', false);

Insert into admin (username, email) values ('admin1', 'admin1@email.com');
Insert into admin (username, email) values ('admin2', 'admin2@email.com');

Insert into creator (name, type, bio) values ('Steven Spielberg', 'Producer', 'Steven Allan Spielberg, (born December 18, 1946) is an American director, producer, and screenwriter. He is considered one of the founding pioneers of the New Hollywood era');
Insert into creator (name, type, bio) values ('Charles Dickens', 'Writer', 'Charles Dickens was an English writer and social critic. He created some of the world''s best-known fictional characters and is regarded by many as the greatest novelist of the Victorian era.');
Insert into creator (name, type, bio) values ('Frank Darabont', 'Producer', 'Three-time Oscar nominee Frank Darabont was born in a refugee camp in 1959 in Montbeliard, France, the son of Hungarian parents who had fled Budapest during the failed 1956 Hungarian revolution');
Insert into creator (name, type, bio) values ('Chris Carter', 'Writer', 'Chris Carter is an American television and film producer, director and writer. Born in Bellflower, California, Carter graduated with a degree in journalism from California State University before spending thirteen years working for Surfing Magazine.');
Insert into creator (name, type, bio) values ('Chuck Lorre', 'Producer', 'Chuck Lorre has conquered the entertainment industry with hit shows like "Grace Under Fire" and "Cybill" as well as the number 1 comedy on television and four year People''s Choice Award winner, "The Big Bang Theory.');
Insert into creator (name, type, bio) values ('J.K. Rowling', 'Writer', 'J. K. Rowling is a British novelist, screenwriter and film producer best known as the author of the Harry Potter fantasy series. The books have gained worldwide attention, won multiple awards, and sold more than 400 million copies.');
Insert into creator (name, type, bio) values ('Akira Toriyama', 'Writer', 'Akira Toriyama is a Japanese manga and game artist. He first achieved mainstream recognition for his highly successful manga Dr. Slump, before going on to create Dragon Ball');

Insert into media(title, cid, avg_rating) values ('E.T. the Extra-Terrestrial', 1, 8);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 115);
Insert into media(title, cid, avg_rating) values ('Jaws', 1, 8);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 130);
Insert into media(title, cid, avg_rating) values ("Schindler\'s List", 1, 8.9);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 195);
Insert into media(title, cid, avg_rating) values ('Raiders of the Lost Ark', 1, 8.5);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 115);
Insert into media(title, cid, avg_rating) values ('Saving Private Ryan', 1, 8.6);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 170);
Insert into media(title, cid, avg_rating) values ('A.I Artificial Intelligence', 1, 7.1);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 146);
Insert into media(title, cid, avg_rating) values ('Close Encounters of the Third Kind', 1, 7.7);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 145);

Insert into creator (name, type, bio) values ('Joss Whedon', 'Director', 'Joss Whedon (born Joseph Hill Whedon on June 23, 1964) is an American screenwriter, film and television director, film and television producer, comic book author, and composer.');
Insert into media(title, cid, avg_rating) values ('Avengers: Age of Ultron', 8, 7.4);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 146);
Insert into media(title, cid, avg_rating) values ('Serenity', 8, 8.0);
Insert into movie(mid, length) values (LAST_INSERT_ID(), 119);
Insert into media(title, cid, avg_rating) values ('The Office', 8, 8.6);
Insert into tvshow(mid, length) values(LAST_INSERT_ID(), 30);
Insert into episode(mid, season, episode_number, title, avg_rating) values (LAST_INSERT_ID(), 4, 6, 'Branch Wars', 8.4);
Insert into episode(mid, season, episode_number, title, avg_rating) values (LAST_INSERT_ID(), 3, 16, 'Business School', 8.8);

Insert into media(title, cid, avg_rating) values ("Harry Potter and the Sorcerer\'s Stone", 6, 8.8);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 320);
Insert into media(title, cid, avg_rating) values ('Harry Potter and the Chamber of Secrets', 6, 8.6);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 341);
Insert into media(title, cid, avg_rating) values ('Harry Potter and Prisoner of Azkaban', 6, 9);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 435);
Insert into media(title, cid, avg_rating) values ('Harry Potter and the Goblet of Fire', 6, 9);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 734);
Insert into media(title, cid, avg_rating) values ('Harry Potter and the Order of the Phoenix', 6, 8.9);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 870);
Insert into media(title, cid, avg_rating) values ('Harry Potter and the Half-Blood Prince', 6, 9.1);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 652);
Insert into media(title, cid, avg_rating) values ('Harry Potter and the Deathly Hallows', 6, 9.2);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 784);

INSERT INTO creator (name, type, bio) values ('Stephen King', 'Writer', 'Stephen Edwin King (born September 21, 1947) is an American author of horror, supernatural fiction, suspense, science fiction, and fantasy.');
Insert into media(title, cid, avg_rating) values ("It", 9, 8.2);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 1116);
Insert into media(title, cid, avg_rating) values ("The Stand", 9, 8.6);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 1153);
Insert into media(title, cid, avg_rating) values ("Carrie", 9, 7.84);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 253);
Insert into media(title, cid, avg_rating) values ("Under the Dome", 9, 7.78);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 1074);
Insert into media(title, cid, avg_rating) values ("The Green Mile", 9, 8.8);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 592);
Insert into media(title, cid, avg_rating) values ("Cell", 9, 7.28);
Insert into book(mid, num_pages) values (LAST_INSERT_ID(), 449);
Insert into media(title, cid, avg_rating) values ("American Vampire", 9, 7.9);
Insert into comic(mid, num_pages) values (LAST_INSERT_ID(), 192);
Insert into volume(mid, title, vol_num, avg_rating) values (LAST_INSERT_ID(), 'American Vampire', 1, 8.1);
Insert into volume(mid, title, vol_num, avg_rating) values (LAST_INSERT_ID(), 'American Vampire', 2, 8.4);
Insert into volume(mid, title, vol_num, avg_rating) values (LAST_INSERT_ID(), 'American Vampire', 3, 8.9);
Insert into volume(mid, title, vol_num, avg_rating) values (LAST_INSERT_ID(), 'American Vampire', 5, 7.1);
Insert into volume(mid, title, vol_num, avg_rating) values (LAST_INSERT_ID(), 'American Vampire', 6, 7.5);

Insert into media(title, cid, avg_rating) values ('David Copperfield', 2, 8);
Insert into book(mid, num_pages) values (2, 624);

Insert into media(title, cid, avg_rating) values ('The Walking Dead', 3, 8.7);
Insert into tvshow(mid, length) values(3, 44);
Insert into episode(mid, season, episode_number, title) values (3, 1, 1, 'Days Gone Bye');
Insert into episode(mid, season, episode_number, title) values (3, 1, 2, 'Guts');
Insert into episode(mid, season, episode_number, title) values (3, 2, 1, 'What Lies Ahead');
Insert into episode(mid, season, episode_number, title) values (3, 2, 3, 'Save the Last One');


Insert into media(title, cid, avg_rating) values ('The X Files', 4, 7.7);
Insert into tvshow(mid, length) values(4, 45);

Insert into media(title, cid, avg_rating) values ('The Big Bang Theory', 5, 6.9);
Insert into tvshow(mid, length) values(5, 22);
Insert into episode(mid, season, episode_number, title) values (5, 1, 1, 'The Big Bran Hypothesis');

Insert into media(title, cid, avg_rating) values ('Harry Potter and the Sorcerer''s Stone', 6, 7.4);
insert into book(mid, num_pages) values (6, 223);

Insert into media(title, cid, avg_rating) values ('Harry Potter and the Chamber of Secrets', 6, 8.5);
insert into book(mid, num_pages) values (7, 251);

Insert into media(title, cid, avg_rating) values ('Dragon Ball', 7, 2.5);
Insert into comic(mid, num_pages) values (7, 192);
Insert into volume(mid, title, vol_num) values (7, 'The Monkey King', 1);
Insert into volume(mid, title, vol_num) values (7, 'Wish Upon a Dragon', 2);

Insert into history(uid, mid) values (1, 1);
Insert into history(uid, mid) values (1, 2);
Insert into history(uid, mid) values (2, 3);

Insert into viewlater(uid, mid) values (1, 3);
Insert into viewlater(uid, mid) values (3, 1);
Insert into viewlater(uid, mid) values (3, 3);