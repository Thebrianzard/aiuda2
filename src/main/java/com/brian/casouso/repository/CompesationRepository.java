package com.brian.casouso.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.casouso.entity.Compesation;

@Repository
public interface CompesationRepository extends CrudRepository<Compesation, Long>{

	public Optional<Compesation> findBytype(String type);

}
