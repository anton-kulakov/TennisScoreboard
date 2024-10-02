CREATE SCHEMA IF NOT EXISTS database;
CREATE TABLE players (
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR UNIQUE
);
CREATE TABLE matches (
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         player1 INTEGER,
                         player2 INTEGER,
                         winner INTEGER,
                         FOREIGN KEY (player1) REFERENCES players (id),
                         FOREIGN KEY (player2) REFERENCES players (id),
                         FOREIGN KEY (winner) REFERENCES players (id)
);