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

import com.brian.casouso.entity.Employee;
import com.brian.casouso.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/employeeFind")
	public String getEmployeeFind(Model model) {
		model.addAttribute("employeeList", employeeService.getAllEmployees());
		model.addAttribute("employee", new Employee());
		return "employee-find";
	}
	
	@GetMapping("/findEmployee")
	public String findEmployee(@ModelAttribute("employeeForm") Employee employee, BindingResult result, Model model) {
		model.addAttribute("employeeList", employeeService.getEmployeesByFilter(employee));
		model.addAttribute("employee", employee);
		return "employee-find";
	}

	@GetMapping("/employeeForm")
	public String getEmployeeForm(Model model) {
		model.addAttribute("employeeForm", new Employee());

		return "employee-form";
	}

	@PostMapping("/employeeForm")
	public String createEmployee(@Valid @ModelAttribute("employeeForm") Employee employee, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("employeeForm", employee);
		} else {
			try {
				employeeService.createEmployee(employee);
				model.addAttribute("employeeForm", new Employee());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("employeeForm", employee);

			}
		}
		// model.addAttribute("employeeList", employeeService.getAllEmployees());

		return "employee-form";
	}

	@GetMapping("/editEmployee/{id}")
	public String getEditEmployeeForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
		Employee employeeToEdit = employeeService.getEmployeeById(id);
		model.addAttribute("employeeForm", employeeToEdit);
		model.addAttribute("employeeList", employeeService.getAllEmployees());
		model.addAttribute("editMode", "true");
		return "employee-form";
	}

	@PostMapping("/editEmployee")
	public String postEditEmployeeForm(@Valid @ModelAttribute("employeeForm") Employee employee, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("employeeForm", employee);
			model.addAttribute("editMode", "true");

		} else {
			try {
				employeeService.updateEmployee(employee);
				model.addAttribute("employeeForm", new Employee());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("employeeForm", employee);
				model.addAttribute("editMode", "true");

			}
		}
		// model.addAttribute("employeeList", employeeService.getAllEmployees());

		return "employee-form";
	}
	
	@GetMapping("/employeeForm/cancel")
	public String cancelEditEmployee(ModelMap model) {
		return "redirect:/employeeForm";
	}
}
