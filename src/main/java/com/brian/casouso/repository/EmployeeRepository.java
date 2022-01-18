package com.brian.casouso.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.casouso.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	
	public Optional<Employee> findByfirstName(String firstname);
	
	@Query("SELECT e FROM Employee e "
			+ "WHERE (:firstName = null or e.firstName like %:firstName%)"
			+ "AND (:lastName = null or e.lastName like %:lastName%)"
			+ "AND (:position = null or e.position like %:position%)")
	public List<Employee> findByFilters(String firstName, String lastName, String position);
	
}
