package com.cg.loan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.loan.bean.CustomerDetails;
import com.cg.loan.bean.LoanApplication;
import com.cg.loan.bean.LoanProgramsOffered;
import com.cg.loan.bean.Users;
import com.cg.loan.exception.LoanApplicationException;
import com.cg.loan.util.ConnectionManager;

public class LoanDAOImpl implements LoanDAO {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	List<LoanProgramsOffered> loanProgramsList = null;
	List<Users> usersList = null;
	List<CustomerDetails> loanProgram = null;
	List<LoanApplication> loanProgram1 = null;
	boolean status = false;
	LoanApplication loanApplication = null;
	CustomerDetails customerDetails = null;

	static Logger log = Logger.getLogger(LoanDAOImpl.class);

	
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
		  - Function Name	   	:	getLoanOfferedList()
	      - Input Parameters   	:	
		  - Return Type		  	:	List
		  - Throws				:  	LoanApplicationException
	      - Author				:	CAPGEMINI
		  - Creation Date		:	02/07/2019
		  - Description			:	Displaying the loan offers
	********************************************************************************************************/
	
	@Override
	public List<LoanProgramsOffered> getLoanOfferedList() throws LoanApplicationException {
		try {
			connection = ConnectionManager.getConnection();
			log.info("Connection Establish : " + connection);
		} catch (LoanApplicationException e) {

			throw new LoanApplicationException();
		}
		LoanProgramsOffered loanProgramsOffered = null;
		loanProgramsList = new ArrayList<LoanProgramsOffered>();
		try {
			statement = connection.createStatement();

			String sql = "SELECT * FROM loanprogramsoffered";
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				loanProgramsOffered = new LoanProgramsOffered();
				loanProgramsOffered.setProgramName(resultSet.getString("PROGRAMNAME"));
				loanProgramsOffered.setDescription(resultSet.getString("DESCRIPTION"));
				loanProgramsOffered.setLoanType(resultSet.getString("TYPE"));
				loanProgramsOffered.setDurationInYears(resultSet.getDouble("DURATIONINYEARS"));
				loanProgramsOffered.setMinLoanAmount(resultSet.getDouble("MINLOANAMOUNT"));
				loanProgramsOffered.setMaxLoanAmount(resultSet.getDouble("MAXLOANAMOUNT"));
				loanProgramsOffered.setRateOfInterest(resultSet.getDouble("RATEOFINTEREST"));
				loanProgramsOffered.setProofsRequired(resultSet.getString("PROOFS_REQUIRED"));

				loanProgramsList.add(loanProgramsOffered);
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());

		}

