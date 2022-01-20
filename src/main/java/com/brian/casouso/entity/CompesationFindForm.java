package com.brian.casouso.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CompesationFindForm {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate desde;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hasta;

	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}

	public LocalDate getHasta() {
		return hasta;
	}

	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;

	}

}
