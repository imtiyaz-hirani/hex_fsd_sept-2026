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
create procedure emp_by_department(IN p_dept varchar(255))
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
	select *
	from employee
	where department = p_dept;
END
$$

drop procedure emp_by_department;

CALL emp_by_department('FINANCE'); # proc called with a parameter  -- p_dept = FINANCE
CALL emp_by_department('ADMIN'); # p_dept = ADMIN
CALL emp_by_department('HR'); # p_dept = HR
CALL emp_by_department('');

-- CAP to fetch employee names based on given department 
DELIMITER $$
create procedure emp_name_by_department(IN p_dept varchar(255))
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
    
    IF NOT EXISTS (select 1 from employee where p_branch IN ('CHENNAI','MUMBAI','NEW YORK')) THEN
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

-- CAP to count number of employees in a given department, return the count. 
# NOTE: procedures cannot return any value 

DELIMITER $$
create procedure cnt_emp_by_dept(IN p_dept varchar(255) , OUT p_cnt INT)
BEGIN
	select count(id) into p_cnt
    from employee
    where department = p_dept ;
END
$$

CALL cnt_emp_by_dept('ADMIN', @count_num); -- session variable. 
select @count_num;

/*
1. simple proc structure 
2. IN param
3. OUT param 
4. IF for validation 
5. exception state 45000 
6. INOUT parameter 
*/

/*
Views 
Triggers 
Cursor** 
*/

select * from employee; 

-- views (hide attributes / columns & rows/records)

create view v_emp AS 
select id,name,branch,department
from employee
where department NOT IN ('ADMIN')
WITH CHECK OPTION;

show tables; 

drop view v_emp;

update v_emp_dev
SET department = 'FINANCE'
where id = 7;

create view v_emp_dev AS 
select * 
from employee 
where department = 'DEV'
WITH CHECK OPTION;
 
drop view v_emp_dev;






