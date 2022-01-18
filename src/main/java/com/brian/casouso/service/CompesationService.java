package com.brian.casouso.service;


import com.brian.casouso.entity.Compesation;

public interface CompesationService {

	public Iterable<Compesation> getAllCompesations();

	public Compesation createCompesation(Compesation compesation) throws Exception;
	
	public Compesation getCompesationById(Long id) throws Exception;
	
	public Compesation updateCompesation(Compesation compesation) throws Exception;
}
