package com.brian.casouso.service;


import com.brian.casouso.entity.Compesation;
import com.brian.casouso.entity.CompesationFindForm;

public interface CompesationService {

	public Iterable<Compesation> getCompesationsByDate(Long employeeId, CompesationFindForm cff);

	public Compesation createCompesation(Compesation compesation) throws Exception;
	
	public Compesation getCompesationById(Long id) throws Exception;
	
	public Compesation updateCompesation(Compesation compesation) throws Exception;
}
