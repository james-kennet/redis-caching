CREATE TABLE person (
id int NOT NULL AUTO_INCREMENT,
first_name varchar(25),
last_name  varchar(25),
gender varchar(10),
PRIMARY KEY (id)
);

INSERT INTO person (first_name, last_name, gender) VALUES ('James', 'Bond', 'Male');
INSERT INTO person (first_name, last_name, gender) VALUES ('Mary', 'Bat Woman', 'Female');