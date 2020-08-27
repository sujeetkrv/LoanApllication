package com.cg.loan.dao;

import java.util.List;

import com.cg.loan.bean.CustomerDetails;
import com.cg.loan.bean.LoanApplication;
import com.cg.loan.bean.LoanProgramsOffered;
import com.cg.loan.bean.Users;
import com.cg.loan.exception.LoanApplicationException;

public interface LoanDAO {

	List<LoanProgramsOffered> getLoanOfferedList() throws LoanApplicationException;

	int applyForLoan(CustomerDetails customerDetails, LoanApplication loanApplication)
			throws LoanApplicationException;
	
	LoanApplication viewLoanApplicationStatus(int id) throws LoanApplicationException;
	
	Users validateLogInUser(String userId,String password,String role) throws LoanApplicationException;
	boolean addLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException;
	
	List<LoanProgramsOffered> getLoanProgramDetails(String loanProgramName) throws LoanApplicationException;

	boolean updateLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException;
	
  
	boolean deleteLoanProgram(String loanProgramName) throws LoanApplicationException;
 	
	List<LoanApplication> viewLoanApplications() throws LoanApplicationException;
	
	List<CustomerDetails> viewCustomerDetails(String programName) throws LoanApplicationException;
	
	public boolean updateStatusOfApplicant(LoanApplication loanApplication) throws LoanApplicationException;
	
}
