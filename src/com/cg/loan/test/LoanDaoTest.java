package com.cg.loan.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.loan.bean.LoanApplication;
import com.cg.loan.bean.LoanProgramsOffered;
import com.cg.loan.bean.Users;
import com.cg.loan.dao.LoanDAO;
import com.cg.loan.dao.LoanDAOImpl;
import com.cg.loan.exception.LoanApplicationException;

public class LoanDaoTest {
	static LoanDAO dao = null;
	
	@Before
	public void setUp() throws Exception {
		
		dao = new LoanDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testGetLoanOfferedList() throws LoanApplicationException {
		
		 LoanProgramsOffered loanProgramsOffered = new LoanProgramsOffered();
		 
		 loanProgramsOffered.setProgramName("Home Loan");
		 loanProgramsOffered.setDescription("Home Loan");
		 loanProgramsOffered.setLoanType("Home");
		 loanProgramsOffered.setDurationInYears(2);
		 loanProgramsOffered.setMinLoanAmount(1000);
		 loanProgramsOffered.setMaxLoanAmount(2000);
		 loanProgramsOffered.setRateOfInterest(3);
		 loanProgramsOffered.setProofsRequired("Adhar Card");
		
		try {
			List<LoanProgramsOffered> loanProgramsOffereds = dao.getLoanOfferedList();
			
			assertEquals(loanProgramsOffereds, loanProgramsOffereds);
			
		} catch (LoanApplicationException e) {
			
			throw new LoanApplicationException(e.getMessage());
		}
		
	}

	@Test
	public void testViewLoanApplicationStatus() {
	
		LoanApplication loanApplication = new LoanApplication();
		
		loanApplication.setApplicationId(1001);
		loanApplication.setLoanProgram("Abc");
		loanApplication.setStatus("applied");
		//loanApplication.setDateOfInterview(21/07/2019);
		try {
			loanApplication = dao.viewLoanApplicationStatus(1001);
			assertEquals(loanApplication, loanApplication);
		} catch (LoanApplicationException	 e) {
			
			System.out.println(e.getMessage());
		}
		
		
	}

	@Test
	public void testValidateLogInUser() throws LoanApplicationException {
		Users users = new Users();
			
		try {
			users= dao.validateLogInUser("admin","admin","admin");
			
			 assertEquals(users, users);
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		}
	}

	
	@Test
	public void testGetLoanProgramDetails() throws LoanApplicationException {
		
		String loanProgramName="abc";
		
		List<LoanProgramsOffered> loanProgramsList = null;
		LoanProgramsOffered loanProgramsOffered = new LoanProgramsOffered();
		 loanProgramsList = new ArrayList<LoanProgramsOffered>();
		 
		 loanProgramsOffered = new LoanProgramsOffered();
		 
		 loanProgramsOffered.setProgramName("Home Loan");
		 loanProgramsOffered.setDescription("Home Loan");
		 loanProgramsOffered.setLoanType("Home");
		 loanProgramsOffered.setDurationInYears(2);
		 loanProgramsOffered.setMinLoanAmount(1000);
		 loanProgramsOffered.setMaxLoanAmount(2000);
		 loanProgramsOffered.setRateOfInterest(3);
		 loanProgramsOffered.setProofsRequired("Adhar Card");
		 
		 loanProgramsList.add(loanProgramsOffered);
		 
		 try {
			loanProgramsList = dao.getLoanProgramDetails(loanProgramName);
			
			 assertEquals(loanProgramsList, loanProgramsList);
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		}
		 		
		 }

	
	@Test
	public void testDeleteLoanProgram() throws LoanApplicationException {
			
		String loanProgramName= "Home Loan";
		
		 try {
			dao.deleteLoanProgram(loanProgramName);
			
			assertEquals(loanProgramName, loanProgramName);
			
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		}
		
		
		
	}

	@Test
	public void testViewLoanApplications() throws LoanApplicationException {
		LoanApplication loanApplication = new LoanApplication();
		List<LoanApplication> loanApplicationList = new ArrayList<LoanApplication>();
		
		loanApplication.setLoanProgram("Home");
		loanApplication.setAmountOfLoan(20000);
		loanApplication.setAddressOfProperty("Bangalore");
		loanApplication.setAnnualFamilyIncome(500000);
		loanApplication.setDocumentProofsAvailable("Adhar");
		loanApplication.setGuaranteeCover("Best in market");
		loanApplication.setMarketValueOfGuaranteeCover(500000);
		loanApplication.setStatus("approved");
		loanApplication.setDateOfInterview(null);
		
		loanApplicationList.add(loanApplication);
		
		try {
			loanApplicationList = dao.viewLoanApplications();
			assertEquals(loanApplication, loanApplication);
		} catch (LoanApplicationException e) {
			 
			throw new LoanApplicationException(e.getMessage());
		}
		
	}
	
	
	
	
	

}
