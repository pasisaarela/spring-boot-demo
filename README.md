# Spring Boot Demo - Employee Registry 

This is a simple Spring Boot microservice REST API. The service uses H2 in memory database to store the values. While running the service default port is set to :8080.

## Installation

Prerequisites: JDK 1.8 and Maven 3.x

Clone this repository and navigate to the root of the cloned repository.


Start the service with command:
```bash
mvn spring-boot:start
```
## Running unit tests
You can run unit tests with the following command (Please note: main service needs to be running so test cases can connect to h2 database): 
```bash
mvn test
```


## Usage
The service can be reached and used with following commands:


### Listing all employees
```bash
GET - http://localhost:8080/employees/
```
Returns a complete list of employees stored.

### Finding an employee by ID-number
```bash
GET - http://localhost:8080/employees/id/<employee-id>
```
Returns an employee object listed as JSON.


### Finding an employee by name
```bash
GET -  http://localhost:8080/employees/name/<firstname lastname>
```
Returns an employee object listed as JSON. Letters in the name are NOT case sensitive.

### Adding a new employee
Remember to include request body (Set to JSON)
```bash
POST -  http://localhost:8080/employees/
```
```bash
{
	"name": "FirstName LastName"
}
```

### Editing an existing employee

Remember to set employee ID in PUT request and include request body (Set to JSON)
```bash
PUT - http://localhost:8080/employees/update/<employee-id>
```
```bash
{
	"name": "FirstName LastName"
}
```
Returns an updated employee object listed as JSON.

### Deleting an employee by ID-number
```bash
DELETE - http://localhost:8080/employees/id/<employee-id>
```
Returns status ok if deletion is succesful.