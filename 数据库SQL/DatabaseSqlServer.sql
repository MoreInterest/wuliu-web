
if exists (select * from sysobjects where name='t_user')

 drop table t_user 

 go 

CREATE TABLE t_user (
  id int identity(1,1) primary key,
  username varchar(200) not NULL ,
  password varchar(200) not NULL ,
  role int not NULL ,
) 
go
insert  into t_user(username,password,role) values ('admin','admin',1);

if exists (select * from sysobjects where name='t_role')

 drop table t_role 

 go 

CREATE TABLE t_role (
  id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  descp varchar(200) not NULL ,
) 
go
insert  into t_role(name,descp) values ('管理员','管理员');
insert  into t_role(name,descp) values ('员工','员工');

if exists (select * from sysobjects where name='t_staff')

 drop table t_staff 

 go 

CREATE TABLE t_staff (
   id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  sex varchar(200) not NULL ,
  age varchar(200) not NULL ,
  tel varchar(200) not NULL ,
  address varchar(200) not NULL ,
  email varchar(200) not NULL ,
  users int not NULL ,
  zhandian varchar(200) not NULL ,
)
go
insert  into t_role(name,descp) values ('用户','用户');

if exists (select * from sysobjects where name='t_member')

 drop table t_member 

 go 

CREATE TABLE t_member (
   id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  sex varchar(200) not NULL ,
  age varchar(200) not NULL ,
  tel varchar(200) not NULL ,
  address varchar(200) not NULL ,
  email varchar(200) not NULL ,
  users int not NULL ,
)
go

if exists (select * from sysobjects where name='t_express')

 drop table t_express 

 go 

CREATE TABLE t_express (
   id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  member int not NULL ,
  chufa varchar(200) not NULL ,
  shoujianname varchar(200) not NULL ,
  tel varchar(200) not NULL ,
  place varchar(200) not NULL ,
  leibie varchar(200) not NULL ,
  settime datetime not NULL ,
  descp varchar(200) not NULL ,
  state varchar(200) not NULL ,
)
go

if exists (select * from sysobjects where name='t_cars')

 drop table t_cars 

 go 

CREATE TABLE t_cars (
   id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  leixing varchar(200) not NULL ,
  zaizhong varchar(200) not NULL ,
  xiulicontent varchar(200) not NULL ,
  jiayou varchar(200) not NULL ,
  beizhucontent varchar(200) not NULL ,
  jiashiyuancontent varchar(200) not NULL ,
  carno varchar(200) not NULL ,
  luxiancontent varchar(200) not NULL ,
)
go

if exists (select * from sysobjects where name='t_finance')

 drop table t_finance 

 go 

CREATE TABLE t_finance (
   id int identity(1,1) primary key,
  name varchar(200) not NULL ,
  settime datetime not NULL ,
  mingxi varchar(200) not NULL ,
  descp varchar(200) not NULL ,
)
go
