-- Create databases (schemas)
CREATE DATABASE IF NOT EXISTS productservicedb;
CREATE DATABASE IF NOT EXISTS userservicedb;
CREATE DATABASE IF NOT EXISTS orderservicedb;
CREATE DATABASE IF NOT EXISTS notificationservicedb;

-- Create users and grant privileges
CREATE USER IF NOT EXISTS 'productdbuser'@'%' IDENTIFIED BY 'cokgizlisifre';
CREATE USER IF NOT EXISTS 'userdbuser'@'%' IDENTIFIED BY 'cokgizlisifre';
CREATE USER IF NOT EXISTS 'orderdbuser'@'%' IDENTIFIED BY 'cokgizlisifre';
CREATE USER IF NOT EXISTS 'notificationdbuser'@'%' IDENTIFIED BY 'cokgizlisifre';

GRANT ALL PRIVILEGES ON productservicedb.* TO 'productdbuser'@'%';
GRANT ALL PRIVILEGES ON userservicedb.* TO 'userdbuser'@'%';
GRANT ALL PRIVILEGES ON orderservicedb.* TO 'orderdbuser'@'%';
GRANT ALL PRIVILEGES ON notificationservicedb.* TO 'notificationdbuser'@'%';

-- (optional) grant minimal access to root for all DBs
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;
