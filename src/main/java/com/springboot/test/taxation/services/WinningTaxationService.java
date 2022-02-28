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
public class WinningTaxationService {

	@Autowired
	private IncomingRepository incomingRepository;

	@Autowired
	private OutgoingRepository outgoingRepository;

	@Autowired
	private TaxationRepository taxationRepository;

	private static final double TAX_RATE = 0.1;
	private static final int AMOUNT = 1;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

	public Taxation winningsTaxation(int input, double odd, String option) {
		Incoming incoming = new Incoming();
		Outgoing outgoing = new Outgoing();
		double returnAmount = input * odd;
		double winningsAmount = returnAmount - input;
		if(option.equals("rate")) {
			getIncoming(input, odd, incoming);
			winningsRateTaxation(outgoing, returnAmount, winningsAmount);
		} else if (option.equals("amount")) {
			getIncoming(input, odd, incoming);
			winningAmountTaxation(outgoing, returnAmount);
		}
		return getTaxation(incoming, outgoing);
	}

	private Taxation getTaxation(Incoming incoming, Outgoing outgoing) {
		Taxation taxation = new Taxation();
		taxation.setIncoming(incoming);
		taxation.setOutgoing(outgoing);
		taxationRepository.save(taxation);
		return taxation;
	}

	private Outgoing winningAmountTaxation(Outgoing outgoing, double returnAmount) {
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

	private Outgoing winningsRateTaxation(Outgoing outgoing, double returnAmount, double winningsAmount) {
		outgoing.setPossibleReturnAmount(winningsAmount);
		double taxAmount = winningsAmount * TAX_RATE;
		outgoing.setTaxAmount(taxAmount);
		double afterTax = returnAmount - taxAmount;
		outgoing.setPossibleReturnAmountAfterTax(afterTax);
		double beforeTax = returnAmount / (1 + TAX_RATE);
		String returnBeforeTax = DECIMAL_FORMAT.format(beforeTax);
		double possibleReturnBeforeTax = Double.parseDouble(returnBeforeTax);
		outgoing.setPossibleReturnAmountBefTax(possibleReturnBeforeTax);
		outgoing.setTaxRate(TAX_RATE);
		return outgoingRepository.save(outgoing);
	}

	private Incoming getIncoming(int input, double odd, Incoming incoming) {
		incoming.setPlayedAmount(input);
		incoming.setOdd(odd);
		return incomingRepository.save(incoming);
	}

}
