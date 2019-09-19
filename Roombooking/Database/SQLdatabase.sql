CREATE DATABASE RoombookingDB;
USE RoombookingDB; 

CREATE TABLE Room(
room_id VARCHAR(10) NOT NULL,
room_type VARCHAR(20),
room_available BOOLEAN,
room_price INTEGER(15),
PRIMARY KEY (room_id)
);

CREATE TABLE Customer(
cus_id VARCHAR (5),
cus_name VARCHAR(50),
cus_phone VARCHAR (10),
cus_email VARCHAR(50),
PRIMARY KEY (cus_id)
);


CREATE TABLE Orders(
order_id VARCHAR(20),
room_id VARCHAR(5),
cus_id VARCHAR(5),
order_checkindate VARCHAR(30),
order_checkoutdate VARCHAR(30),
PRIMARY KEY (order_id),
FOREIGN KEY (room_id) REFERENCES Room(room_id),
FOREIGN KEY (cus_id) REFERENCES Customer(cus_id)
);


INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('sr01', 'singleroom', TRUE, 250);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('sr02', 'singleroom', TRUE, 250);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('sr03', 'singleroom', TRUE, 250);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('sr04', 'singleroom', TRUE, 250);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('dr01', 'singleroom', TRUE, 500);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('dr02', 'singleroom', TRUE, 500);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('dr03', 'singleroom', TRUE, 500);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('dr04', 'singleroom', TRUE, 500);
INSERT INTO Room(room_id, room_type, room_available, room_price)
VALUES('dr05', 'singleroom', TRUE, 500);
INSERT INTO Room (room_id, room_type, room_available, room_price)
VALUES ('fr01', 'familyroom', TRUE, 750);
INSERT INTO Room (room_id, room_type, room_available, room_price)
VALUES ('fr02', 'familyroom', TRUE, 750);
INSERT INTO Room (room_id, room_type, room_available, room_price)
VALUES ('fr03', 'familyroom', TRUE, 750);
INSERT INTO Room (room_id, room_type, room_available, room_price)
VALUES ('zj01', 'suiteroom', TRUE, 1000);
INSERT INTO Room (room_id, room_type, room_available, room_price)
VALUES ('zj02', 'suiteroom', TRUE, 1000);




