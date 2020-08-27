package com.cg.loan.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.loan.bean.CustomerDetails;
import com.cg.loan.bean.LoanApplication;
import com.cg.loan.bean.LoanProgramsOffered;
import com.cg.loan.bean.Users;
import com.cg.loan.exception.LoanApplicationException;
import com.cg.loan.service.LoanService;
import com.cg.loan.service.LoanServiceImpl;

public class LoanMain {

	static Scanner scanner = new Scanner(System.in);
	static Scanner scanner1 = new Scanner(System.in);
	static LoanService loanService = null;
	static List<LoanProgramsOffered> loanProgramsList = null;
	static List<LoanApplication> loanApplicationList = null;
	static List<CustomerDetails> customerDetailsList = null;
	static CustomerDetails customerDetails = null;
	static LoanApplication loanApplication = null;
	static boolean status = false;
	static int applicationId = 0;

	static Logger log = Logger.getLogger(LoanMain.class);

	// main method starts here.
	public static void main(String[] args) {

		System.out.println("Please enter user type : ");
		System.out.println("1. Customer \n2. Loan Approval Department Member \n3. Admin");

		try {
			int userTypeChoice = scanner.nextInt();
			log.info("user's choice");

			// select user type
			switch (userTypeChoice) {
			case 1:
				System.out.println("*****Customer*****");
				System.out.println("\n");

				while (true) {
					System.out.println("\n");
					System.out.println("Please select your operation");
					System.out.println("1. View all loan programs");
					System.out.println("2. Apply for loan");
					System.out.println("3. View Application status");
					System.out.println("4. Exit");

					int customerChoice = scanner.nextInt();

					switch (customerChoice) {

					// Show Loan Programs List

					case 1:
						System.out.println("Loan programs are : ");
						System.out.println("\n");
						loanProgramsList = new ArrayList<LoanProgramsOffered>();
						loanProgramsList = loanProgramDetails();
						if (loanProgramsList != null) {
							System.out.println(
									"PROGRAMNAME \t DESCRIPTION \t TYPE \t DURATIONINYEARS \t MINLOANAMOUNT \t MAXLOANAMOUNT \t RATEOFINTEREST \t PROOFS_REQUIRED");
							for (LoanProgramsOffered loanProgramsOffered : loanProgramsList) {
								log.info("retrieving the programs");
								System.out.println(loanProgramsOffered.getProgramName() + " \t\t "
										+ loanProgramsOffered.getDescription() + " \t\t "
										+ loanProgramsOffered.getLoanType() + " \t\t "
										+ loanProgramsOffered.getDurationInYears() + " \t\t "
										+ loanProgramsOffered.getMinLoanAmount() + " \t\t "
										+ loanProgramsOffered.getMaxLoanAmount() + " \t\t "
										+ loanProgramsOffered.getRateOfInterest() + " \t\t "
										+ loanProgramsOffered.getProofsRequired());
							}
						} else
							System.out.println("No Loan Program found.");

						break;

					// Apply for loan program

					case 2:
						System.out.println("Please fill application form to apply for loan : ");
						System.out.println("\n");
						applicationId = applyLoan();

						if (applicationId != 0) {
							System.out.println("Loan applied successfully, Your application Id is : " + applicationId);
							log.info("Printing the status");
						} else {
							System.out.println("Failed to apply for loan");
							log.info("Printing the status");
						}

						break;

					// View Loan Application

					case 3:
						System.out.println("Please enter your Id : ");
						LoanApplication loanApplication = new LoanApplication();
						loanApplication.setApplicationId(scanner.nextInt());
						loanApplication = loanApplicationStatus(loanApplication.getApplicationId());
						if (loanApplication.getStatus() != null) {
							System.out.println("Your loan application status : ");
							System.out.println("Application Id \tLoan Program \tStatus \tDate Of Interview");
							System.out.println(loanApplication.getApplicationId() + "\t\t\t"
									+ loanApplication.getLoanProgram() + "\t\t" + loanApplication.getStatus() + "\t"
									+ loanApplication.getDateOfInterview());
						} else {
							System.out.println("Please enter correct Application Id");
						}
						break;

					case 4:
						System.out.println("Exit from loan application");
						System.exit(0);

					default:
						System.out.println("Please enter correct choice");
						break;
					}

				}

			case 2:
				System.out.println("*****Loan Approval Department Member*****");
				System.out.println("\n");
				System.out.println("***Please login first***");
				System.out.println("\n");
				Users users = new Users();
				Users userLad = new Users();
				System.out.println("Please enter LogIn Id : ");
				users.setLoginId(scanner1.nextLine());
				System.out.println("Please enter password : ");
				users.setPassword(scanner1.nextLine());
				System.out.println("Please enter role : ");
				users.setRole(scanner1.nextLine());

				userLad = loginUser(users.getLoginId(), users.getPassword(), users.getRole());
				if (userLad.getLoginId() != null && userLad.getPassword() != null && userLad.getRole() != null) {

					if (userLad.getLoginId().equals(users.getLoginId())
							&& userLad.getPassword().equals(users.getPassword()) && userLad.getRole().equals("lad")) {
						System.out.println("Login successfully");

						while (true) {
							System.out.println("Please select your choice : ");
							System.out.println("1. View all loan program");
							System.out.println("2. View loan application");
							System.out.println("3. To Update Status");
							System.out.println("4. Exit");
							int option = scanner.nextInt();
							log.info("Option Entered");
							switch (option) {
							case 1:
								System.out.println("****************View All Loan Programs*******************");
								System.out.println("Loan programs are : ");
								System.out.println("\n");
								loanProgramsList = new ArrayList<LoanProgramsOffered>();
								loanProgramsList = loanProgramDetails();
								if (!loanProgramsList.isEmpty()) {
									System.out.println(
											"PROGRAMNAME DESCRIPTION TYPE DURATIONINYEARS MINLOANAMOUNT MAXLOANAMOUNT RATEOFINTEREST PROOFS_REQUIRED");
									for (LoanProgramsOffered loanProgramsOffered : loanProgramsList) {
										System.out.println(loanProgramsOffered.getProgramName() + " \t "
												+ loanProgramsOffered.getDescription() + " \t "
												+ loanProgramsOffered.getLoanType() + " \t "
												+ loanProgramsOffered.getDurationInYears() + " \t "
												+ loanProgramsOffered.getMinLoanAmount() + " \t "
												+ loanProgramsOffered.getMaxLoanAmount() + " \t "
												+ loanProgramsOffered.getRateOfInterest() + " \t "
												+ loanProgramsOffered.getProofsRequired());

									}
								} else
									System.out.println("No Loan Program found.");

								break;

							case 2: {
								System.out.println("Customer Details");
								loanApplication = new LoanApplication();
								customerDetailsList = loanApproval();
								if (customerDetailsList != null) {
									for (CustomerDetails customerDetails : customerDetailsList) {
										System.out.println("");
										System.out.println(
												"Application Id \t Appilcant Name \t Date Of Birth \t\t Marital Status \t Mobile Number");
										System.out.println(customerDetails.getApplicationId() + " \t\t\t "
												+ customerDetails.getApplicantName() + " \t "
												+ customerDetails.getDateOfBirth() + " \t "
												+ customerDetails.getMaritalStatus() + " \t\t "
												+ customerDetails.getMobileNumber());
										try {
											loanApplication = loanService
													.viewLoanApplicationStatus(customerDetails.getApplicationId());
										} catch (LoanApplicationException e) {
											System.err.println("Error : " + e.getMessage());
										}
										System.out.println("Loan Status: " + loanApplication.getStatus());
										System.out
												.println("Date Of Interview: " + loanApplication.getDateOfInterview());
									}

								} else {
									System.out.println("Invalid Program Name");
								}
							}
								break;
							case 3:
								System.out.println("Update Status");
								updateStatus();
								break;
							case 4:
								System.out.println("Exit from application");
								System.exit(0);
								break;

							default:
								System.out.println("Please enter correct choice");
								break;
							}
						}

					}

				} else {
					System.out.println("Invalid LoginId/Password/Role");
				}

				break;

			case 3:
				System.out.println("******************Admin*******************");
				System.out.println("\n");
				System.out.println("***Please login first***");
				System.out.println("\n");
				Users useradmin = new Users();
				Users userAdminValidate = new Users();
				System.out.println("Please enter LogIn Id : ");
				useradmin.setLoginId(scanner1.nextLine());
				System.out.println("Please enter password : ");
				useradmin.setPassword(scanner1.nextLine());
				System.out.println("Please enter role : ");
				useradmin.setRole(scanner1.nextLine());

				userAdminValidate = loginUser(useradmin.getLoginId(), useradmin.getPassword(), useradmin.getRole());
				if (userAdminValidate.getLoginId() != null && userAdminValidate.getPassword() != null
						&& userAdminValidate.getRole() != null) {

					if (userAdminValidate.getLoginId().equals(useradmin.getLoginId())
							&& userAdminValidate.getPassword().equals(useradmin.getPassword())
							&& userAdminValidate.getRole().equals("admin")) {
						System.out.println("Login successfully");
						System.out.println("\n");

						while (true) {
							System.out.println("Please select your choice : ");
							System.out.println("1. Add loan programs");
							System.out.println("2. Update loan program");
							System.out.println("3. Delete loan program");
							System.out.println("4. Generate reports");
							System.out.println("5. View all loan programs");
							System.out.println("6. EXIT");

							// Admin choice action
							int adminChoice = scanner.nextInt();

							switch (adminChoice) {
							case 1:
								System.out.println("****************Add Loan Program*******************");
								addLoanProgram();
								break;

							case 2:
								System.out.println("****************Update Loan Program*******************");
								System.out.println("Please enter loan program name");
								String loanProgramName = scanner1.nextLine();
								updateLoanProgram(loanProgramName);

								break;

							case 3:
								System.out.println("****************Delete Loan Program********************");
								System.out.println("Please enter loan program name");
								String loanProgramName1 = scanner1.nextLine();
								deleteLoanProgram(loanProgramName1);

								break;

							case 4:
								System.out.println(
										"****************Generate Report For Loan Applications*******************");
								System.out.println("View Loan Application List(Approved/Accepted/Rejected)");

								viewLoanApplications();

								break;

							case 5:
								System.out.println("****************View All Loan Programs*******************");

								System.out.println("Loan programs are : ");
								System.out.println("\n");
								loanProgramsList = new ArrayList<LoanProgramsOffered>();
								loanProgramsList = loanProgramDetails();
								if (!loanProgramsList.isEmpty()) {
									System.out.println(
											"PROGRAMNAME DESCRIPTION TYPE DURATIONINYEARS MINLOANAMOUNT MAXLOANAMOUNT RATEOFINTEREST PROOFS_REQUIRED");
									for (LoanProgramsOffered loanProgramsOffered : loanProgramsList) {
										System.out.println(loanProgramsOffered.getProgramName() + " \t "
												+ loanProgramsOffered.getDescription() + " \t "
												+ loanProgramsOffered.getLoanType() + " \t "
												+ loanProgramsOffered.getDurationInYears() + " \t "
												+ loanProgramsOffered.getMinLoanAmount() + " \t "
												+ loanProgramsOffered.getMaxLoanAmount() + " \t "
												+ loanProgramsOffered.getRateOfInterest() + " \t "
												+ loanProgramsOffered.getProofsRequired());
									}
								} else
									System.out.println("No Loan Program found.");

								break;

							case 6:
								System.out.println("Exit From Application");
								System.exit(0);

							default:
								System.out.println("Please select correct choice");
								break;
							}
						}
					}

				} else {
					System.out.println("Invalid LoginId/Password/Role");
				}
				break;
			default:
				System.out.println("Please select correct option");
				break;
			}

		} catch (InputMismatchException e) {

			System.err.println("Please enter integer only");

		}

	}

