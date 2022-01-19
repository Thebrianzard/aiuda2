package com.brian.casouso.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.casouso.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	@Query("SELECT e FROM Employee e "
				+ "WHERE e.firstName = :firstName AND e.midleName = :midleName AND e.lastName = :lastName AND e.birthDate = :birthDate")
	public Optional<Employee> verifyEmployee(String firstName, String midleName, String lastName, LocalDate birthDate);
	
	@Query("SELECT e FROM Employee e "
			+ "WHERE (:firstName = null or e.firstName like %:firstName%)"
			+ "AND (:lastName = null or e.lastName like %:lastName%)"
			+ "AND (:position = null or e.position like %:position%)")
	public List<Employee> findByFilters(String firstName, String lastName, String position);
	
}
