-- ετσι παιρνουμε το ονομα του υπλαλληλου που συνδεθηκε στην εφαρμογη μας
select first_name, last_name from employees where irs_number='%s';

-- ετσι παιρνουμε τα δεδομενα που θελουμε για να τα προβαλλουμε στον πινακα των οχηματων
SELECT * FROM vehicles_view

-- βρισκουμε τα πιο συχνα χρησιμοποιουμενα οχηματα στις ενοικιασεις
select rents_with_payments_customers_vehicles_and_employees.vehicle_name,
count(rents_with_payments_customers_vehicles_and_employees.vehicle_name) as counter
from rents_with_payments_customers_vehicles_and_employees
group by rents_with_payments_customers_vehicles_and_employees.vehicle_name
order by counter desc;

-- ετσι παιρνουμε ολες τις στηλες του stores για να τις προβαλλουμε
SELECT * FROM stores

-- βρισκουμε ολα τα τηλεφωνα που ανηκουν σε ενα καταστημα
SELECT phone_number FROM phone_numbers_stores where store_id='%d';

-- βρισκουμε ολα τα emails που ανηκουν σε ενα καταστημα
SELECT email_address FROM email_addresses_stores where store_id='%d';

-- ετσι παιρνουμε ολες τις στηλες του customers_view για να τις προβαλλουμε
SELECT * FROM customers_view;

-- μετραμε τους customers για να προβαλλουμε τον αριθμο τους
select count(irs_number) as totalCustomers from customers_view;

--- βρισκουμε τους πελατες (ενωνοντας το ονομα καθε πελατη) με τις περισσοτερες perfect return_state ενοικιασεις
select concat(customer_first_name, " ", customer_last_name) as customer_name, count(customer_id) as counter, return_state
from `rents_with_payments_customers_vehicles_and_employees`
group by customer_id
having return_state='Perfect'
order by counter desc;

-- παιρνουμε τα στοιχεια που θελουμε για τον πινακα Employment Data
SELECT * FROM employment_data;

-- παιρνουμε τα στοιχεια που θελουμε για τον πινακα Employees
SELECT * FROM employees;

-- μετραμε τους employees για να προβαλλουμε τον αριθμο τους
select count(irs_number) as totalEmployees from employees;

-- προβαλλουμε τις ενοικιασεις (ειτε ολες, ειτε τις τρεχουσες, ειτε τις αρχειοθετημενες) και τις ταξινομουμε με βαση την επιλεγμενη στηλη
select * from rents_with_payments_customers_vehicles_and_employees {where finish_date >= CURDATE()} order by %s;

-- τα ιδια
select * from reserves_with_customers_and_vehicles;

-- ελεγχουμε αν υπαρχει καποιο απο τα μοναδικα στοιχεια του πελατη οταν παμε να προσθεσουμε νεο
SELECT irs_number, social_security_number, driver_license FROM customers_view
WHERE irs_number='%s' OR social_security_number='%s' OR driver_license='%s';

-- προσθετουμε νεο πελατη
INSERT INTO customers_view (irs_number, social_security_number, first_name, 
last_name, driver_license, first_registration, city, postal_code, street, 
street_number) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');

UPDATE customers_view SET irs_number='%s', social_security_number='%s', 
first_name='%s', last_name='%s', driver_license='%s', city='%s', 
postal_code='%s', street='%s', street_number='%s' WHERE irs_number='%s';

-- επιλογη του σωστου πελατη για εγγραφη ή αλλαγη στον πινακα των ενοικιασεων
select customer_id, first_name, last_name from customers_view where irs_number='%s';

-- νεα εγγραφη στα rents
INSERT INTO rents (start_date, finish_date, start_location, finish_location, return_state, customer_id, license_plate, irs_number) VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s');

-- νεα εγγραφη στα payment_transaction
INSERT INTO payment_transaction (license_plate, start_date, payment_amount, payment_method) VALUES ('%s', '%s', '%s', '%s');