	private static void viewLoanApplications() {
		loanService = new LoanServiceImpl();
		// List<LoanApplication> loanApplicationList = new ArrayList<LoanApplication>();

		try {
			loanApplicationList = loanService.viewLoanApplications();
			if (loanApplicationList.isEmpty()) {
				System.out.println("No Loan Application available");
			} else {
				for (LoanApplication loanApplication : loanApplicationList) {
					System.out.println(loanApplication);
				}
			}

		} catch (LoanApplicationException e) {
			System.err.println("Error : " + e.getMessage());
		}
	}

	private static void deleteLoanProgram(String loanProgramName1) {
		loanService = new LoanServiceImpl();
		try {
			loanProgramsList = loanService.getLoanProgramDetails(loanProgramName1);

			System.out.println("Loan Program details for LoanProgramName = " + loanProgramName1);

			if (!loanProgramsList.isEmpty()) {
				System.out.println(
						"PROGRAMNAME DESCRIPTION TYPE DURATIONINYEARS MINLOANAMOUNT MAXLOANAMOUNT RATEOFINTEREST PROOFS_REQUIRED");
				for (LoanProgramsOffered loanProgramsOffered : loanProgramsList) {
					System.out.println(loanProgramsOffered.getProgramName() + " \t "
							+ loanProgramsOffered.getDescription() + " \t " + loanProgramsOffered.getLoanType() + " \t "
							+ loanProgramsOffered.getDurationInYears() + " \t " + loanProgramsOffered.getMinLoanAmount()
							+ " \t " + loanProgramsOffered.getMaxLoanAmount() + " \t "
							+ loanProgramsOffered.getRateOfInterest() + " \t "
							+ loanProgramsOffered.getProofsRequired());
				}
			} else {
				System.out.println("No Loan Program found.");
			}

			System.out.println("Do you want to delete ? (y/n)");
			String deleteConfirmation = scanner.next();

			if (deleteConfirmation.equalsIgnoreCase("y")) {
				status = loanService.deleteLoanProgram(loanProgramName1);
				if (status) {
					System.out.println("Record deleted successfully for programName = " + loanProgramName1);
				} else {
					System.out.println("Not Deleted");
				}
			} else {
				System.out.println("Not Deleted");
			}

		} catch (LoanApplicationException e) {
			System.err.println("Error : " + e.getMessage());
		}

	}

