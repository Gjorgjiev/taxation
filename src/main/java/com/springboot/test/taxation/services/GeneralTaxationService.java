package com.springboot.test.taxation.services;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.test.taxation.model.Incoming;
import com.springboot.test.taxation.model.Outgoing;
import com.springboot.test.taxation.model.Taxation;
import com.springboot.test.taxation.repository.IncomingRepository;
import com.springboot.test.taxation.repository.OutgoingRepository;
import com.springboot.test.taxation.repository.TaxationRepository;

@Service
public class GeneralTaxationService {

	@Autowired
	private IncomingRepository incomingRepository;

	@Autowired
	private OutgoingRepository outgoingRepository;

	@Autowired
	private TaxationRepository taxationRepository;

	private static final double TAX_RATE = 0.1;
	private static final int AMOUNT = 2;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

	public Taxation generalTaxation(int input, double odd, String option) {
		Outgoing outgoing = new Outgoing();
		Incoming incoming = new Incoming();
		if(option.equals("rate")) {
			getIncoming(input, odd, incoming);
			generalRateTaxation(input, odd, outgoing);
		} else if(option.equals("amount")) {
			getIncoming(input, odd, incoming);
			generalAmountTaxation(input, odd, outgoing);
		}
		return getTaxation(incoming, outgoing);
	}

	private Taxation getTaxation(Incoming incoming, Outgoing outgoing) {
		Taxation taxation = new Taxation();
		taxation.setOutgoing(outgoing);
		taxation.setIncoming(incoming);
		taxationRepository.save(taxation);
		return taxation;
	}

	private Incoming getIncoming(int input, double odd, Incoming incoming) {
		incoming.setPlayedAmount(input);
		incoming.setOdd(odd);
		return incomingRepository.save(incoming);
	}

	private Outgoing generalAmountTaxation(int input, double odd, Outgoing outgoing) {
		double returnAmount = input * odd;
		outgoing.setPossibleReturnAmount(returnAmount);
		outgoing.setTaxAmount(AMOUNT);
		double returnAmountAfterTax = returnAmount - AMOUNT;
		double taxRate = ((returnAmount - returnAmountAfterTax) / returnAmount) * 100;
		String tax = DECIMAL_FORMAT.format(taxRate);
		double finalTaxRate = Double.parseDouble(tax);
		outgoing.setTaxRate(finalTaxRate);
		double returnPossibleBeforeTax = returnAmount / (1 + taxRate);
		String beforeTax = DECIMAL_FORMAT.format(returnPossibleBeforeTax);
		double possibleReturnBeforeTax = Double.parseDouble(beforeTax);
		outgoing.setPossibleReturnAmountBefTax(possibleReturnBeforeTax);
		outgoing.setPossibleReturnAmountAfterTax(returnAmountAfterTax);
		return outgoingRepository.save(outgoing);
	}

	private Outgoing generalRateTaxation(int input, double odd, Outgoing outgoing) {
		double returnAmount = input * odd;
		outgoing.setPossibleReturnAmount(returnAmount);
		double taxAmount = returnAmount * TAX_RATE;
		outgoing.setTaxAmount(taxAmount);
		double returnAmountAfterTax = returnAmount - taxAmount;
		outgoing.setPossibleReturnAmountAfterTax(returnAmountAfterTax);
		double returnAmountBeforeTax = returnAmount / (1 + TAX_RATE);
		String beforeTax = DECIMAL_FORMAT.format(returnAmountBeforeTax);
		double possibleReturnBeforeTax = Double.parseDouble(beforeTax);
		outgoing.setPossibleReturnAmountBefTax(possibleReturnBeforeTax);
		outgoing.setTaxRate(TAX_RATE);
		return outgoingRepository.save(outgoing);
	}

}
