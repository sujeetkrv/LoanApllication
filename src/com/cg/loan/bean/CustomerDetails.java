package com.cg.loan.bean;

import java.io.Serializable;

public class CustomerDetails implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private int applicationId;
	private String applicantName;
	private String dateOfBirth;
	private String maritalStatus;
	private long phoneNumber;
	private long mobileNumber;
	private int countOfDependents;
	private String emailId;
	
	public CustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CustomerDetails(int applicationId, String applicantName, String dateOfBirth, String maritalStatus,
			long phoneNumber, long mobileNumber, int countOfDependents, String emailId) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.dateOfBirth = dateOfBirth;
		this.maritalStatus = maritalStatus;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.countOfDependents = countOfDependents;
		this.emailId = emailId;
	}



	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getCountOfDependents() {
		return countOfDependents;
	}

	public void setCountOfDependents(int countOfDependents) {
		this.countOfDependents = countOfDependents;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "CustomerDetails [applicationId=" + applicationId + ", applicantName=" + applicantName + ", dateOfBirth="
				+ dateOfBirth + ", maritalStatus=" + maritalStatus + ", phoneNumber=" + phoneNumber + ", mobileNumber="
				+ mobileNumber + ", countOfDependents=" + countOfDependents + ", emailId=" + emailId + "]";
	}

	
	

}