	private static void updateLoanProgram(String loanProgramName) {

		loanService = new LoanServiceImpl();

		try {
			loanProgramsList = loanService.getLoanProgramDetails(loanProgramName);

			System.out.println("Loan Program details for LoanProgramName = " + loanProgramName);

			if (!loanProgramsList.isEmpty()) {
				System.out.println(
						"PROGRAMNAME DESCRIPTION TYPE DURATIONINYEARS MINLOANAMOUNT MAXLOANAMOUNT RATEOFINTEREST PROOFS_REQUIRED");
				for (LoanProgramsOffered loanProgramsOffered : loanProgramsList) {
					System.out.println(loanProgramsOffered.getProgramName() + " \t "
							+ loanProgramsOffered.getDescription() + " \t " + loanProgramsOffered.getLoanType() + " \t "
							+ loanProgramsOffered.getDurationInYears() + " \t " + loanProgramsOffered.getMinLoanAmount()
							+ " \t " + loanProgramsOffered.getMaxLoanAmount() + " \t "
							+ loanProgramsOffered.getRateOfInterest() + " \t "
							+ loanProgramsOffered.getProofsRequired());
				}
			} else {
				System.out.println("No Loan Program found.");
			}
			System.out.println("Please enter details to update the loan program = " + loanProgramName);

			LoanProgramsOffered loanProgramsOffered = new LoanProgramsOffered();
			loanProgramsOffered.setProgramName(loanProgramName);
			System.out.println("Enter Program Description");
			loanProgramsOffered.setDescription(scanner1.nextLine());
			System.out.println("Enter Program Type");
			loanProgramsOffered.setLoanType(scanner1.nextLine());
			System.out.println("Enter Duration (in years)");
			loanProgramsOffered.setDurationInYears(scanner.nextDouble());
			System.out.println("Enter Minimum Loan Amount");
			loanProgramsOffered.setMinLoanAmount(scanner.nextDouble());
			System.out.println("Enter Maximum Loan Amount");
			loanProgramsOffered.setMaxLoanAmount(scanner.nextDouble());
			System.out.println("Enter Rate Of Interest");
			loanProgramsOffered.setRateOfInterest(scanner.nextDouble());
			System.out.println("Enter Proofs Required");
			loanProgramsOffered.setProofsRequired(scanner1.nextLine());

			status = loanService.updateLoanProgram(loanProgramsOffered);

			if (status) {
				System.out.println("Loan Application Updated Successfully for Loan Program " + loanProgramName);
			} else {
				System.out.println("Loan application not updated., Please try again");
			}

		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}

	}

