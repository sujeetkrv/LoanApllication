package com.cg.loan.exception;

public class LoanApplicationException extends Exception{

	private static final long serialVersionUID = 726264577455921591L;
	public LoanApplicationException()
	{
		super();
	}
	
	public LoanApplicationException(String message,Throwable cause)
	{
		super(message, cause);
	}
	
	public LoanApplicationException(String message)
	{
		super(message);
	}
	public LoanApplicationException(Throwable cause)
	{
		super(cause);
	}
}
