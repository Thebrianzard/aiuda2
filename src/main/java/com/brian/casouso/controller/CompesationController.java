package com.brian.casouso.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.brian.casouso.entity.Compesation;
import com.brian.casouso.repository.EmployeeRepository;
import com.brian.casouso.service.CompesationService;

@Controller
public class CompesationController {

	@Autowired
	CompesationService compesationService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	

	@GetMapping("/compesationFind")
	public String getCompesationFind(Model model) {
		model.addAttribute("compesationList", compesationService.getAllCompesations());
		return "compesation-find";
	}
/*
	@GetMapping("/findCompesation")
	public String findCompesation(@RequestParam(value = "desde") LocalDate desde,
			@RequestParam(value = "hasta") LocalDate hasta,
			 BindingResult result, Model model) {

	//	model.addAttribute("compesationList", compesationService.getCompesationByFilter(compesation));
		model.addAttribute("compesationList", compesationService.getCompesationByFilter(desde,hasta));
		//model.addAttribute("compesation", compesation);
		return "compesation-find";
	}*/

	/*
	 * Este es por si no sirve el del employee id
	 * @GetMapping("/compesationForm")
	public String getCompesationForm(Model model) {
		model.addAttribute("compesationForm", new Compesation());

		return "compesation-form";
	}	*/
	
	@GetMapping("/compesationForm/{employee_id}")
	public String getCompesationForm(Model model, @PathVariable(name="employee_id") Long employee_id) throws Exception{
		model.addAttribute("compesationForm", new Compesation());
		model.addAttribute("employee_id",employee_id);
		System.out.println(employee_id);

		return "compesation-form";
	}

	@PostMapping("/compesationForm/{employee_id}")
	public String createCompesation(@Valid @ModelAttribute("compesationForm") Compesation compesation,
			BindingResult result, ModelMap model, @PathVariable(name="employee_id") Long employee_id) {
		if (result.hasErrors()) {
			model.addAttribute("compesationForm", compesation);
		} else {
			try {
				model.addAttribute("employee_id",employee_id);
				compesation.setEmployee_id(employee_id);
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
	public String postEditCompesationForm(@Valid @ModelAttribute("employeeForm") Compesation compesation,
			BindingResult result, ModelMap model) {
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
