This project is configured to call a stored procedure from a MySQL database. The steps to prepare the environment are described below:

- Select test database
	> USE test;

- Create table orders
	> CREATE TABLE orders (orderId integer primary key, description varchar(255));
	
- Create the stored procedure 'get_order'
	> DELIMITER //
	> CREATE PROCEDURE get_order (IN id INT, OUT descrip VARCHAR(255))
	> BEGIN
	> SELECT description INTO descrip FROM orders
	> WHERE orderId = id;
	> END //
