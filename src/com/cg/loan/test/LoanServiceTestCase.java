package com.cg.loan.test;

import static org.junit.Assert.*;

import org.junit.After;
 import org.junit.Before;
 import org.junit.Test;

import com.cg.loan.bean.CustomerDetails;
import com.cg.loan.bean.LoanApplication;
import com.cg.loan.service.LoanServiceImpl;

public class LoanServiceTestCase {

	static LoanServiceImpl service = null;

	@Before
	public void setUp() throws Exception {
		
		service = new LoanServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		service= null;
	}

	
	@Test
	public void testIsvalidDetails() {
		
		CustomerDetails customerDetails = new CustomerDetails();
		LoanApplication loanApplication= new LoanApplication();
		
		customerDetails.setApplicantName("Hemant");
		assertEquals("Hemant", "Hemant");
		
		customerDetails.setMobileNumber(1234567123);
		
		assertEquals(1234567123, 1234567123);
		
		customerDetails.setPhoneNumber(0764456221);
		assertEquals(0764456221, 0764456221);
		
		customerDetails.setEmailId("hemant@gmail.com");
		assertEquals("hemant@gmail.com", "hemant@gmail.com");
		
		loanApplication.setLoanProgram("Homeloan");
		assertEquals("Homeloan", "Homeloan");
		
		loanApplication.setAmountOfLoan(1000);
		assertEquals(1000, 1000);
		
		loanApplication.setAnnualFamilyIncome(500000);
		assertEquals(500000, 500000);
		
		loanApplication.setMarketValueOfGuaranteeCover(100000);
		assertEquals(100000, 100000);
	
		
		
	}

	

}
