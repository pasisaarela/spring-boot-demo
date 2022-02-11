package com.employee.registry.employeeregistrydemo;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Employee {

	private Integer employeeId;
	
	@Size(min=4, max = 64)
	private String name;

	@Column(name="datecreated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
    private Timestamp dateCreated;
	
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
		return dateCreated;
	}
	
	public void setTimeAdded(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	// Constructor from fields
	public Employee(Integer employeeId, @Size(min = 4, max = 64) String name, Timestamp dateCreated) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.dateCreated = dateCreated;
	}
	
	// Default constructor
	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", dateCreated=" + dateCreated + "]";
	}
}
