--Table: Account
CREATE TABLE Account(
	User VARCHAR(32) NOT NULL UNIQUE PRIMARY KEY,
	Pass VARCHAR(32) NOT NULL
)

--Table: Account - Data
INSERT INTO Account
VALUES("admin", "123")

--Table WORD2PDF
CREATE TABLE WORD2PDF(
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- user who to convert them
	User VARCHAR(32) NOT NULL,
    -- original name
	SourceName TEXT,
	-- original file
	SourcePath TEXT,
	-- file after successfully processed
	TargetPath TEXT,
	-- date start
	DateStart DATETIME,
	-- date finish
	DateCompleted DATETIME,
	-- -1: error, 0: pending, 1: processing, 2: successful
	Result INT DEFAULT 0,
	FOREIGN KEY (User) REFERENCES Account(User) 
)

--Table PDF2WORD
CREATE TABLE PDF2WORD(
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- user who to convert them
	User VARCHAR(32) NOT NULL,
    -- original name
	SourceName TEXT,
	-- original file
	SourcePath TEXT,
	-- file after successfully processed
	TargetPath TEXT,
	-- date start
	DateStart DATETIME,
	-- date finish
	DateCompleted DATETIME,
	-- -1: error, 0: pending, 1: processing, 2: successful
	Result INT DEFAULT 0,
	FOREIGN KEY (User) REFERENCES Account(User) 
)
