package com.cg.loan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.loan.bean.CustomerDetails;
import com.cg.loan.bean.LoanApplication;
import com.cg.loan.bean.LoanProgramsOffered;
import com.cg.loan.bean.Users;
import com.cg.loan.dao.LoanDAO;
import com.cg.loan.dao.LoanDAOImpl;
import com.cg.loan.exception.LoanApplicationException;

public class LoanServiceImpl implements LoanService {

	LoanDAO loanDAO = null;

	@Override
	public List<LoanProgramsOffered> getLoanOfferedList() throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();

		return loanDAO.getLoanOfferedList();
	}

	@Override
	public int applyForLoan(CustomerDetails customerDetails, LoanApplication loanApplication)
			throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.applyForLoan(customerDetails, loanApplication);
	}

	@Override
	public LoanApplication viewLoanApplicationStatus(int id) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.viewLoanApplicationStatus(id);
	}

	@Override
	public Users validateLogInUser(String userId, String password, String role) throws LoanApplicationException {
		 
		loanDAO = new LoanDAOImpl();
		return loanDAO.validateLogInUser(userId, password, role);
	}

	@Override
	public boolean addLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.addLoanProgram(loanProgramsOffered);
	}

	@Override
	public List<LoanProgramsOffered> getLoanProgramDetails(String loanProgramName) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.getLoanProgramDetails(loanProgramName);
	}

	@Override
	public boolean updateLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.updateLoanProgram(loanProgramsOffered);
	}

	@Override
	public boolean deleteLoanProgram(String loanProgramName) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.deleteLoanProgram(loanProgramName);
	}

	@Override
	public List<LoanApplication> viewLoanApplications() throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.viewLoanApplications();
	}

	@Override
	public List<CustomerDetails> viewCustomerDetails(String programName) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.viewCustomerDetails(programName);
	}

	@Override
	public boolean updateStatusOfApplicant(LoanApplication loanApplication) throws LoanApplicationException {
		loanDAO = new LoanDAOImpl();
		return loanDAO.updateStatusOfApplicant(loanApplication);
	}

	public boolean isvalidDetails(CustomerDetails customerDetails, LoanApplication loanApplication)
			throws LoanApplicationException {
		List<String> validationErrors = new ArrayList<String>();
		if (!(isValidLoanApplicantName(customerDetails.getApplicantName()))) {
			validationErrors.add("The Applicant's Name Should Be In Alphabets and Have Atleast 3 Characters");
		}
		if (!(isValidMobileNumber(customerDetails.getMobileNumber()))) {

			validationErrors.add("Please Enter A Valid 10 digit Mobile Number");
		}
		if (!(isValidPhoneNumber(customerDetails.getPhoneNumber()))) {

			validationErrors.add("Phone Number Should Be In Digits Only");
		}
		if (!(isValidCountOfDependents(customerDetails.getCountOfDependents()))) {

			validationErrors.add("Please Enter A Valid 10 digit Mobile Number");
		}
		if (!(isValidEmailId(customerDetails.getEmailId()))) {

			validationErrors.add("Please Enter A Valid Email Id");
		}
		if (!(isValidLoanProgram(loanApplication.getLoanProgram()))) {

			validationErrors.add("Please Enter The Loan Program in 5 Letters");
		}
		if (!(isValidAmountOfLoan(loanApplication.getAmountOfLoan()))) {

			validationErrors.add("The Loan Amount Should In Numbers Only");
		}
		if (!(isValidAnnualFamilyIncome(loanApplication.getAnnualFamilyIncome()))) {

			validationErrors.add("The Annual Amount Should Be In Numbers Only");
		}
		if (!(isValidMarketValueOfGuarantee(loanApplication.getMarketValueOfGuaranteeCover()))) {

			validationErrors.add("The Market Value Amount Should Be In Numbers Only");
		}
		if (!(isValidDateOfBirth(customerDetails.getDateOfBirth()))) {

			validationErrors.add("Please Enter A Valid Date Of Birth");
		}
		if (!(validationErrors.isEmpty())) {
			throw new LoanApplicationException(validationErrors + "");
		}	
		return true;
	}

	private boolean isValidDateOfBirth(String dateOfBirth) {
		Pattern namePattern = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
		Matcher nameMatcher = namePattern.matcher(dateOfBirth);
		return nameMatcher.matches();
	}

	private boolean isValidMarketValueOfGuarantee(double marketValueOfGuaranteeCover) {
		if (marketValueOfGuaranteeCover > 0) {
			return true;
		}
		return false;
	}

	private boolean isValidAnnualFamilyIncome(double annualFamilyIncome) {
		if (annualFamilyIncome > 0) {
			return true;
		}
		return false;
	}

	private boolean isValidAmountOfLoan(double amountOfLoan) {
		if (amountOfLoan > 0) {
			return true;
		}
		return false;
	}

	private boolean isValidLoanProgram(String loanProgram) {
		Pattern namePattern = Pattern.compile("^[A-Za-z]{1,5}$");
		Matcher nameMatcher = namePattern.matcher(loanProgram);
		return nameMatcher.matches();
	}

	private boolean isValidPhoneNumber(long phoneNumber) {
		if (phoneNumber >= 0) {
			return true;
		}
		return false;
	}

	private boolean isValidCountOfDependents(int countOfDependents) {
		if (countOfDependents > 0) {
			return true;
		}
		return false;
	}

	private boolean isValidMobileNumber(long mobileNumber) {
		Pattern namePattern = Pattern.compile("^[6-9]{1}[0-9]{9}$");
		Matcher nameMatcher = namePattern.matcher(String.valueOf(mobileNumber));
		return nameMatcher.matches();
	}

	public boolean isValidLoanApplicantName(String name) {
		Pattern namePattern = Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher = namePattern.matcher(name);
		return nameMatcher.matches();
	}

	public boolean isValidEmailId(String mailId) {

		Pattern emailPattern = Pattern.compile("[A-Za-z0-9]{1,20}[@][A-Za-z]{3,15}[.][A-Za-z]{1,15}");
		Matcher nameMatcher = emailPattern.matcher(mailId);
		return nameMatcher.matches();

	}


}