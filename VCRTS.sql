CREATE SCHEMA VCRTS;

USE VCRTS;

CREATE TABLE vehicleTable (
timestamp VARCHAR(255),
owner_name VARCHAR(255),
owner_DOB VARCHAR(255),
owner_ID VARCHAR(255),
make VARCHAR(255),
model VARCHAR(255),
year INT,
color VARCHAR(255),
license_plate VARCHAR(255),
residency_time DOUBLE
);

CREATE TABLE jobTable (
timestamp VARCHAR(255),
requester_name VARCHAR(255),
requester_DOB VARCHAR(255),
requester_ID VARCHAR(255),
duration DOUBLE,
deadline VARCHAR(255),
type VARCHAR(255),
intensity VARCHAR(255)
);



