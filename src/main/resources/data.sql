CREATE TABLE Mission(
    missionName VARCHAR(50) NOT NULL PRIMARY KEY,
    isCompleted BOOLEAN NOT NULL,
    isDeleted BOOLEAN NOT NULL,
    superHeroName VARCHAR(50) 
);
CREATE TABLE SuperHero (
    firstName VARCHAR(50),
    lastName  VARCHAR(50),
    superHeroName VARCHAR(50) NOT NULL PRIMARY KEY,
    missionName VARCHAR(50),
    FOREIGN KEY (missionName) REFERENCES Mission(missionName)
);