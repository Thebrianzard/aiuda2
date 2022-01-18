package com.brian.casouso.service;


import java.util.List;

import com.brian.casouso.entity.Employee;

public interface EmployeeService {
	
	public Iterable<Employee> getAllEmployees();

	public Employee createEmployee(Employee employee) throws Exception;
	
	public Employee getEmployeeById(Long id) throws Exception;
	
	public Employee updateEmployee(Employee employee) throws Exception;
		
	public List<Employee> getEmployeesByFilter(Employee employee);
}
