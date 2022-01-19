package com.brian.casouso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.casouso.entity.Compesation;
import com.brian.casouso.entity.CompesationFindForm;
import com.brian.casouso.repository.CompesationRepository;

@Service
public class CompesationServiceImpl implements CompesationService {
	
	@Autowired
	CompesationRepository repository;

	@Override
	public Iterable<Compesation> getCompesationsByDate(Long employeeId, CompesationFindForm cff) {
		return repository.findByIdAndDate(employeeId, cff.getDesde(), cff.getHasta());
	}

	@Override
	public Compesation createCompesation(Compesation compesation) throws Exception {
		compesation = repository.save(compesation);
		return compesation;
	}

	Compesation com;

	@Override
	public Compesation getCompesationById(Long id) throws Exception {
		Optional<Compesation> compesationOptional = repository.findById(id);
		if (compesationOptional.isPresent())
			com = compesationOptional.get();
		return com;
	}

	@Override
	public Compesation updateCompesation(Compesation fromcompesation) throws Exception {
		Compesation toEmployee = getCompesationById(fromcompesation.getId());
		mapCompesation(fromcompesation, toEmployee);
		return repository.save(toEmployee);

	}

	/*
	 * Mapeando todo
	 */
	protected void mapCompesation(Compesation from, Compesation to) {
		to.setId(from.getId());
		to.setType(from.getType());
		to.setAmount(from.getAmount());
		to.setDescription(from.getDescription());
		to.setDate(from.getDate());
		to.setEmployee_id(from.getEmployee_id());

	}

	
}
