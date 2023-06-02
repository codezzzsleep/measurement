CREATE database measurement;
use measurement;
CREATE TABLE `user` (
                     userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     account CHAR ( 12 ) NOT NULL,
                     passwd CHAR ( 64 ) NOT NULL,
                     isDelete TINYINT DEFAULT 0,
                     isAdmin INT DEFAULT 0,
                     college VARCHAR ( 50 ),
                     class VARCHAR ( 50 ),
                     counselor CHAR ( 12 )
);
CREATE TABLE score (
                       scoreID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       account CHAR ( 12 ) NOT NULL,
                       ideologicalAndMoral DOUBLE,
                       courseLearning DOUBLE,
                       bodyArtIntegrated DOUBLE,
                       practiceAbility DOUBLE
);
CREATE TABLE rules (
                       rulesID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       groupID INT NOT NULL,
                       content TEXt,
                       addScore TINYINT,
                       maxScore DOUBLE,
                       belong CHAR ( 12 )
);
CREATE TABLE `group` ( groupID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, belong CHAR ( 12 ), proportion DOUBLE );
CREATE TABLE audit ( auditID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, belong CHAR ( 12 ), `status` TINYINT, rulesID INT, file VARCHAR ( 100 ) );