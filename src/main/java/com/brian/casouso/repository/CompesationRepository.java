package com.brian.casouso.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.casouso.entity.Compesation;
import com.brian.casouso.entity.Employee;

@Repository
public interface CompesationRepository extends CrudRepository<Compesation, Long>{

	public Optional<Compesation> findBytype(String type);
	
	@Query("SELECT c FROM Compesation c "
			+ "WHERE c.employee_id = :employeeId and c.date BETWEEN :desde AND :hasta")
	public List<Compesation> findByIdAndDate(Long employeeId, LocalDate desde, LocalDate hasta);


	//	public List<Compesation> findByFilters(LocalDate desde, LocalDate hasta);

	
}
