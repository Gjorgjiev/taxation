package com.springboot.test.taxation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Outgoing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "possible_return_amount")
	private double possibleReturnAmount;

	@Column(name = "possible_return_amount_before_tax")
	private double possibleReturnAmountBefTax;

	@Column(name = "possible_return_amount_after_tax")
	private double possibleReturnAmountAfterTax;

	@Column(name = "tax_rate")
	private double taxRate;

	@Column(name = "tax_amount")
	private double taxAmount;

}