	private static void addLoanProgram() {
		LoanProgramsOffered loanProgramsOffered = new LoanProgramsOffered();
		loanService = new LoanServiceImpl();
		System.out.println("\n");
		System.out.println("Please enter details of loan program : ");
		System.out.println("Enter Program Name");
		loanProgramsOffered.setProgramName(scanner1.nextLine());
		System.out.println("Enter Program Description");
		loanProgramsOffered.setDescription(scanner1.nextLine());
		System.out.println("Enter Program Type");
		loanProgramsOffered.setLoanType(scanner1.nextLine());
		System.out.println("Enter Duration (in years)");
		loanProgramsOffered.setDurationInYears(scanner.nextDouble());
		System.out.println("Enter Minimum Loan Amount");
		loanProgramsOffered.setMinLoanAmount(scanner.nextDouble());
		System.out.println("Enter Maximum Loan Amount");
		loanProgramsOffered.setMaxLoanAmount(scanner.nextDouble());
		System.out.println("Enter Rate Of Interest");
		loanProgramsOffered.setRateOfInterest(scanner.nextDouble());
		System.out.println("Enter Proofs Required");
		loanProgramsOffered.setProofsRequired(scanner1.nextLine());

		try {
			status = loanService.addLoanProgram(loanProgramsOffered);

			if (status) {
				System.out.println("Loan Program Added Successfully.");
			} else {
				System.out.println("Loan Program Not Added., Please try again!");
			}
		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}

	}

