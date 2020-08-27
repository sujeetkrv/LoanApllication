package com.cg.loan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.loan.exception.LoanApplicationException;

public class ConnectionManager{

	 
	static Connection connection = null;
	public static Connection getConnection() throws LoanApplicationException {
		
		//Create Properties reference
		Properties prop = new Properties();
		try {
			if(connection==null) {
				
				//Getting driverName, URL , userName and password from properties file
				prop.load(new FileInputStream("resources/db.prop"));
				Class.forName(prop.getProperty("driverName"));
				connection=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("userName"),prop.getProperty("password"));
			
				 
			}
			 
		}
		catch(ClassNotFoundException e) {
			throw new LoanApplicationException(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new LoanApplicationException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LoanApplicationException(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new LoanApplicationException(e.getMessage());
		}
		return connection;
	}
	
	 
	
}