-- ενημερωση στα rents
UPDATE rents SET license_plate='%s', start_date='%s', start_location='%s',
finish_location='%s', finish_date='%s', return_state='%s', customer_id='%s', irs_number='%s'
WHERE license_plate='%s' and start_date='%s';

-- ενημερωση στα payment_transaction 
UPDATE payment_transaction SET license_plate='%s', start_date='%s', payment_amount='%s', payment_method='%s'
WHERE license_plate='%s' and start_date='%s';

-- νεα εγγραφη στα reserves
INSERT INTO reserves (license_plate, start_date, start_location, finish_location, finish_date, paid, customer_id) VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s');

-- ενημερωση στα reserves
UPDATE reserves SET license_plate='%s', start_date='%s', start_location='%s', finish_location='%s', finish_date='%s', paid=%s, customer_id='%s'
WHERE license_plate='%s' and start_date='%s';

-- διαγραφη πελατη
DELETE FROM customers_view WHERE irs_number='%s';

-- διαγραφη rent
DELETE FROM rents WHERE license_plate='%s' AND start_date='%s';

-- διαγραφη reserve
DELETE FROM reserves WHERE license_plate='%s' AND start_date='%s';

--ευρεση του επιλεγμενου οχηματος οταν παμε να κανουμε edit σε συγκεκριμενη κρατηση ή ενοικιαση 
select license_plate, make, model from vehicles where license_plate='%s';

-- επιλογη ολων των οχηματων τα οποια ειναι διαθεσιμα για ενοικιαση ή κρατηση
-- πρεπει το οχημα να μην ειναι νοικιασμενο ή κρατημενο για τις ημερομηνιες που ζηταει ο πελατης
-- πρεπει το start_date να μην βρισκεται αναμεσα σε υπαρχοντα start_date - finish_date μιας κρατησης ή μιας ενοικιασης
-- το ιδιο και για το finish_date
-- επισης δεν πρεπει να υπαρχει ενα start_date πριν απο υπαρχον start_date οταν το finish_date ειναι μετα απο το αντιστοιχο finish_date
select a.plate, a.make, a.model 
from 
(
	select vehicles.license_plate as plate, vehicles.make as make, vehicles.model as model 
	from vehicles
	where vehicles.license_plate not in
	(
		select rents.license_plate 
		from rents where '%s' between rents.start_date and rents.finish_date 
		or '%s' between rents.start_date and rents.finish_date 
		or ('%s' <= rents.start_date and '%s' >= rents.finish_date)
	)
) a where a.plate not in
	(
		select reserves.license_plate 
		from reserves 
		where '%s' between reserves.start_date and reserves.finish_date 
		or '%s' between reserves.start_date and reserves.finish_date 
		or ('%s' <= reserves.start_date and '%s' >= reserves.finish_date)
	);

-- παιρνουμε απο τη βαση τους πελατες που μπορουμε να επιλεξουμε σε μια νεα κρατηση ή ενοικιαση
select first_name, last_name, irs_number from customers_view;

-- με αυτον τον τροπο (συνθετοντας το query απο κομματια αναλογα με το Input του χρηστη) κανουμε αναζητηση στις ημερομηνιες
-- αμα θελουμε να μην ψαξουμε για ενα απο τα day, month, year βαζουμε στην αναζητηση "_" και αφαιρουμε το αντιστοιχο κομματι απο το query
SELECT * FROM reserves_with_customers_and_vehicles WHERE day(start_date)='%s' and month(start_date)='%s' and year(start_date)='΄%s'

-- με παρομοιο τροπο κανουμε αναζητηση πελατων κλπ
SELECT * FROM reserves_with_customers_and_vehicles WHERE 
LOWER(customer_first_name) LIKE LOWER('%%') AND 
LOWER(customer_last_name) LIKE LOWER('%%') and 
LOWER(customer_irs_number) LIKE LOWER('%%');

