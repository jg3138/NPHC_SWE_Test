package org.nphc.payroll.repository;

import org.nphc.payroll.dto.Employee;
import org.nphc.payroll.dto.EmployeeDto;
import org.nphc.payroll.dto.MessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JDBC Template - communication layer with database to application */
@Repository 
public class EmployeeJDBC {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeJDBC.class);
    @Autowired
    private static JDBCTemplate jdbcTemplate; 
    
    /* Create SQL Table */
    @PostConstruct
    public void init() {
        try {
            logger.info("Creating SQL table: employee if it does not exist with id, login, name, salary, start_date");
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS employee(id varchar(32) PRIMARY KEY, " +
                    "login VARCHAR(32) UNIQUE, name VARCHAR(128), salary decimal, start_date date);");
        } catch (DataAccessException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /* Find Employee by ID */
    public static List<Employee> findIds(List<Employee> employeeList) {
        if (employeeList.isEmpty()) {
            return new ArrayList<>();
        }
        List<Object> ids = new ArrayList<>();
        employeeList.forEach(employee -> ids.add(employee.getId()));
        String query = "SELECT id FROM employee WHERE id IN " + getInParameter(employeeList.size());
        RowMapper rowMapper = (rs, rowNum) -> rs.getString(1);
        return jdbcTemplate.query(query, rowMapper, ids.toArray());
    }
    
    /* To avoid employee id duplicate record exception */
    public static boolean isIdExists(Employee employee) {
        String query = "SELECT COUNT(*) FROM employee WHERE id = ?1";
        RowMapper rowMapper = (rs, rowNum) -> rs.getInt(1);
        Integer integer = jdbcTemplate.queryForObject(query, rowMapper, employee.getId());
        return integer != null && 0 < integer;
    }

    /* To avoid employee login duplicate record exception */
    public static boolean isLoginExits(org.nphc.payroll.data.Employee employee) {
        String query = "SELECT COUNT(*) FROM employee WHERE id != ?1 AND login = ?2";
        RowMapper rowMapper = (rs, rowNum) -> rs.getInt(1);
        Integer integer = jdbcTemplate.queryForObject(query, rowMapper, employee.getId());
        return integer != null && 0 < integer;
    }

    /* Search employee list with filter by salary range, order by id, login, salary, startDate */
    public List<Employee> search(Double Double, String orderBy, int limit, int offset, boolean isDesc) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, Object> parameterMap = new HashMap<>();
        int index = 0;
        if (Double != null) {
            index += 1;
            builder.append(" AND salary > ?").append(index);
            parameterMap.put(index, Double);
        }
        if (Double != null) {
            index += 1;
            builder.append(" AND salary < ?").append(index);
            parameterMap.put(index, Double);
        }
        if(orderBy == null) {
            orderBy = "id";
        } else if("startDate".equals(orderBy)) {
            orderBy = "start_date";
        }
        builder.append(" ORDER BY ").append(orderBy);
        if (isDesc) {
            builder.append(" DESCENDING ");
        }
        if (0 < limit) {
            builder.append(" LIMIT ").append(limit);
        }
        if (0 < offset) {
            builder.append(" OFFSET  ").append(offset);
        }
        String suffix = builder.toString();
        if (builder.toString().startsWith(" AND")) {
            suffix = suffix.replaceFirst(" AND", " WHERE ");
        }
        PreparedStatementSetter statementSetter = null;
		return jdbcTemplate.query("SELECT id, login, name, salary, start_date FROM employee " + suffix, statementSetter, new EmployeeDto());
    }

    /* Search employee list with filter by salary range, order by id, login, salary, startDate */
    public static Employee findById(String id) {
        PreparedStatement ps;
		PreparedStatementSetter statementSetter = ps.setString(1, id);
        List<Employee> list = jdbcTemplate.query("SELECT id, login, name, salary, start_date FROM employee WHERE id = ?1",
                statementSetter, new EmployeeDto());
        return list.isEmpty() ? null : list.get(0);
    }

    /* Delete employee by id */
    public int deleteById(String id) {
        PreparedStatement ps;
		PreparedStatementSetter statementSetter = ps.setString(1, id);
        return jdbcTemplate.update("DELETE FROM employee WHERE id = ?1", statementSetter);
    }


    /* Insert employees */
    public void insert(List<Employee> employeeList) {
        EmployeeDto dto = new EmployeeDto();
        List<Object[]> parameters = new ArrayList<>();
        employeeList.forEach(employee -> parameters.add(dto.getInsertParameters(employee)));
        jdbcTemplate.batchUpdate("INSERT INTO employee (id, login, name, salary, start_date) values(?,?,?,?,?)",
                parameters, dto.getInsertSQLType());
    }

    /* Update employees */
    public void update(List<org.nphc.payroll.data.Employee> list) {
        EmployeeDto dto = new EmployeeDto();
        List<Object[]> parameters = new ArrayList<>();
        list.forEach(employee -> parameters.add(dto.getUpdateObjects(employee)));
        jdbcTemplate.batchUpdate("UPDATE employee SET login = ?, name = ?, salary = ?, start_date = ? WHERE id  = ?",
                parameters, dto.getUpdateSQLType());
    }

    private static String getInParameter(int size) {
        StringBuilder builder = new StringBuilder();
        for (int idx = 1; idx <= size; idx++) {
            builder.append(",").append("?").append(idx);
        }
        builder.replace(0, 1, "(").append(")");
        return builder.toString();
    }

	public static boolean isIdExists(org.nphc.payroll.data.Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}
}
