package com.cg.loan.bean;

import java.io.Serializable;
import java.util.Date;

public class LoanApplication implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int applicationId;
	private String applicationDate;
	private String loanProgram;
	private double amountOfLoan;
	private String addressOfProperty;
	private double annualFamilyIncome;
	private String documentProofsAvailable;
	private String guaranteeCover;
	private double marketValueOfGuaranteeCover;
	private String status;
	private Date dateOfInterview;
	
	public LoanApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanApplication(String applicationDate, String loanProgram, double amountOfLoan, String addressOfProperty,
			double annualFamilyIncome, String documentProofsAvailable, String guaranteeCover,
			double marketValueOfGuaranteeCover, String status, Date dateOfInterview) {
		super();
		this.applicationDate = applicationDate;
		this.loanProgram = loanProgram;
		this.amountOfLoan = amountOfLoan;
		this.addressOfProperty = addressOfProperty;
		this.annualFamilyIncome = annualFamilyIncome;
		this.documentProofsAvailable = documentProofsAvailable;
		this.guaranteeCover = guaranteeCover;
		this.marketValueOfGuaranteeCover = marketValueOfGuaranteeCover;
		this.status = status;
		this.dateOfInterview = dateOfInterview;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getLoanProgram() {
		return loanProgram;
	}

	public void setLoanProgram(String loanProgram) {
		this.loanProgram = loanProgram;
	}

	public double getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(double amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

	public String getAddressOfProperty() {
		return addressOfProperty;
	}

	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
	}

	public double getAnnualFamilyIncome() {
		return annualFamilyIncome;
	}

	public void setAnnualFamilyIncome(double annualFamilyIncome) {
		this.annualFamilyIncome = annualFamilyIncome;
	}

	public String getDocumentProofsAvailable() {
		return documentProofsAvailable;
	}

	public void setDocumentProofsAvailable(String documentProofsAvailable) {
		this.documentProofsAvailable = documentProofsAvailable;
	}

	public String getGuaranteeCover() {
		return guaranteeCover;
	}

	public void setGuaranteeCover(String guaranteeCover) {
		this.guaranteeCover = guaranteeCover;
	}

	public double getMarketValueOfGuaranteeCover() {
		return marketValueOfGuaranteeCover;
	}

	public void setMarketValueOfGuaranteeCover(double marketValueOfGuaranteeCover) {
		this.marketValueOfGuaranteeCover = marketValueOfGuaranteeCover;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(Date date) {
		this.dateOfInterview = date;
	}

	@Override
	public String toString() {
		return "LoanApplication [applicationId=" + applicationId + ", applicationDate=" + applicationDate
				+ ", loanProgram=" + loanProgram + ", amountOfLoan=" + amountOfLoan + ", addressOfProperty="
				+ addressOfProperty + ", annualFamilyIncome=" + annualFamilyIncome + ", documentProofsAvailable="
				+ documentProofsAvailable + ", guaranteeCover=" + guaranteeCover + ", marketValueOfGuaranteeCover="
				+ marketValueOfGuaranteeCover + ", status=" + status + ", dateOfInterview=" + dateOfInterview + "]";
	}
	
	
	
}
