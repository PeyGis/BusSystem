create database BusTicketProject;
use BusTicketProject; 

create table Admin(

adminId int not null,
adminName varchar(50),
password varchar(50),
primary key (adminId)
);

insert into Admin values(100, 'pages', 'coffy999');
select * from Admin;