package com.service;

import com.dao.AccountDao;
import com.dto.Account;

public class AccountService {
	AccountDao accountDao = new AccountDao();

	public void accountService(Account account) {
		if(!accountDao.accountExists(account.getAccountNumber())) {
			accountDao.saveAccount(account);
		}
	}

}
