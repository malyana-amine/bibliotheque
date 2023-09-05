
CREATE DATABASE bibliothequetest;

\c bibliothequetest

CREATE TABLE auteur (
                        id serial PRIMARY KEY,
                        name varchar(255)
);

CREATE TABLE books (
                       id serial PRIMARY KEY,
                       title varchar(255),
                       quantitytotal int,
                       quantitydispo int,
                       bookmissing int,
                       prix real,
                       authorid int,
                       FOREIGN KEY (authorid) REFERENCES auteur (id)
);

CREATE TABLE users (
                       id serial PRIMARY KEY,
                       fullname varchar(255),
                       email varchar(255),
                       phone varchar(255)
);

CREATE TABLE demande (
                         id serial PRIMARY KEY,
                         userid int,
                         bookid int,
                         startdate date,
                         enddate date,
                         quantity int,
                         returned boolean,
                         FOREIGN KEY (userid) REFERENCES users (id),
                         FOREIGN KEY (bookid) REFERENCES books (id)
);




-- Create the trigger
CREATE OR REPLACE FUNCTION bookBorrowed()
RETURNS TRIGGER AS $$
BEGIN
UPDATE books
SET quantitydispo = quantitydispo - 1
WHERE id = NEW.bookid;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER bookBorrowed
    AFTER INSERT ON demande
    FOR EACH ROW
    EXECUTE FUNCTION bookBorrowed();
