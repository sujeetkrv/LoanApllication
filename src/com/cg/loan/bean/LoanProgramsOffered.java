package com.cg.loan.bean;

import java.io.Serializable;

public class LoanProgramsOffered implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private String programName;
	private String description;
	private String loanType;
	private double durationInYears;
	private double minLoanAmount;
	private double maxLoanAmount;
	private double rateOfInterest;
	private String proofsRequired;
	
	public LoanProgramsOffered() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanProgramsOffered(String programName, String description, String loanType, double durationInYears,
			double minLoanAmount, double maxLoanAmount, double rateOfInterest, String proofsRequired) {
		super();
		this.programName = programName;
		this.description = description;
		this.loanType = loanType;
		this.durationInYears = durationInYears;
		this.minLoanAmount = minLoanAmount;
		this.maxLoanAmount = maxLoanAmount;
		this.rateOfInterest = rateOfInterest;
		this.proofsRequired = proofsRequired;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getDurationInYears() {
		return durationInYears;
	}
	public void setDurationInYears(double durationInYears) {
		this.durationInYears = durationInYears;
	}
	public double getMinLoanAmount() {
		return minLoanAmount;
	}
	public void setMinLoanAmount(double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}
	public double getMaxLoanAmount() {
		return maxLoanAmount;
	}
	public void setMaxLoanAmount(double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public String getProofsRequired() {
		return proofsRequired;
	}
	public void setProofsRequired(String proofsRequired) {
		this.proofsRequired = proofsRequired;
	}
	@Override
	public String toString() {
		return "LoanProgramsOffered [programName=" + programName + ", description=" + description + ", loanType="
				+ loanType + ", durationInYears=" + durationInYears + ", minLoanAmount=" + minLoanAmount
				+ ", maxLoanAmount=" + maxLoanAmount + ", rateOfInterest=" + rateOfInterest + ", proofsRequired="
				+ proofsRequired + "]";
	}

	
	
}
