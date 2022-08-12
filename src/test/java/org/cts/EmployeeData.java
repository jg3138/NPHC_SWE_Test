package org.cts;

import java.text.SimpleDateFormat;

import com.opencsv.bean.CsvBindByName;

public class EmployeeData {
	@CsvBindByName
	private String id;
	@CsvBindByName
	private String login;
	@CsvBindByName
	private String name;
	@CsvBindByName
	private float salary;
	@CsvBindByName
	private String startDate;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private java.util.Date date;
	String format = formatter.format(date);
	
	public EmployeeData(String id, String login, String name, float salary, String startDate) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
	}
	
}

