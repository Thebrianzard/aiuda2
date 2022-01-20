package com.brian.casouso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.casouso.entity.Compesation;

import com.brian.casouso.entity.CompesationFindForm;
import com.brian.casouso.entity.Employee;
import com.brian.casouso.entity.MonthsAmount;
import com.brian.casouso.repository.CompesationRepository;
import com.brian.casouso.repository.EmployeeRepository;

@Service
public class CompesationServiceImpl implements CompesationService {

	@Autowired
	CompesationRepository repository;
	
	@Autowired
	EmployeeRepository emprepository;

	@Override
	public Iterable<MonthsAmount> getCompesationsByMonth(Long employeeId, CompesationFindForm cff) {
		List<Compesation> findByIdAndDate = repository.findByIdAndDate(employeeId, cff.getDesde(), cff.getHasta());
		List<MonthsAmount> months = new ArrayList<>();
		
		Boolean isMoreThanOneYear = (cff.getHasta().getYear() - cff.getDesde().getYear()) >= 1;
		
		
		String anterior = "";
		for (Compesation compesation : findByIdAndDate) {
			String monthAndYear;
			if (isMoreThanOneYear) {
				monthAndYear = compesation.getDate().getMonth().toString() + " - " + compesation.getDate().getYear();
			} else {
				monthAndYear = compesation.getDate().getMonth().toString();
			}
			
			if (months.size() == 0) {
				months.add(new MonthsAmount(monthAndYear, compesation.getAmount(), compesation.getDate().getMonth(), compesation.getDate().getYear()));
				anterior = monthAndYear;
			} else {
				
				if (monthAndYear.equals(anterior)) {
					months.get(months.size() - 1).addAmount(compesation.getAmount());
				} else {
					months.add(new MonthsAmount(monthAndYear, compesation.getAmount(), compesation.getDate().getMonth(), compesation.getDate().getYear()));
					anterior = monthAndYear;
				}
				
			}
		}
		
		return months;
	}
	
	@Override
	public Iterable<Compesation> getCompesationsByIdAndDate(Long employeeId, CompesationFindForm cff) {
		return repository.findByIdAndDate(employeeId, cff.getDesde(), cff.getHasta());
	}

	@Override
	public Compesation createCompesation(Compesation compesation) throws Exception {
		compesation = repository.save(compesation);
		return compesation;
	}

	Compesation com;
	Employee emp;

	@Override
	public Compesation getCompesationById(Long id) throws Exception {
		Optional<Compesation> compesationOptional = repository.findById(id);
		if (compesationOptional.isPresent())
			com = compesationOptional.get();
		return com;
	}
	/*Long empId;
	@Override
	public Long getCompesationByEmployeeId(Long id) throws Exception {
		Optional<Employee> employeeOptional = emprepository.findById(id);
		if (employeeOptional.isPresent())
			emp = employeeOptional.get();
			empId = emp.getId();
		return empId;
	}
*/

	@Override
	public Compesation updateCompesation(Compesation fromcompesation) throws Exception {
		Compesation toEmployee = getCompesationById(fromcompesation.getId());
		mapCompesation(fromcompesation, toEmployee);
		return repository.save(toEmployee);

	}

	/*@Override
	public List<Compesation> getCompesationByFilter(LocalDate desde,LocalDate hasta) {
		
		List<Compesation> compesations = repository.findByFilters(desde,hasta);
		return compesations;
	}*/

	/*
	 * Mapeando todo
	 */
	
	protected void mapCompesation2(Employee from, Compesation to) {
		to.setEmployee_id(from.getId());
		

	}
	protected void mapCompesation(Compesation from, Compesation to) {
		to.setId(from.getId());
		to.setType(from.getType().toString());
		to.setAmount(from.getAmount());
		to.setDescription(from.getDescription());
		to.setDate(from.getDate());
		to.setEmployee_id(from.getEmployee_id());

	}


}
