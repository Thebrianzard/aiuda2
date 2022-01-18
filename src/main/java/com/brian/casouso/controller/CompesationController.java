package com.brian.casouso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.brian.casouso.entity.Compesation;
import com.brian.casouso.entity.Employee;
import com.brian.casouso.repository.CompesationRepository;
import com.brian.casouso.service.CompesationService;

@Controller
public class CompesationController {
	
	@Autowired
	CompesationService compesationService;
	
	
	@GetMapping("/compesationFind")
	public String getCompesationFind(Model model) {
		model.addAttribute("compesationList", compesationService.getAllCompesations());
		return "compesation-find";
	}

	@GetMapping("/compesationForm")
	public String getCompesationForm(Model model) {
		model.addAttribute("compesationForm", new Compesation());

		return "compesation-form";
	}
	
	@PostMapping("/compesationForm")
	public String createCompesation(@Valid @ModelAttribute("compesationForm") Compesation compesation, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("compesationForm", compesation);
		} else {
			try {
				compesationService.createCompesation(compesation);
				model.addAttribute("compesationForm", new Compesation());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("compesationForm", compesation);

			}
		}
		// model.addAttribute("employeeList", employeeService.getAllEmployees());

		return "compesation-form";
	}

	@GetMapping("/editCompesation/{id}")
	public String getEditCompesationForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
		Compesation compesationToEdit = compesationService.getCompesationById(id);
		model.addAttribute("compesationForm", compesationToEdit);
		model.addAttribute("compesationList", compesationService.getAllCompesations());
		model.addAttribute("editMode", "true");
		return "compesation-form";
	}
	
	@PostMapping("/editCompesation")
	public String postEditCompesationForm(@Valid @ModelAttribute("employeeForm") Compesation compesation, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("compesationForm", compesation);
			model.addAttribute("editMode", "true");

		} else {
			try {
				compesationService.updateCompesation(compesation);
				model.addAttribute("compesationForm", new Compesation());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("compesationForm", compesation);
				model.addAttribute("editMode", "true");

			}
		}
		// model.addAttribute("employeeList", employeeService.getAllEmployees());

		return "compesation-form";
	}
	
	@GetMapping("/compesationForm/cancel")
	public String cancelEditEmployee(ModelMap model) {
		return "redirect:/compesationForm";
	}
}
