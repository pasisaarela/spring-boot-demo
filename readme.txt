Employee registry REST API

The service is running locally and connected to h2 in-memory database. Default configuration is set to port 8080. 

Query all employees
GET - http://localhost:8080/employees/

Query employee by employee id-number
GET - http://localhost:8080/employees/id/<employee-id>

Query employee by name – (Case insensitive)
GET -  http://localhost:8080/employees/name/<firstname lastname>

Add a new employee
POST -  http://localhost:8080/employees/

Edit an existing employee
PUT - http://localhost:8080/employees/update/<employee-id>

Delete an employee 
DELETE - http://localhost:8080/employees/id/<employee-id>


To build with maven you need to add following goal:
spring-boot:start


JSON Body for adding or updating users

    {
        "name": "FirstName LastName"
    }
