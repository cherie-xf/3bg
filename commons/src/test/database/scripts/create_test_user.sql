DROP SCHEMA IF EXISTS `3bdbtest`;
CREATE SCHEMA IF NOT EXISTS `3bdbtest` DEFAULT CHARACTER SET utf8;

DROP SCHEMA IF EXISTS `3bdbtest_service`;
CREATE SCHEMA IF NOT EXISTS `3bdbtest_service` DEFAULT CHARACTER SET utf8;



DROP USER 'test'@'localhost';
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
GRANT ALL ON `3bdbtest`.* TO 'test'@'localhost';
GRANT ALL ON `3bdbtest_service`.* TO 'test'@'localhost';
FLUSH PRIVILEGES;