--ακολουθει ενα παραδειγμα απο τα triggers, ολα τα υπολοιπα βρισκονται μεσα στο αρχειο "database queries.sql" με αναλυτικες εξηγησεις
CREATE DEFINER=`root`@`localhost` TRIGGER `rents_BEFORE_INSERT` BEFORE INSERT ON `rents` FOR EACH ROW BEGIN
	set new.start_date = curdate();
    if new.finish_date < new.start_date then
			SIGNAL SQLSTATE VALUE '45000'
					SET MESSAGE_TEXT = '[table:rents] - finish date cannot be before start date';
			END IF;
END

-- Ακολουθουν τα view που φτιάξαμε
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `customers_view` AS
    SELECT 
        `customers`.`customer_id` AS `customer_id`,
        `customers`.`irs_number` AS `irs_number`,
        `customers`.`social_security_number` AS `social_security_number`,
        `customers`.`first_name` AS `first_name`,
        `customers`.`last_name` AS `last_name`,
        `customers`.`driver_license` AS `driver_license`,
        `customers`.`first_registration` AS `first_registration`,
        `customers`.`city` AS `city`,
        `customers`.`postal_code` AS `postal_code`,
        `customers`.`street` AS `street`,
        `customers`.`street_number` AS `street_number`
    FROM
        `customers`
		
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `employment_data` AS
    SELECT 
        `a`.`first_name` AS `first_name`,
        `a`.`last_name` AS `last_name`,
        `a`.`irs_number` AS `irs_number`,
        `a`.`start_date` AS `start_date`,
        `a`.`finish_date` AS `finish_date`,
        `a`.`position` AS `position`,
        `rent_a_car_schema`.`stores`.`city` AS `city`
    FROM
        ((SELECT 
            `rent_a_car_schema`.`works`.`irs_number` AS `irs_number`,
                `rent_a_car_schema`.`works`.`store_id` AS `store_id`,
                `rent_a_car_schema`.`works`.`start_date` AS `start_date`,
                `rent_a_car_schema`.`works`.`finish_date` AS `finish_date`,
                `rent_a_car_schema`.`works`.`position` AS `position`,
                `rent_a_car_schema`.`employees`.`first_name` AS `first_name`,
                `rent_a_car_schema`.`employees`.`last_name` AS `last_name`
        FROM
            (`rent_a_car_schema`.`works`
        JOIN `rent_a_car_schema`.`employees` ON ((`rent_a_car_schema`.`works`.`irs_number` = `rent_a_car_schema`.`employees`.`irs_number`)))) `a`
        JOIN `rent_a_car_schema`.`stores` ON ((`a`.`store_id` = `rent_a_car_schema`.`stores`.`store_id`)))		

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vehicles_view` AS
    SELECT 
        `a`.`make` AS `make`,
        `a`.`model` AS `model`,
        `a`.`license_plate` AS `license_plate`,
        `a`.`type` AS `type`,
        `a`.`year` AS `year`,
        `a`.`cylinder_capacity` AS `cylinder_capacity`,
        `a`.`horse_power` AS `horse_power`,
        `a`.`kilometers` AS `kilometers`,
        `a`.`damages` AS `damages`,
        `a`.`malfunctions` AS `malfunctions`,
        `a`.`last_service` AS `last_service`,
        `a`.`next_service` AS `next_service`,
        `a`.`insurance_exp_date` AS `insurance_exp_date`,
        `rent_a_car_schema`.`stores`.`city` AS `city`
    FROM
        ((SELECT 
            `rent_a_car_schema`.`vehicles`.`make` AS `make`,
                `rent_a_car_schema`.`vehicles`.`model` AS `model`,
                `rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,
                `rent_a_car_schema`.`vehicles`.`year` AS `year`,
                `rent_a_car_schema`.`vehicles`.`cylinder_capacity` AS `cylinder_capacity`,
                `rent_a_car_schema`.`vehicles`.`horse_power` AS `horse_power`,
                `rent_a_car_schema`.`vehicles`.`kilometers` AS `kilometers`,
                `rent_a_car_schema`.`vehicles`.`damages` AS `damages`,
                `rent_a_car_schema`.`vehicles`.`malfunctions` AS `malfunctions`,
                `rent_a_car_schema`.`vehicles`.`last_service` AS `last_service`,
                `rent_a_car_schema`.`vehicles`.`next_service` AS `next_service`,
                `rent_a_car_schema`.`vehicles`.`insurance_exp_date` AS `insurance_exp_date`,
                `rent_a_car_schema`.`vehicles`.`store_id` AS `store_id`,
                `rent_a_car_schema`.`vehicle_type`.`vehicle_type` AS `type`
        FROM
            (`rent_a_car_schema`.`vehicles`
        LEFT JOIN `rent_a_car_schema`.`vehicle_type` ON ((`rent_a_car_schema`.`vehicles`.`type` = `rent_a_car_schema`.`vehicle_type`.`vehicle_type_id`)))) `a`
        LEFT JOIN `rent_a_car_schema`.`stores` ON ((`a`.`store_id` = `rent_a_car_schema`.`stores`.`store_id`)))
		
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `reserves_with_customers_and_vehicles` AS
    SELECT 
        `c`.`start_date` AS `start_date`,
        `c`.`finish_date` AS `finish_date`,
        `c`.`start_location` AS `start_location`,
        `c`.`finish_location` AS `finish_location`,
        `c`.`paid` AS `paid`,
        `c`.`customer_id` AS `customer_id`,
        `c`.`customer_first_name` AS `customer_first_name`,
        `c`.`customer_last_name` AS `customer_last_name`,
        `c`.`customer_irs_number` AS `customer_irs_number`,
        `c`.`license_plate` AS `vehicle_license_plate`,
        `c`.`make` AS `vehicle_make`,
        `c`.`model` AS `vehicle_model`
    FROM
        (SELECT 
            `b`.`lp` AS `lp`,
                `b`.`start_date` AS `start_date`,
                `b`.`start_location` AS `start_location`,
                `b`.`finish_date` AS `finish_date`,
                `b`.`finish_location` AS `finish_location`,
                `b`.`paid` AS `paid`,
                `b`.`customer_id` AS `customer_id`,
                `b`.`customer_first_name` AS `customer_first_name`,
                `b`.`customer_last_name` AS `customer_last_name`,
                `b`.`customer_irs_number` AS `customer_irs_number`,
                `b`.`license_plate` AS `license_plate`,
                `b`.`model` AS `model`,
                `b`.`type` AS `type`,
                `b`.`make` AS `make`,
                `b`.`year` AS `year`,
                `b`.`kilometers` AS `kilometers`,
                `b`.`cylinder_capacity` AS `cylinder_capacity`,
                `b`.`horse_power` AS `horse_power`,
                `b`.`damages` AS `damages`,
                `b`.`malfunctions` AS `malfunctions`,
                `b`.`next_service` AS `next_service`,
                `b`.`insurance_exp_date` AS `insurance_exp_date`,
                `b`.`last_service` AS `last_service`,
                `b`.`store_id` AS `store_id`
        FROM
            (SELECT 
            `a`.`lp` AS `lp`,
                `a`.`start_date` AS `start_date`,
                `a`.`start_location` AS `start_location`,
                `a`.`finish_date` AS `finish_date`,
                `a`.`finish_location` AS `finish_location`,
                `a`.`paid` AS `paid`,
                `a`.`customer_id` AS `customer_id`,
                `a`.`customer_first_name` AS `customer_first_name`,
                `a`.`customer_last_name` AS `customer_last_name`,
                `a`.`customer_irs_number` AS `customer_irs_number`,
                `rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,
                `rent_a_car_schema`.`vehicles`.`model` AS `model`,
                `rent_a_car_schema`.`vehicles`.`type` AS `type`,
                `rent_a_car_schema`.`vehicles`.`make` AS `make`,
                `rent_a_car_schema`.`vehicles`.`year` AS `year`,
                `rent_a_car_schema`.`vehicles`.`kilometers` AS `kilometers`,
                `rent_a_car_schema`.`vehicles`.`cylinder_capacity` AS `cylinder_capacity`,
                `rent_a_car_schema`.`vehicles`.`horse_power` AS `horse_power`,
                `rent_a_car_schema`.`vehicles`.`damages` AS `damages`,
                `rent_a_car_schema`.`vehicles`.`malfunctions` AS `malfunctions`,
                `rent_a_car_schema`.`vehicles`.`next_service` AS `next_service`,
                `rent_a_car_schema`.`vehicles`.`insurance_exp_date` AS `insurance_exp_date`,
                `rent_a_car_schema`.`vehicles`.`last_service` AS `last_service`,
                `rent_a_car_schema`.`vehicles`.`store_id` AS `store_id`
        FROM
            ((SELECT 
            `rent_a_car_schema`.`reserves`.`license_plate` AS `lp`,
                `rent_a_car_schema`.`reserves`.`start_date` AS `start_date`,
                `rent_a_car_schema`.`reserves`.`start_location` AS `start_location`,
                `rent_a_car_schema`.`reserves`.`finish_date` AS `finish_date`,
                `rent_a_car_schema`.`reserves`.`finish_location` AS `finish_location`,
                `rent_a_car_schema`.`reserves`.`paid` AS `paid`,
                `rent_a_car_schema`.`customers`.`customer_id` AS `customer_id`,
                `rent_a_car_schema`.`customers`.`first_name` AS `customer_first_name`,
                `rent_a_car_schema`.`customers`.`last_name` AS `customer_last_name`,
                `rent_a_car_schema`.`customers`.`irs_number` AS `customer_irs_number`
        FROM
            (`rent_a_car_schema`.`reserves`
        LEFT JOIN `rent_a_car_schema`.`customers` ON ((`rent_a_car_schema`.`reserves`.`customer_id` = `rent_a_car_schema`.`customers`.`customer_id`)))) `a`
        LEFT JOIN `rent_a_car_schema`.`vehicles` ON ((`a`.`lp` = `rent_a_car_schema`.`vehicles`.`license_plate`)))) `b`) `c`
		
--- !!!!!! DANGER!!! DO NOT PROCEED!!! YOUR LIFE MIGHT END WHILE READING THE FOLLOWING LINES!!!

-- 
-- κανουμε LEFT join 5 πινακες τον ενα μετα τον αλλο με το αποτελεσμα του προηγουμενου 
-- join και συνδεουμε τους πινακες με βαση τα τα primary keys τους
-- 

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `rents_with_payments_customers_vehicles_and_employees` AS
    SELECT 
        `d`.`start_date` AS `start_date`,
        `d`.`finish_date` AS `finish_date`,
        `d`.`start_location` AS `start_location`,
        `d`.`finish_location` AS `finish_location`,
        `d`.`return_state` AS `return_state`,
        `d`.`customer_id` AS `customer_id`,
        `d`.`customer_first_name` AS `customer_first_name`,
        `d`.`customer_last_name` AS `customer_last_name`,
        `d`.`customer_irs_number` AS `customer_irs_number`,
        `d`.`vehicle_license_plate` AS `vehicle_license_plate`,
        CONCAT(`d`.`vehicle_make`, ' ', `d`.`vehicle_model`) AS `vehicle_name`,
        `d`.`employee_irs_number` AS `employee_irs_number`,
        `d`.`employee_first_name` AS `employee_first_name`,
        `d`.`employee_last_name` AS `employee_last_name`,
        `d`.`amount` AS `amount`,
        `d`.`method` AS `method`
    FROM
        (SELECT 
            `c`.`start_date` AS `start_date`,
                `c`.`finish_date` AS `finish_date`,
                `c`.`start_location` AS `start_location`,
                `c`.`finish_location` AS `finish_location`,
                `c`.`return_state` AS `return_state`,
                `c`.`customer_id` AS `customer_id`,
                `c`.`customer_first_name` AS `customer_first_name`,
                `c`.`customer_last_name` AS `customer_last_name`,
                `c`.`customer_irs_number` AS `customer_irs_number`,
                `c`.`license_plate` AS `vehicle_license_plate`,
                `c`.`make` AS `vehicle_make`,
                `c`.`model` AS `vehicle_model`,
                `c`.`irs_number` AS `employee_irs_number`,
                `c`.`first_name` AS `employee_first_name`,
                `c`.`last_name` AS `employee_last_name`,
                `rent_a_car_schema`.`payment_transaction`.`payment_amount` AS `amount`,
                `rent_a_car_schema`.`payment_transaction`.`payment_method` AS `method`
        FROM
            ((SELECT 
            `b`.`rents_irs_number` AS `rents_irs_number`,
                `b`.`start_date` AS `start_date`,
                `b`.`finish_date` AS `finish_date`,
                `b`.`start_location` AS `start_location`,
                `b`.`finish_location` AS `finish_location`,
                `b`.`return_state` AS `return_state`,
                `b`.`customer_id` AS `customer_id`,
                `b`.`customer_first_name` AS `customer_first_name`,
                `b`.`customer_last_name` AS `customer_last_name`,
                `b`.`customer_irs_number` AS `customer_irs_number`,
                `b`.`license_plate` AS `license_plate`,
                `b`.`model` AS `model`,
                `b`.`make` AS `make`,
                `rent_a_car_schema`.`employees`.`irs_number` AS `irs_number`,
                `rent_a_car_schema`.`employees`.`first_name` AS `first_name`,
                `rent_a_car_schema`.`employees`.`last_name` AS `last_name`
        FROM
            ((SELECT 
            `a`.`rents_irs_number` AS `rents_irs_number`,
                `a`.`start_date` AS `start_date`,
                `a`.`finish_date` AS `finish_date`,
                `a`.`start_location` AS `start_location`,
                `a`.`finish_location` AS `finish_location`,
                `a`.`return_state` AS `return_state`,
                `a`.`customer_id` AS `customer_id`,
                `a`.`customer_first_name` AS `customer_first_name`,
                `a`.`customer_last_name` AS `customer_last_name`,
                `a`.`customer_irs_number` AS `customer_irs_number`,
                `rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,
                `rent_a_car_schema`.`vehicles`.`model` AS `model`,
                `rent_a_car_schema`.`vehicles`.`make` AS `make`
        FROM
            ((SELECT 
            `rent_a_car_schema`.`rents`.`license_plate` AS `lp`,
                `rent_a_car_schema`.`rents`.`irs_number` AS `rents_irs_number`,
                `rent_a_car_schema`.`rents`.`start_date` AS `start_date`,
                `rent_a_car_schema`.`rents`.`finish_date` AS `finish_date`,
                `rent_a_car_schema`.`rents`.`start_location` AS `start_location`,
                `rent_a_car_schema`.`rents`.`finish_location` AS `finish_location`,
                `rent_a_car_schema`.`rents`.`return_state` AS `return_state`,
                `rent_a_car_schema`.`customers`.`customer_id` AS `customer_id`,
                `rent_a_car_schema`.`customers`.`first_name` AS `customer_first_name`,
                `rent_a_car_schema`.`customers`.`last_name` AS `customer_last_name`,
                `rent_a_car_schema`.`customers`.`irs_number` AS `customer_irs_number`
        FROM
            (`rent_a_car_schema`.`rents`
        LEFT JOIN `rent_a_car_schema`.`customers` ON ((`rent_a_car_schema`.`rents`.`customer_id` = `rent_a_car_schema`.`customers`.`customer_id`)))) `a`
        LEFT JOIN `rent_a_car_schema`.`vehicles` ON ((`a`.`lp` = `rent_a_car_schema`.`vehicles`.`license_plate`)))) `b`
        LEFT JOIN `rent_a_car_schema`.`employees` ON ((`b`.`rents_irs_number` = `rent_a_car_schema`.`employees`.`irs_number`)))) `c`
        LEFT JOIN `rent_a_car_schema`.`payment_transaction` ON (((`c`.`license_plate` = `rent_a_car_schema`.`payment_transaction`.`license_plate`)
            AND (`c`.`start_date` = `rent_a_car_schema`.`payment_transaction`.`start_date`))))) `d`
			
			

