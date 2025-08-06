package com.controller;

import com.dao.UserDao;
import com.dto.Account;
import com.dto.User;
import com.service.AccountService;
import com.service.UserService;
import java.util.Scanner;

public class BankingApp {

	public static void bankLogin(int userId) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\n****************************Bank Login Page****************************");
		System.out.println("Enter security pin for login into bank account");
		int securityPin = scanner.nextInt();
		
		AccountService accountService = new AccountService();
		if (accountService.validatePin(userId, securityPin)) {
			System.out.println("logged into bank account....");
			while (true) {
				System.out.println(
						"Press 1 for credit \nPress 2 for debit \nPress 3 for transfer \nPress 4 for check your account balance \nPress 0 for exit");
				int key = scanner.nextInt();
				switch (key) {
				case 1: {
					System.out.println("Enter amount to credit into your account:");
					double amount = scanner.nextDouble();
					// add money into account
					System.out.println("Money Credited into your account successfully.");
					break;
				}
				case 2: {
					System.out.println("Enter amount for debit:");
					double amount = scanner.nextDouble();
					System.out.println("Enter security pin:");
					int tempSecurityPin = scanner.nextInt();
					if (tempSecurityPin == securityPin) {
						// debit money from account
						System.out.println("Money debited successfully.");
					} else {
						System.out.println("Incorrect pin");
					}
					break;
				}
				case 3: {
					System.out.println("Enter account number in which you transfer money");
					long accountNumber = scanner.nextLong();
					System.out.println("Enter your security pin");
					int tempSecurityPin = scanner.nextInt();
					if (tempSecurityPin == securityPin /* && accountNumber.exists() */) {
						System.out.println("Amount transferred successfully.");
					} else {
						System.out.println("Pin or Account number is incorrect..");
					}
					break;
				}
				case 4: {
					System.out.println("Enter security pin:");
					int tempSecurityPin = scanner.nextInt();
					if (securityPin == tempSecurityPin) {
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
		System.out.println("****************************BANKING SYSTEM****************************");
		System.out.println("Choose any option: \nPress 1 for create user... \nPress 2 for Login.... ");
		int key = scanner.nextInt();

		switch (key) {
		case 1: {
			System.out.println("****************************Create User****************************");
			System.out.println("Enter User name: ");
			scanner.nextLine();
			String userName = scanner.nextLine();

			if (!UserService.validateName(userName)) {
				System.out.println(userName);
				System.out.println("Invalid name!!!");
				break;
			}

			System.out.println("Enter the email: ");
			String userEmail = scanner.nextLine();

			if (!UserService.validateEmail(userEmail)) {
				System.out.println("Email id is not valid!!");
				break;
			}

			System.out.println("Enter Password, length should be more than 5: ");
			String password = scanner.nextLine();
			System.out.println("Re-enter your password: ");
			String passwordVarification = scanner.nextLine();

			if (password.length() > 5 && passwordVarification.equals(password) && userName.length() >= 3) {
				UserService userService = new UserService();
				User user = new User(userName, userEmail, password);
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

			UserDao userDao = new UserDao();
			int currentUserId = userDao.userLogin(email, password);

			if (currentUserId != 0) {
				System.out.println("Logged in successfully.....");
				System.out.println(
						"Press 1 for create bank account \nPress 2 for login in your bank account \nPress 3 for main menu");
				key = scanner.nextInt();
				switch (key) {
				case 1: {
					System.out.println("****************************Create Bank Account****************************");
					AccountService accountService = new AccountService();
					System.out.println("Enter initial balance: ");
					double amount = scanner.nextDouble();
					scanner.nextLine();
					System.out.println("Enter security pin: "); // need to verify twice
					String securityPin = scanner.nextLine();
					
					Account account = new Account(securityPin, currentUserId, amount);
					accountService.accountService(account, currentUserId);
					break;
				}
				case 2: {
					bankLogin(currentUserId);
					break;
				}
				case 3: {
					System.out.println("main menu");
					break;
				}
				default:
					System.out.println("Invalid input....");
				}
			}
			break;
		}
		default:
			System.out.println("Invalid input, Thanks for using bank management system");
			return;
		}
		scanner.close();
	}
}
