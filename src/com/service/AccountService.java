package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.AccountDao;
import com.dto.Account;

public class AccountService {
	AccountDao accountDao = new AccountDao();

	public void accountService(Account account, int currentUserId) {
		if(account.getSecretPin().length() == 4) {
			if(account.getAmount() < 500) {
				System.out.println("Opening balance should be atleast 500!!!");
				return;
			}				
			if(accountDao.accountExists(currentUserId))
				return;
			accountDao.saveAccount(account);
		} else {
			System.out.println("Only four digit pin is acceptable!!!!");
		}
	}
	
	public boolean validatePin(int userId, int userPin) {
		ResultSet resultSet = accountDao.accountDetails(userId);
		try {
			if(resultSet.next()) {
				int secretPin = resultSet.getInt("secretPin");
				if (secretPin == userPin)
					return true;
				else {					
					System.out.println("Incorrect Pin!!!!");
					return false;
				}
			} else {
				System.out.println("Details not found!!!!");
				return false;
			}
			
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
			return false;
		}

	}

}
