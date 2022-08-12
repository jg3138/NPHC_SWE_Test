package org.nphc.payroll.repository;

import java.util.List;

import org.nphc.payroll.dto.Employee;
import org.nphc.payroll.dto.EmployeeDto;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class JDBCTemplate {

	public void execute(String string) {
		// TODO Auto-generated method stub
		
	}

	public List<Employee> query(String string, PreparedStatementSetter statementSetter, EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(String string, PreparedStatementSetter statementSetter) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void batchUpdate(String string, List<Object[]> parameters, Object insertSQLType) {
		// TODO Auto-generated method stub
		
	}

	public Integer queryForObject(String query, RowMapper rowMapper, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> query(String query, RowMapper rowMapper, Object[] array) {
		// TODO Auto-generated method stub
		return null;
	}

}
