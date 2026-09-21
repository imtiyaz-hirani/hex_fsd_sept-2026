create database fsd_hex_sept_2026;
use fsd_hex_sept_2026;

-- table customer
create table employee(
id INT primary key auto_increment, 
name varchar(255) NOT NULL,
branch ENUM('CHENNAI', 'MUMBAI', 'NEW YORK'),
city varchar(255),
department ENUM('ADMIN', 'DEV', 'FINANCE')
);

desc employee;

-- insert 
insert into employee(name,branch,city,department) values 
('harry potter', 'MUMBAI', 'london', 'DEV'),
('ronald weasley', 'MUMBAI', 'london', 'ADMIN'),
('hermione granger', 'CHENNAI', 'surrey', 'FINANCE');

-- modify attribute 
alter table employee 
MODIFY department ENUM('ADMIN', 'DEV', 'FINANCE');

-- create a procedure (CAP) to display all employee records 

DELIMITER $$
create procedure all_emp()
BEGIN
	select * from employee;
END
$$

CALL all_emp;

-- CAP to fetch employee names based on given department 
DELIMITER $$
create procedure emp_by_branch(IN p_dept varchar(255))
BEGIN
	IF p_dept = '' then
		-- throw exception 
        signal sqlstate "45000" # we r telling the db, that i signal you to throw a exception/error here 
        SET message_text = 'department should not be empty';
    end if;
    
    IF NOT EXISTS (select 1 from employee where department = p_dept)  THEN
		signal sqlstate "45000"
        SET message_text = 'Invalid value of department given';
    END IF;
    
	-- this runs only if validation succeeds 
	select name
	from employee
	where department = p_dept;
END
$$

drop procedure emp_by_branch;

CALL emp_by_branch('FINANCE'); # proc called with a parameter  -- p_dept = FINANCE
CALL emp_by_branch('ADMIN'); # p_dept = ADMIN
CALL emp_by_branch('HR'); # p_dept = HR
CALL emp_by_branch('');


select 1 from employee where department = 'HR';
 

-- CAP to update the branch of an employee based on given id. 
-- take id and branch as input and update the branch record. 

DELIMITER $$
create procedure update_emp_branch(IN p_id INT, IN p_branch varchar(255))
BEGIN
	-- validate the id 
	IF NOT EXISTS (select 1 from employee where id = p_id) THEN
		signal sqlstate "45000"
        SET message_text = 'Please provide valid id';
    END if; 
    
	-- validate for null or empty inputs
    IF p_branch = '' OR p_branch IS null THEN
		signal sqlstate "45000"
        SET message_text = 'Please provide branch value';
    END if; 
    
    IF NOT EXISTS (select 1 from employee where branch = p_branch) THEN
		signal sqlstate "45000"
        SET message_text = 'Please provide valid branch value';
    END IF;
    
	update employee
    SET branch = p_branch
    WHERE id = p_id; 
END
$$
drop procedure update_emp_branch;
CALL update_emp_branch(8,'NEW YORK');
CALL update_emp_branch(8,null);
CALL update_emp_branch(10,'LONDON');











