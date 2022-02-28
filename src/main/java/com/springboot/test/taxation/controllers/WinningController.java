package com.springboot.test.taxation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.taxation.model.Taxation;
import com.springboot.test.taxation.services.WinningTaxationService;

@RestController
public class WinningController {
	
	@Autowired
	private WinningTaxationService winningService;
	
	@PostMapping("/winning-taxation/{input}/{odd}/{option}")
	public Taxation getReturnAmount(@PathVariable int input, @PathVariable double odd, @PathVariable String option) {
		return winningService.winningsTaxation(input, odd, option);
	}

}
