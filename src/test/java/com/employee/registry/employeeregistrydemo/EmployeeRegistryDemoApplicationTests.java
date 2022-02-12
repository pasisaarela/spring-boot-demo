package com.employee.registry.employeeregistrydemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

// --- MAKE SURE THE MAIN APPLICATION IS RUNNING SO THE TESTS CAN CONNECT TO THE DATABASE ---

@SpringBootTest
class EmployeeRegistryDemoApplicationTests {
	
	private static int testUserId;

	public static int getTestUserId() {
		return testUserId;
	}

	public static void setTestUserId(int testUserId) {
		EmployeeRegistryDemoApplicationTests.testUserId = testUserId;
	}

	@Test
	void contextLoads() {
	}
	
    private static final String API_ROOT
    = "http://localhost:8080/employees";


	// Testing that API root responds
    @Test
    public void whenGetAllEmployees_thenOK() {
        Response response = RestAssured.get(API_ROOT);
     
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    // Create employee successful
    @Test
    public void whenPostEmployee_thenOk() {
    	Employee employee = new Employee();
    	employee.setName("Mikaela Sundqvist");
    	Response response = RestAssured.given()
    	          .contentType(MediaType.APPLICATION_JSON_VALUE)
    	          .body(employee)
    	          .post(API_ROOT);
    	setTestUserId(response.getBody().jsonPath().getInt("employeeId"));
    	assertEquals("Mikaela Sundqvist", response.getBody().jsonPath().get("name"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    // Create employee with too short name
    @Test
    public void whenPostTooShortEmployee_thenBadRequest() {
    	Employee employee = new Employee();
    	employee.setName(RandomStringUtils.randomAlphabetic(2));
    	Response response = RestAssured.given()
    	          .contentType(MediaType.APPLICATION_JSON_VALUE)
    	          .body(employee)
    	          .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }
    
    // Create employee with too long name
    @Test
    public void whenPostTooLongEmployee_thenBadRequest() {
    	Employee employee = new Employee();
    	employee.setName(RandomStringUtils.randomAlphabetic(70));
    	Response response = RestAssured.given()
    	          .contentType(MediaType.APPLICATION_JSON_VALUE)
    	          .body(employee)
    	          .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }
    
	// Get employee by ID successful
    @Test
    public void whenGetEmployeeById_thenOK() {
        Response response = RestAssured.get(API_ROOT+"/id/1001");
        assertEquals(1001, response.getBody().jsonPath().getInt("employeeId"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    // Get employee by ID failed
    @Test
    public void whenGetEmployeeById_thenNotFound() {
        Response response = RestAssured.get(API_ROOT+"/id/60001");
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
	// Get employee by Name successful
    @Test
    public void whenGetEmployeeByName_thenOK() {
        Response response = RestAssured.get(API_ROOT+"/name/Mira Virta");
        assertEquals("Mira Virta", response.getBody().jsonPath().get("name"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    // Get employee by Name failed
    @Test
    public void whenGetEmployeeByName_thenNotFound() {
        Response response = RestAssured.get(API_ROOT+"/name/"+RandomStringUtils.randomAlphabetic(16));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
    
    // edit existing employee succesful
    @Test
    public void whenPutEmployee_thenOk() {
    	Employee employee = new Employee();
    	employee.setName("Tero Testaaja");
    	Response response = RestAssured.given()
    	          .contentType(MediaType.APPLICATION_JSON_VALUE)
    	          .body(employee)
    	          .put(API_ROOT+"/update/"+testUserId);
    	assertEquals("Tero Testaaja", response.getBody().jsonPath().get("name"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    
    // Edit existing employee failed
    @Test
    public void whenPutEmployee_thenNotFound() {
    	Employee employee = new Employee();
    	employee.setName("Teemu Testaaja");
    	Response response = RestAssured.given()
    	          .contentType(MediaType.APPLICATION_JSON_VALUE)
    	          .body(employee)
    	          .put(API_ROOT+"/update/"+20000);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
    // Delete employee succesful 
    @Test
    public void whenDeleteEmployee_thenOk() {
    	Response response = RestAssured.given()
  	          .contentType(MediaType.APPLICATION_JSON_VALUE)
  	          .delete(API_ROOT+"/id/"+testUserId);
      assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    // Delete employee failed 
    @Test
    public void whenDeleteEmployee_thenNotFound() {
    	Response response = RestAssured.given()
  	          .contentType(MediaType.APPLICATION_JSON_VALUE)
  	          .delete(API_ROOT+"/id/"+30000);
      assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
}


