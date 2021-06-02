INSERT INTO car (name, plate_number, nr_of_wheel) VALUES
    ('1969 Ford Mustang Convertible 351 cui', 'abc-123', '4'),
    ('1967 Chevrolet Impala SS 427 Convertiblee', 'abc-124', '4'),
    ('1971 Chevrolet El Camino SS 454 cui', 'abc-125', '4'),
    ('1963 Lincoln Continental Convertible', 'abc-126', '4'),
    ('1970 DODGE CHALLENGER 426 HEMI ENGINE', 'abc-127', '4');
    
INSERT INTO person (first_name, last_name) VALUES
	('John', 'Higgins'),
	('Mark', 'Selby'),
	('Judd', 'Trump'),
	('Neil', 'Robertson'),
	('Ronnie', 'O''Sullivan');
	
INSERT INTO car_to_person (car_id, person_id) VALUES
	('2','1'),
	('2','5'),
	('2','4'),
	('3','3'),
	('3','4'),
	('3','5'),
	('3','1'),
	('1','1'),
	('1','4'),
	('4','5');