	// Check login credential
	private static Users loginUser(String loginId, String password, String role) {
		loanService = new LoanServiceImpl();
		Users users = null;
		try {
			users = loanService.validateLogInUser(loginId, password, role);
		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}
		return users;

	}

	private static LoanApplication loanApplicationStatus(int applicationId) {
		loanService = new LoanServiceImpl();
		LoanApplication loanApplication = new LoanApplication();
		try {
			loanApplication = loanService.viewLoanApplicationStatus(applicationId);
		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}
		return loanApplication;

	}

	// Apply for Loan

	private static int applyLoan() {

		CustomerDetails customerDetails = new CustomerDetails();
		LoanApplication loanApplication = new LoanApplication();
		loanService = new LoanServiceImpl();

		// Set userDetails input Data into CustomerDetails bean

		System.out.println("Please enter your name : ");
		customerDetails.setApplicantName(scanner1.nextLine());

		System.out.println("Please enter your date of birth(dd/MM/yyyy) : ");
		customerDetails.setDateOfBirth(scanner1.nextLine());

		System.out.println("Please enter marital status(Single/Married) : ");
		customerDetails.setMaritalStatus(scanner.next());

		System.out.println("Please enter your phone number(Landline), if not available please enter( 0 ) : ");
		customerDetails.setPhoneNumber(scanner.nextLong());

		System.out.println("Please enter your mobile number : ");
		customerDetails.setMobileNumber(scanner.nextLong());

		System.out.println("Please enter Count_Of_Dependents : ");
		customerDetails.setCountOfDependents(scanner.nextInt());

		System.out.println("Please enter your email id : ");
		customerDetails.setEmailId(scanner.next());

		// Set loanApplication input data into LoanApplication bean

		System.out.println("Please enter loan program : ");
		loanApplication.setLoanProgram(scanner1.nextLine());

		System.out.println("Please enter amount of loan : ");
		loanApplication.setAmountOfLoan(scanner.nextDouble());

		System.out.println("Please enter address of property : ");
		loanApplication.setAddressOfProperty(scanner1.nextLine());

		System.out.println("Please enter annual family income : ");
		loanApplication.setAnnualFamilyIncome(scanner.nextDouble());

		System.out.println("Please enter Document proofs available : ");
		loanApplication.setDocumentProofsAvailable(scanner1.nextLine());

		System.out.println("Please enter guarantee cover : ");
		loanApplication.setGuaranteeCover(scanner1.nextLine());

		System.out.println("Please enter market value of guarantee cover : ");
		loanApplication.setMarketValueOfGuaranteeCover(scanner.nextDouble());

		try {
			if (loanService.isvalidDetails(customerDetails, loanApplication)) {
				applicationId = loanService.applyForLoan(customerDetails, loanApplication);
			}

		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException.getMessage());
			System.err.println("Error : " + loanApplicationException.getMessage());
		}

