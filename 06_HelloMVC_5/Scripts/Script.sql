ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
CREATE USER web IDENTIFIED BY web DEFAULT tablespace users
quota unlimited ON users;
GRANT resource,CONNECT TO web;
