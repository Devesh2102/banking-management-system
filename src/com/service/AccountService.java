package com.service;

import com.dao.AccountDao;
import com.dto.Account;
import com.dto.UserDetails;

public class AccountService {
	AccountDao accountDao = new AccountDao();

	public void accountService(Account account, UserDetails currentUser) {
		if(account.getSecretPin().length() == 4) {
			if(account.getAmount() < 500) {
				System.out.println("Opening balance should be atleast 500!!!");
				return;
			}
			accountDao.saveAccount(currentUser, account);
		} else {
			System.out.println("Only four digit pin is acceptable!!!!");
		}
	}
}
