DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE CUSTOMERS (
    ID INT(5) DEFAULT 0 NOT NULL AUTO_INCREMENT,
    UID INT(5),
    NAME VARCHAR(20) NOT NULL,
    BIRTHDAY DATE NOT NULL,
    ILLEGAL_AT TIME NULL,
    UPDATE_AT TIME NULL,
);