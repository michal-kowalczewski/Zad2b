CREATE DATABASE zad2b;

GO

USE zad2b
CREATE TABLE Users(
    IdUser int NOT NULL IDENTITY(0,1),
    UserName varchar(50) UNIQUE,
    UserPassword varchar(64),
    UserDescription varchar(50)
    PRIMARY KEY(IdUser));

GO

Insert into Users
Values(
    'user0',
    '670253d8c638f94d12aa4c7504a2b35413c562049df0a4d6456e664bbe62dbfa',
    'first user');

Insert into Users
Values(
    'user1',
    '7e79a7aefd2649069ac1734796dd672895f1008a8613c34da1d76950e6f26b1e',
    'second user');

Insert into Users
Values(
    'user2',
    '574780479b6c2045911078106c8d6ec7f514e1b284c978c05af388d0c5e3277e',
	'third user');