INSERT INTO Clients (name, email) VALUES 
			("James Bond", "jamesbond@gmail.com"),
            ("Jane Doe", "janedoe@gmail.com");
INSERT INTO COUNTRY(name, abbreviation) VALUES
			("Argentina", "ARG"),
            ("Brazil", "BR");
INSERT INTO CITIES(name, abbreviation, country_id, zip_code) VALUES
			("Tandil", "TAN", 1, 7000),
            ("Balcarce", "BAL", 1, 7620);
INSERT INTO LOCATION (name, city_id) VALUES
			("Roca 500", 1),
            ("Alsina 301", 1),
            ("10 532", 2);
INSERT INTO PROJECT (begin_date, location_id, description, expected_end_date) VALUES
			("2021-01-01", 1, "Our first project", "2021-12-15"),
            ("2021-02-01", 2, "Our second project", "2022-06-01"),
            ("2021-02-01", 3, "Our third project", "2022-06-01");
INSERT INTO CONTRACTS (project_id, client_id, begin_date, end_date, prize) VALUES
			(1, 1, "2020-12-20", "2021-12-20", 20000),
            (2, 2, "2021-02-01", "2022-06-01", 50000),
            (3, 2, "2021-02-01", "2022-06-01", 50000);
INSERT INTO PAYMENTS (date, amount, serial_number, contract_id) VALUES
			("2021-01-01", 5000, "X2K4WL1", 1),
            ("2021-03-01", 5000, "SK2XO21", 1),
            ("2021-02-01", 20000, "SX5V5X9", 2),
            ("2021-02-01", 20000, "WQW21X9", 3);
INSERT INTO ASSETS (weekly_hours, project_id) VALUES
			(40, null),
            (40, 1),
            (40, 1),
            (20, 2),
            (30, null),
            (50, 1);
INSERT INTO EMPLOYEES (name, email, salary, asset_id) VALUES
			("Javier Gomez", "javiergomez@gmail.com", 1500, 1),
            ("Manuel Garcia", "manuelgarcia@gmail.com", 2000, 2),
            ("Juan Fernandez", "juanfernandez@gmail.com", 2000, 6);
INSERT INTO COMPANY (name) VALUES
			("Bosch"),
            ("Caterpillar");	
INSERT INTO MACHINE_TYPES (name, description) VALUES
			("Excavator", "This machine is used to excave"),
            ("Paver", "This machine is used to pave");
INSERT INTO MACHINES (buying_date, number, asset_id, type_id, company_id) VALUES
			("2020-10-10", 20192412, 3, 1, 1),
            ("2020-10-10", 40291202, 4, 1, 2),
            ("2021-01-04", 30129312, 5, 2, 1);
            
UPDATE CITIES SET abbreviation="BLC" WHERE id=2;
UPDATE LOCATION SET name="Roca 201" WHERE id=1;
UPDATE COMPANY SET name="Bosch Inc." WHERE name="Bosch";
UPDATE ASSETS SET weekly_hours=45 WHERE project_id=2;
UPDATE ASSETS SET project_id=1 WHERE id=1;
UPDATE ASSETS SET project_id=2 WHERE id=5;

DELETE FROM COUNTRY WHERE abbreviation LIKE "BR";
DELETE FROM EMPLOYEES WHERE name LIKE "Juan Fernandez";
DELETE FROM PAYMENTS WHERE id=4;
DELETE FROM PROJECT WHERE id=3;
DELETE FROM CITIES WHERE id=2;
DELETE FROM CONTRACTS WHERE id=3;