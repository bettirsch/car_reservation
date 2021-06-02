DROP TABLE IF EXISTS car_to_person;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS person;

CREATE TABLE car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    plate_number VARCHAR(45) NOT NULL UNIQUE,
    nr_of_wheel INT
);

CREATE TABLE person (
	person_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL
);

CREATE TABLE car_to_person(
	car_id INT,
    person_id INT,
    constraint fk_car_id foreign key(car_id) references car(car_id),
    constraint fk_person_id foreign key(person_id) references person(person_id)
);