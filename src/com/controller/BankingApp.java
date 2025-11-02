package com.controller;

import com.dao.AccountDao;
import com.dto.Account;
import com.dto.User;
import com.dto.UserDetails;
import com.service.AccountService;
import com.service.UserService;
import java.util.Scanner;

public class BankingApp {

	public static void bankLogin(UserDetails currentUser) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\n****************************Bank Login Page****************************");
		System.out.println("Enter security pin for login into bank account");
		String securityPin = scanner.nextLine();
		
		if (currentUser.getSecretPin().equals(securityPin)) {
			System.out.println("logged into bank account....");
			while (true) {
				System.out.println(
						"Press 1 for credit \nPress 2 for debit \nPress 3 for transfer \nPress 4 for check your account balance \nPress 0 for exit");
				int key = scanner.nextInt();
				scanner.nextLine();
				switch (key) {
				case 1: {
					System.out.println("Enter amount to credit into your account:");
					double amount = scanner.nextDouble();
					// add money into account
					AccountDao accountDao = new AccountDao();
					if(accountDao.creditAccount(amount)) {			
						System.out.println("Money Credited into your account successfully.");
					} else {
						System.out.println("Transaction unsuccessfull!!");
					}
					break;
				}
				case 2: {
					System.out.println("Enter amount for debit:");
					double amount = scanner.nextDouble();
					scanner.nextLine();
					System.out.println("Enter security pin:");
					String tempSecurityPin = scanner.nextLine();
					if (tempSecurityPin.equals(securityPin)) {
						// debit money from account
						System.out.println(amount + " debited successfully.");
					} else {
						System.out.println("Incorrect pin");
					}
					break;
				}
				case 3: {
					System.out.println("Enter account number in which you transfer money");
					long accountNumber = scanner.nextLong();
					scanner.nextLine();
					System.out.println("Enter your security pin");
					String tempSecurityPin = scanner.nextLine();
					if (tempSecurityPin.equals(securityPin) /* && accountNumber.exists() */) {
						System.out.println("Amount transferred successfully to " + accountNumber);
					} else {
						System.out.println("Pin or Account number is incorrect..");
					}
					break;
				}
				case 4: {
					System.out.println("Enter security pin:");
					String tempSecurityPin = scanner.nextLine();
					if (securityPin.equals(tempSecurityPin)) {
						System.out.println("Your account balance is: ");
					} else {
						System.out.println("Pin is incorrect..");
					}
					break;
				}
				case 0:
					return;
				default:
					System.out.println("Invalid Input....");
					break;
				}
			}
		} else {
			System.out.println("Incorrect Pin");
		}
		scanner.close();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserService userService = new UserService();
		System.out.println("****************************BANKING SYSTEM****************************");
		System.out.println("Choose any option: \nPress 1 for create user... \nPress 2 for Login.... ");
		int key = scanner.nextInt();
		scanner.nextLine();
		
		switch (key) {
		case 1: {
			System.out.println("****************************Create User****************************");
			System.out.println("Enter User name: ");
			String userName = scanner.nextLine();

			if (!UserService.validateName(userName.trim())) {
				System.out.println(userName);
				System.out.println("Invalid name!!!");
				break;
			}

			System.out.println("Enter the email: ");
			String userEmail = scanner.nextLine();

			if (!UserService.validateEmail(userEmail.trim())) {
				System.out.println("Email id is not valid!!");
				break;
			}

			System.out.println("Enter Password, length should be more than 5: ");
			String password = scanner.nextLine().trim();
			System.out.println("Re-enter your password: ");
			String passwordVarification = scanner.nextLine().trim();

			if (password.length() > 5 && passwordVarification.equals(password) && userName.length() >= 3) {
				User user = new User(userName, userEmail, password); //need to store encrypted password
				userService.saveUserService(user);
			} else {
				System.out.println(
						password.length() <= 5 ? "Password length is less than 6!!!!" : "Passwords are different!!!!!");
			}
			break;
		}

		case 2: {
			System.out.println("****************************User Login Page****************************");
			System.out.println("Enter Email:");
			String email = scanner.next();
			if (!UserService.validateEmail(email)) {
				System.out.println("Email is not valid!!");
				break;
			}

			System.out.println("Enter your password:");
			String password = scanner.next();

			UserDetails currentUser = userService.fetchUserDetailsService(email, password);
			
			if (currentUser != null && currentUser.isAccountExists()) {
				System.out.println("Logged in successfully.....");
				bankLogin(currentUser);
			} else if(currentUser != null && (!currentUser.isAccountExists())) {
				int currentUserId = currentUser.getUserId();
				System.out.println("Logged in successfully.....");
				System.out.println("****************************Create Bank Account****************************");
				AccountService accountService = new AccountService();
				System.out.println("Enter initial balance: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Enter security pin: "); // need to verify twice
				String securityPin = scanner.nextLine();
				
				Account account = new Account(securityPin, currentUserId, amount);
				accountService.accountService(account, currentUser);
			}
			break;
		}
		default:
			System.out.println("Invalid input, Thanks for using bank management system");
			break;
		}
		scanner.close();
	}
}
