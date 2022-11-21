-- Set up default data

CREATE SCHEMA IF NOT EXISTS `VenPalMo` ;

USE VenPalMo;

CREATE TABLE IF NOT EXISTS `VenPalMo`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `zip` VARCHAR(45) NULL DEFAULT NULL,
  `joined_date` DATETIME NOT NULL,
  `birth_date` DATETIME NULL DEFAULT NULL,
  `role` VARCHAR(45) NOT NULL,
  `active` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `VenPalMo`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  `balance` DECIMAL(10,2) NOT NULL,
  `default_acct` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `users_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `VenPalMo`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
  
  
CREATE TABLE IF NOT EXISTS `VenPalMo`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sending_user_id` INT NOT NULL,
  `recieving_user_id` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  `note` VARCHAR(1000) NULL DEFAULT NULL,
  `status` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `i_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `sending_id_idx` (`sending_user_id` ASC) VISIBLE,
  INDEX `recieving_id_idx` (`recieving_user_id` ASC) VISIBLE,
  CONSTRAINT `recieving_id`
    FOREIGN KEY (`recieving_user_id`)
    REFERENCES `VenPalMo`.`user` (`id`),
  CONSTRAINT `sending_id`
    FOREIGN KEY (`sending_user_id`)
    REFERENCES `VenPalMo`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
   
CREATE TABLE IF NOT EXISTS `VenPalMo`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  `text` VARCHAR(1000) NOT NULL,
  `to_user_id` INT NOT NULL,
  `from_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `to_user_idx` (`to_user_id` ASC) VISIBLE,
  INDEX `from_user_idx` (`from_user_id` ASC) VISIBLE,
  CONSTRAINT `from_user`
    FOREIGN KEY (`from_user_id`)
    REFERENCES `VenPalMo`.`user` (`id`),
  CONSTRAINT `to_user`
    FOREIGN KEY (`to_user_id`)
    REFERENCES `VenPalMo`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- Create Users

INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Devin','Butts','devinmbutts@gmail.com','test','1185 Code St.','Grand Rapids','MI','49534',CURDATE(),'1993-07-02','ADMIN',1);
INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Nick','Rufus','nick@gmail.com','test','1188 Code St.','Grand Rapids','MI','49534',CURDATE(),'1994-02-24','USER',1);
INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Chris','Channells','cc@gmail.com','test','1185 Code St.','Grand Rapids','MI','49534',CURDATE(),'1992-07-05','USER',1);
INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Tori','Butts','tori.butts@gmail.com','test','1185 Code St.','Grand Rapids','MI','49534',CURDATE(),'1994-02-24','USER',1);
INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Justin','Frank','frankjustin@gmail.com','test','1190 Code St.','Grand Rapids','MI','49534',CURDATE(),'1993-06-02','USER',1);
INSERT INTO user (first_name,last_name,email,password,address,city,state,zip,joined_date,birth_date,role,active) VALUES ('Cookie','Knopf','knopfcook@gmail.com','test','1191 Code St.','Grand Rapids','MI','49534',CURDATE(),'1990-08-04','USER',1);


-- Create Accounts

INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),'USD',555.22,1);
INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'nick@gmail.com'),'USD',5046450.22,1);
INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'cc@gmail.com'),'USD',55588.22,1);
INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),'USD',100.22,1);
INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'frankjustin@gmail.com'),'USD',65880600.22,1);
INSERT INTO account (user_id,currency,balance,default_acct) VALUES ((SELECT id FROM user WHERE email like 'knopfcook@gmail.com'),'USD',546008.22,1);


-- Create Messages

INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Hello',(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),(SELECT id FROM user WHERE email like 'nick@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Hi!',(SELECT id FROM user WHERE email like 'nick@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Can I have rent money?',(SELECT id FROM user WHERE email like 'nick@gmail.com'),(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Sure one second',(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),(SELECT id FROM user WHERE email like 'nick@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Where are we at with the event?',(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),(SELECT id FROM user WHERE email like 'frankjustin@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Need funding soon.',(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),(SELECT id FROM user WHERE email like 'knopfcook@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Heres rent money',(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Ill send rent soon',(SELECT id FROM user WHERE email like 'cc@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('hows your 3d print going?',(SELECT id FROM user WHERE email like 'knopfcook@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('Ill buy it off you',(SELECT id FROM user WHERE email like 'frankjustin@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('name your price',(SELECT id FROM user WHERE email like 'frankjustin@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'));
INSERT INTO message (text,to_user_id,from_user_id) VALUES ('sending rent',(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),(SELECT id FROM user WHERE email like 'nick@gmail.com'));

-- Create Transactions

INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),(SELECT id FROM user WHERE email like 'tori.butts@gmail.com'),40.22,'USD','Suprise!','CLEARED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'nick@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),1000.22,'USD','Rent for May','REQUESTED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'nick@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),6540.22,'USD','Please Pay','DENIED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'cc@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),1000.55,'USD','Rent for May','CLEARED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'frankjustin@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),300.22,'USD','Dinner','CLEARED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'knopfcook@gmail.com'),(SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),60.22,'USD','Repairs','CLEARED');
INSERT INTO transaction (sending_user_id,recieving_user_id,amount,currency,note,status) VALUES ((SELECT id FROM user WHERE email like 'devinmbutts@gmail.com'),(SELECT id FROM user WHERE email like 'knopfcook@gmail.com'),60.22,'USD','No worries','CLEARED');




SELECT * FROM user;
SELECT * FROM account;
SELECT * FROM message;
SELECT * FROM transaction;









   
  




