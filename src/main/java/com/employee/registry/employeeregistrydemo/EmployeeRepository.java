package com.employee.registry.employeeregistrydemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee findByNameIgnoreCase(String name);

}
