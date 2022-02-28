package com.springboot.test.taxation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.taxation.model.Taxation;
import com.springboot.test.taxation.services.GeneralTaxationService;

@RestController
public class GeneralController {

	@Autowired
	private GeneralTaxationService generalService;

	@PostMapping("/general-taxation/{input}/{odd}/{option}")
	public Taxation getReturnAmount(@PathVariable int input, @PathVariable double odd, @PathVariable String option) {
		return generalService.generalTaxation(input, odd, option);
	}

}
