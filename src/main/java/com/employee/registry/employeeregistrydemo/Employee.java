package com.employee.registry.employeeregistrydemo;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class Employee {

	private Integer employeeId;
	
	@Size(min=4, max = 64)
	private String name;

    private Timestamp timeAdded = new Timestamp(System.currentTimeMillis());
	
	@Id
	@GeneratedValue
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getTimeAdded() {
		return timeAdded;
	}
	
	public void setTimeAdded(Timestamp timeAdded) {
		this.timeAdded = timeAdded;
	}
	
	// Constructor from fields
	public Employee(Integer employeeId, @Size(min = 4, max = 64) String name, Timestamp dateCreated) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.timeAdded = dateCreated;
	}
	
	// Default constructor
	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", dateCreated=" + timeAdded + "]";
	}
}
