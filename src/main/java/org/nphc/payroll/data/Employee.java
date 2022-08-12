package org.nphc.payroll.data;

import java.time.LocalDate;

/* holds employee details for SQL */

public class Employee {
	private String id, login, name;
	private Double salary;
	private LocalDate startDate;
	
	public Employee() {
		
	}
	
	public Employee(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	public String setId(String id) {
		return login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
