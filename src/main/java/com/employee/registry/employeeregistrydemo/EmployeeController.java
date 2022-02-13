package com.employee.registry.employeeregistrydemo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	// Retrieve all employees
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees() {
		return employeeRepository.findAll();
	}
	
	// Retrieve a single employee by id
	@GetMapping("/employees/id/{employeeId}")
	public Employee retrieveEmployee(@PathVariable int employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		if (!employee.isPresent()) {
			throw new UserNotFoundException("employee id:" +employeeId+ " not found");
		} else {
			return employee.get();
		}
	}
	
	// Retrieve a single employee by name
	@GetMapping("/employees/name/{name}")
	public Employee retrieveEmployee(@PathVariable String name) {
		Employee employee = employeeRepository.findByNameIgnoreCase(name);
		
		if (employee==null) {
			throw new UserNotFoundException("employee name: " +name+ " not found");
		} else {
			return employee;
		}
	}

	// Adding an employee
	@PostMapping("/employees")
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		 employeeRepository.save(employee);
		return employee;
	} 
	
	
	// Update existing employee
	@PutMapping("/employees/update/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable int id) {
		return employeeRepository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					return employeeRepository.save(employee);
				})
				.orElseGet(() -> {
					throw new UserNotFoundException("employee id: " +id+" not found");
				});
	}
	
	
	// Delete employee by id
	@DeleteMapping("/employees/id/{employeeId}")
	public void deleteEmployeeById(@PathVariable int employeeId) {
		if (employeeRepository.existsById(employeeId)) {
			employeeRepository.deleteById(employeeId);
		} else {
			throw new UserNotFoundException("employee id: " +employeeId+" not found");
		}
		
		
	}
	
	
}
