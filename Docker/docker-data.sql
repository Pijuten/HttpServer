CREATE TABLE userData(
    username VARCHAR PRIMARY KEY UNIQUE, 
    password VARCHAR,
    bio varchar,
    profileimage varchar
    );
CREATE TABLE cards(
    cardid varchar PRIMARY KEY UNIQUE, 
    cardname varchar,
    damage FLOAT, 
    deck BOOLEAN DEFAULT FALSE,
    username varchar,
    packageid int,    
    CONSTRAINT FK_username_userData FOREIGN KEY(username)
        REFERENCES userData(username)
);
