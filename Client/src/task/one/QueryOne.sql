CREATE TABLE dummy 
( 
id number not null, 
name varchar2(50) not null, 
phone number(11) not null, 
address varchar2(50) not null, 
salary number(20) not null, 
CONSTRAINT dummy_id PRIMARY KEY (id) 
)