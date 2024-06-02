drop table if exists member CASCADE;
 create table member
 (
     id   IDENTITY primary key,
      name varchar(255),
      primary key (id)
 );