		return applicationId;
	}

	// Show Loan Program Details
	private static List<LoanProgramsOffered> loanProgramDetails() {
		loanService = new LoanServiceImpl();
		loanProgramsList = new ArrayList<LoanProgramsOffered>();
		try {
			loanProgramsList = loanService.getLoanOfferedList();
		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}
		return loanProgramsList;
	}

	// retrieving customer Details based on the loan program
	public static List<CustomerDetails> loanApproval() {

		loanService = new LoanServiceImpl();
		customerDetailsList = new ArrayList<CustomerDetails>();
		System.out.println("Enter the program name");
		String programName = scanner.next();
		try {
			customerDetailsList = loanService.viewCustomerDetails(programName);

			if (customerDetailsList.isEmpty()) {
				System.out.println("Invalid Program name");
				System.out.println("\n");
			}
		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException);
			System.err.println("Error : " + loanApplicationException.getMessage());
		}
		return customerDetailsList;

	}

	// Updating the status of loan application
	public static void updateStatus() {
		System.out.println("Enter the Application Id for updating the status");
		int applicantId = scanner.nextInt();
		Date interviewDate = null;
		String enteredStatus = null;
		try {
			loanApplication = loanService.viewLoanApplicationStatus(applicantId);
			Date date = loanApplication.getDateOfInterview();
			if (date == null) {
				String actualStatus = "accepted";
				String actualStatus1 = "rejected";
				System.out.println("Enter the status");
				enteredStatus = scanner.next();
				loanApplication.setStatus(enteredStatus);
				if (enteredStatus.matches(actualStatus)) {
					System.out.println("Enter the Interview Date");
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					interviewDate = format.parse(scanner.next());
					loanApplication.setDateOfInterview(interviewDate);
					System.out.println("The interview date is scheduled on: " + interviewDate);
				} else if (enteredStatus.matches(actualStatus1)) {
					System.out.println("The Application Is Rejected");
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					interviewDate = format.parse("01/01/2000");
					loanApplication.setDateOfInterview(interviewDate);
				} else {
					System.out.println("Invalid status is entered");
				}
			} else {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date currentDate = new Date();
				dateFormat.format(currentDate);
				if (currentDate.compareTo(date) > 0) {
					System.out.println("Interview Date Is Already Finished");
					System.out.println("Kindly Update the Status To Approved Or Rejected");
					System.out.println("Enter the status");
					enteredStatus = scanner.next();
					loanApplication.setStatus(enteredStatus);
				} else {
					System.out.println("Interview Date is Already Scheduled");
					System.out.println("Enter 1 To Reschedule The Date");
					{
						switch (scanner.nextInt()) {
						case 1:
							System.out.println("Enter the Interview Date");
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
							interviewDate = format.parse(scanner.next());
							loanApplication.setDateOfInterview(interviewDate);
							System.out.println("Enter the status");
							enteredStatus = scanner.next();
							loanApplication.setStatus(enteredStatus);
							break;
						case 2:
							System.exit(0);
							break;
						}
					}

				}
			}
			loanApplication.setApplicationId(applicantId);
			loanService.updateStatusOfApplicant(loanApplication);

		} catch (LoanApplicationException loanApplicationException) {
			log.error(loanApplicationException.getMessage());
			System.err.println("Error : " + loanApplicationException.getMessage());
		} catch (ParseException parseException) {
			log.error(parseException.getMessage());
			System.err.println("Error : " + parseException.getMessage());
		}
	}

}
