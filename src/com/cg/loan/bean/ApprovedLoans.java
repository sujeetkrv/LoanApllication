package com.cg.loan.bean;

import java.io.Serializable;

public class ApprovedLoans implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private String applicantName;
	private double amountOfLoanGranted;
	private double monthlyInstallment;
	private double yearsTimePeriod;
	private double downPayment;
	private double rateOfInterest;
	private double totalAmountPayable;
	
	public ApprovedLoans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApprovedLoans(String applicantName, double amountOfLoanGranted, double monthlyInstallment,
			double yearsTimePeriod, double downPayment, double rateOfInterest, double totalAmountPayable) {
		super();
		this.applicantName = applicantName;
		this.amountOfLoanGranted = amountOfLoanGranted;
		this.monthlyInstallment = monthlyInstallment;
		this.yearsTimePeriod = yearsTimePeriod;
		this.downPayment = downPayment;
		this.rateOfInterest = rateOfInterest;
		this.totalAmountPayable = totalAmountPayable;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public double getAmountOfLoanGranted() {
		return amountOfLoanGranted;
	}
	public void setAmountOfLoanGranted(double amountOfLoanGranted) {
		this.amountOfLoanGranted = amountOfLoanGranted;
	}
	public double getMonthlyInstallment() {
		return monthlyInstallment;
	}
	public void setMonthlyInstallment(double monthlyInstallment) {
		this.monthlyInstallment = monthlyInstallment;
	}
	public double getYearsTimePeriod() {
		return yearsTimePeriod;
	}
	public void setYearsTimePeriod(double yearsTimePeriod) {
		this.yearsTimePeriod = yearsTimePeriod;
	}
	public double getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public double getTotalAmountPayable() {
		return totalAmountPayable;
	}
	public void setTotalAmountPayable(double totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}
	@Override
	public String toString() {
		return "ApprovedLoans [applicantName=" + applicantName + ", amountOfLoanGranted=" + amountOfLoanGranted
				+ ", monthlyInstallment=" + monthlyInstallment + ", yearsTimePeriod=" + yearsTimePeriod
				+ ", downPayment=" + downPayment + ", rateOfInterest=" + rateOfInterest + ", totalAmountPayable="
				+ totalAmountPayable + "]";
	}
	
}
