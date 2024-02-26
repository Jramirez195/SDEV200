# M06 Programing Assignment 1
## Chapter 34 - 34.1 {Access and update a Staff table}

Write a program that views, inserts, and updates staff information stored in a database. The View button displays a record with a specified ID. The insert button inserts and new record. The update button updates the record for the specified ID. 

The Staff table is created as follows:

```
create table Staff ( id char(9) not 
null, lastName varchar(15),
firstName varchar(15),
    mi char(1), address varchar(20),
city varchar(20), state char(2),
telephone char(10), email.
varchar(40), primary key (id) );
```