DROP TABLE IF EXISTS Customer;
CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE Customer (
      id                BIGINT              PRIMARY KEY,
      name              VARCHAR(255)        NOT NULL,
      surname           VARCHAR(255)        NOT NULL,
      age               INT                 NOT NULL,
      city              VARCHAR(20)         NOT NULL,
      cc_number         VARCHAR(19)         NOT NULL,
      cc_expiration     VARCHAR(5)          NOT NULL,
      cc_cvv            VARCHAR(3)          NOT NULL
);