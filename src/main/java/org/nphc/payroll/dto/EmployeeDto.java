package org.nphc.payroll.dto;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EmployeeDto {

	public Object[] getInsertParameters(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInsertSQLType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] getUpdateObjects(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUpdateSQLType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee getValidEmployee(CSVRecord csv) {
		// TODO Auto-generated method stub
		return null;
	}

	public JsonNode getArrayNode(List<Employee> employeeList) {
		// TODO Auto-generated method stub
		return null;
	}

}