		return loanProgramsList;
	}
	
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
			- Function Name	   	:	applyForLoan(CustomerDetails customerDetails, LoanApplication loanApplication)
		    - Input Parameters  :	customerDetails,loanApplication
			- Return Type		:	int
			- Throws			:  	LoanApplicationException
		    - Author			:	CAPGEMINI
			- Creation Date		:	02/07/2019
			- Description		:	Customer applying for loan
	********************************************************************************************************/

	@Override
	public int applyForLoan(CustomerDetails customerDetails, LoanApplication loanApplication)
			throws LoanApplicationException {

		String loanStatus = "applied"; // Default loanStatus is "applied"
		int row1, row2;
		int applicationId = 0;

		try {
			connection = ConnectionManager.getConnection();
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		}

		String applicationIdQuery = "SELECT application_Id_Seq.NEXTVAL from dual";
		try {
			preparedStatement = connection.prepareStatement(applicationIdQuery);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			applicationId = resultSet.getInt(1);
			loanApplication.setApplicationId(applicationId);

			// Insert data into loanApplication table

			String sql1 = "INSERT INTO loanapplication VALUES(?,sysdate,?,?,?,?,?,?,?,?,null) ";
			preparedStatement = connection.prepareStatement(sql1);

			preparedStatement.setInt(1, loanApplication.getApplicationId());
			preparedStatement.setString(2, loanApplication.getLoanProgram());
			preparedStatement.setDouble(3, loanApplication.getAmountOfLoan());
			preparedStatement.setString(4, loanApplication.getAddressOfProperty());
			preparedStatement.setDouble(5, loanApplication.getAnnualFamilyIncome());
			preparedStatement.setString(6, loanApplication.getDocumentProofsAvailable());
			preparedStatement.setString(7, loanApplication.getGuaranteeCover());
			preparedStatement.setDouble(8, loanApplication.getMarketValueOfGuaranteeCover());
			preparedStatement.setString(9, loanStatus);

			row1 = preparedStatement.executeUpdate();

			// Insert data into customerDetails table

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateOfBirthUtil = simpleDateFormat.parse(customerDetails.getDateOfBirth());
			java.sql.Date dateOfBirthSql = new java.sql.Date(dateOfBirthUtil.getTime());

			String sql2 = "INSERT INTO customerdetails VALUES(?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setInt(1, loanApplication.getApplicationId());
			preparedStatement.setString(2, customerDetails.getApplicantName());
			preparedStatement.setDate(3, dateOfBirthSql);
			preparedStatement.setString(4, customerDetails.getMaritalStatus());
			preparedStatement.setLong(5, customerDetails.getPhoneNumber());
			preparedStatement.setLong(6, customerDetails.getMobileNumber());
			preparedStatement.setInt(7, customerDetails.getCountOfDependents());
			preparedStatement.setString(8, customerDetails.getEmailId());

			row2 = preparedStatement.executeUpdate();

			if (row1 > 0 && row2 > 0) {

				return applicationId;
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());
		}

		return applicationId;
	}

	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
			- Function Name	   	:	viewLoanApplicationStatus(int id)
			- Input Parameters  :	id
			- Return Type		:	LoanApplication
			- Throws			:  	LoanApplicationException
			- Author			:	CAPGEMINI
			- Creation Date		:	02/07/2019
			- Description		:	Customer can view status of their loan application based on id
	********************************************************************************************************/
	@Override
	public LoanApplication viewLoanApplicationStatus(int id) throws LoanApplicationException {
		LoanApplication loanApplication = new LoanApplication();

		try {
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			String sql = "SELECT * FROM LOANAPPLICATION WHERE APPLICATION_ID=" + id;
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				loanApplication.setApplicationId(resultSet.getInt("APPLICATION_ID"));
				loanApplication.setLoanProgram(resultSet.getString("LOAN_PROGRAM"));
				loanApplication.setStatus(resultSet.getString("STATUS"));
				loanApplication.setDateOfInterview(resultSet.getDate("DATEOFINTERVIEW"));
			}

		} catch (LoanApplicationException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());
		}
		return loanApplication;
	}

	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
			- Function Name	   	:	validateLogInUser(String userId, String password, String role)
			- Input Parameters  :	userId,password,role
			- Return Type		:	Users
			- Throws			:  	LoanApplicationException
			- Author			:	CAPGEMINI
			- Creation Date		:	02/07/2019
			- Description		:	Validation for user login
	********************************************************************************************************/
	@Override
	public Users validateLogInUser(String userId, String password, String role) throws LoanApplicationException {
		Users users = new Users();
		usersList = new ArrayList<Users>();

		try {
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();

			String sql = "SELECT * FROM users WHERE LOGIN_ID='" + userId + "'" + "and PASSWORD='" + password
					+ "' and ROLE='" + role + "'";
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				users.setLoginId(resultSet.getString("login_id"));
				users.setPassword(resultSet.getString("password"));
				users.setRole(resultSet.getString("role"));
			}
		} catch (LoanApplicationException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());

		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());

		}

		return users;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
		/*******************************************************************************************************
				- Function Name	   	:	addLoanProgram(LoanProgramsOffered loanProgramsOffered)
				- Input Parameters  :	loanProgramName
				- Return Type		:	boolean
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Add Loan Program
		********************************************************************************************************/
	
	@Override
	public boolean addLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException {

		int row = 0;

		try {
			connection = ConnectionManager.getConnection();

			// Insert data into LoanProgramsOffered table

			String sql1 = "INSERT INTO LOANPROGRAMSOFFERED VALUES(?,?,?,?,?,?,?,?) ";
			preparedStatement = connection.prepareStatement(sql1);

			preparedStatement.setString(1, loanProgramsOffered.getProgramName());
			preparedStatement.setString(2, loanProgramsOffered.getDescription());
			preparedStatement.setString(3, loanProgramsOffered.getLoanType());
			preparedStatement.setDouble(4, loanProgramsOffered.getDurationInYears());
			preparedStatement.setDouble(5, loanProgramsOffered.getMinLoanAmount());
			preparedStatement.setDouble(6, loanProgramsOffered.getMaxLoanAmount());
			preparedStatement.setDouble(7, loanProgramsOffered.getRateOfInterest());
			preparedStatement.setString(8, loanProgramsOffered.getProofsRequired());

			row = preparedStatement.executeUpdate();
			if (row > 0) {
				status = true;
				return status;
			}
		} catch (LoanApplicationException e) {
			log.error(e.getMessage());
			throw new LoanApplicationException(e.getMessage());
		} catch (SQLException e) {
			throw new LoanApplicationException(e.getMessage());
		}
		return status;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
		/*******************************************************************************************************
				- Function Name	   	:	getLoanProgramDetails(String loanProgramName)
				- Input Parameters  :	loanProgramName
				- Return Type		:	List
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Display loan program details based on loan program name 
		********************************************************************************************************/

	@Override
	public List<LoanProgramsOffered> getLoanProgramDetails(String loanProgramName) throws LoanApplicationException {

		LoanProgramsOffered loanProgramsOffered = null;
		loanProgramsList = new ArrayList<LoanProgramsOffered>();

		try {
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			System.out.println(loanProgramName);
			String sql = "SELECT * FROM LOANPROGRAMSOFFERED WHERE PROGRAMNAME='" + loanProgramName + "'";
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				loanProgramsOffered = new LoanProgramsOffered();
				loanProgramsOffered.setProgramName(resultSet.getString("PROGRAMNAME"));
				loanProgramsOffered.setDescription(resultSet.getString("DESCRIPTION"));
				loanProgramsOffered.setLoanType(resultSet.getString("TYPE"));
				loanProgramsOffered.setDurationInYears(resultSet.getDouble("DURATIONINYEARS"));
				loanProgramsOffered.setMinLoanAmount(resultSet.getDouble("MINLOANAMOUNT"));
				loanProgramsOffered.setMaxLoanAmount(resultSet.getDouble("MAXLOANAMOUNT"));
				loanProgramsOffered.setRateOfInterest(resultSet.getDouble("RATEOFINTEREST"));
				loanProgramsOffered.setProofsRequired(resultSet.getString("PROOFS_REQUIRED"));
				loanProgramsList.add(loanProgramsOffered);
			}

		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		} catch (SQLException e) {

			throw new LoanApplicationException(e.getMessage());
		}

		return loanProgramsList;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
				- Function Name	   	:	updateLoanProgram(LoanProgramsOffered loanProgramsOffered)
				- Input Parameters  :	loanApplication
				- Return Type		:	boolean
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Updating the status of Loan application for applicant 
	********************************************************************************************************/
	@Override
	public boolean updateLoanProgram(LoanProgramsOffered loanProgramsOffered) throws LoanApplicationException {

		int row = 0;
		try {
			connection = ConnectionManager.getConnection();

			String sql = "UPDATE LOANPROGRAMSOFFERED SET DESCRIPTION=?,TYPE=?,DURATIONINYEARS=?,MINLOANAMOUNT=?,MAXLOANAMOUNT=?,RATEOFINTEREST=?,PROOFS_REQUIRED=? WHERE PROGRAMNAME=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, loanProgramsOffered.getDescription());
			preparedStatement.setString(2, loanProgramsOffered.getLoanType());
			preparedStatement.setDouble(3, loanProgramsOffered.getDurationInYears());
			preparedStatement.setDouble(4, loanProgramsOffered.getMinLoanAmount());
			preparedStatement.setDouble(5, loanProgramsOffered.getMaxLoanAmount());
			preparedStatement.setDouble(6, loanProgramsOffered.getRateOfInterest());
			preparedStatement.setString(7, loanProgramsOffered.getProofsRequired());
			preparedStatement.setString(8, loanProgramsOffered.getProgramName());

			row = preparedStatement.executeUpdate();
			if (row > 0) {
				status = true;
				return status;
			}
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());
		} catch (SQLException e) {
			throw new LoanApplicationException(e.getMessage());
		}
		return status;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
				- Function Name	   	:	deleteLoanProgram(String loanProgramName)
				- Input Parameters  :	loanProgramName
				- Return Type		:	boolean
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Deleting the old loan program 
	********************************************************************************************************/
	@Override
	public boolean deleteLoanProgram(String loanProgramName) throws LoanApplicationException {

		int row = 0;

		try {
			connection = ConnectionManager.getConnection();
			String sql = "DELETE FROM LOANPROGRAMSOFFERED WHERE PROGRAMNAME='" + loanProgramName + "'";
			preparedStatement = connection.prepareStatement(sql);
			row = preparedStatement.executeUpdate();
			if (row > 0) {
				status = true;
				return status;
			}
		} catch (SQLException e) {
			throw new LoanApplicationException(e.getMessage());
		}

		return status;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
				- Function Name	   	:	viewLoanApplications()
				- Input Parameters  :	
				- Return Type		:	List
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Viewing the loan application and status of the application	
	********************************************************************************************************/
	@Override
	public List<LoanApplication> viewLoanApplications() throws LoanApplicationException {

		String loanStatus[] = { "accepted", "approved", "rejected" };

		LoanApplication loanApplication = null;
		List<LoanApplication> loanApplicationList = new ArrayList<LoanApplication>();

		try {

			connection = ConnectionManager.getConnection();

			String sql = "SELECT * FROM LOANAPPLICATION WHERE STATUS='" + loanStatus[0] + "'" + "OR STATUS='"
					+ loanStatus[1] + "'" + "OR STATUS='" + loanStatus[2] + "'";

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				loanApplication = new LoanApplication();
				loanApplication.setApplicationId(resultSet.getInt("APPLICATION_ID"));

				java.sql.Date applicationDateSql = resultSet.getDate("APPLICATION_DATE");
				Date applicationDateUtil = new Date(applicationDateSql.getTime());
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				String applicationDateUtil1 = dateFormat.format(applicationDateUtil);
				loanApplication.setApplicationDate(applicationDateUtil1);

				loanApplication.setLoanProgram(resultSet.getString("LOAN_PROGRAM"));
				loanApplication.setAmountOfLoan(resultSet.getDouble("AMOUNTOFLOAN"));
				loanApplication.setAddressOfProperty(resultSet.getString("ADDRESSOFPROPERTY"));
				loanApplication.setAnnualFamilyIncome(resultSet.getDouble("ANNUALFAMILYINCOME"));
				loanApplication.setDocumentProofsAvailable(resultSet.getString("DOCUMENTPROOFSAVAILABLE"));
				loanApplication.setGuaranteeCover(resultSet.getString("GUARANTEECOVER"));
				loanApplication.setMarketValueOfGuaranteeCover(resultSet.getDouble("MARKETVALUEOFGUARANTEECOVER"));
				loanApplication.setStatus(resultSet.getString("STATUS"));
				loanApplication.setDateOfInterview(resultSet.getDate("DATEOFINTERVIEW"));
				loanApplicationList.add(loanApplication);
			}

		} catch (SQLException e) {
			throw new LoanApplicationException(e.getMessage());
		}

		return loanApplicationList;
	}
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
				- Function Name	   	:	viewCustomerDetails(String programName)
				- Input Parameters  :	programName
				- Return Type		:	List
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Viewing the details of the customer	
	********************************************************************************************************/	
	@Override
	public List<CustomerDetails> viewCustomerDetails(String programName) throws LoanApplicationException {
		loanProgram = new ArrayList<>();
		try {
			connection = ConnectionManager.getConnection();
			String sql = "select c.application_id,c.applicant_name,c.date_of_birth,c.marital_status,c.phone_number,c.mobile_number,c.countofdependents,c.email_id from customerdetails c join loanapplication l on(l.APPLICATION_ID=c.APPLICATION_ID) where l.Loan_program=?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, programName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customerDetails = new CustomerDetails();
				customerDetails.setApplicationId(resultSet.getInt(1));
				customerDetails.setApplicantName(resultSet.getString(2));
				customerDetails.setDateOfBirth(resultSet.getString(3));
				customerDetails.setMaritalStatus(resultSet.getString(4));
				customerDetails.setPhoneNumber(resultSet.getLong(5));
				customerDetails.setMobileNumber(resultSet.getLong(6));
				customerDetails.setCountOfDependents(resultSet.getInt(7));
				customerDetails.setEmailId(resultSet.getString(8));
				loanProgram.add(customerDetails);
			}
		} catch (LoanApplicationException e) {
			throw new LoanApplicationException(e.getMessage());

		} catch (SQLException e) {
			throw new LoanApplicationException(e.getMessage());
		}
		return loanProgram;

	}
	//------------------------ Loan Application Processing System ------------------------------------------
	/*******************************************************************************************************
				- Function Name	   	:	updateStatusOfApplicant(LoanApplication loanApplication)
				- Input Parameters  :	programName
				- Return Type		:	boolean
				- Throws			:  	LoanApplicationException
				- Author			:	CAPGEMINI
				- Creation Date		:	02/07/2019
				- Description		:	Update the status of applicant	
	********************************************************************************************************/	
	public boolean updateStatusOfApplicant(LoanApplication loanApplication) throws LoanApplicationException {

		java.sql.Date dateOfInterviewSql = new java.sql.Date(loanApplication.getDateOfInterview().getTime());
		try {
			connection = ConnectionManager.getConnection();
			String sql = "update loanapplication set status=?,dateofinterview=? where application_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loanApplication.getStatus());
			preparedStatement.setDate(2, dateOfInterviewSql);
			preparedStatement.setInt(3, loanApplication.getApplicationId());
			preparedStatement.executeUpdate();
		} catch (LoanApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return true;

	}

}
