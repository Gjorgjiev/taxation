package com.springboot.test.taxation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "incoming")
public class Incoming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long traderId;

	@Column(name = "played_amount")
	private int playedAmount;

	private double odd;

}
