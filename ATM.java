import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.InputMismatchException;

class Account {
	private int pinCode;
	private int NumberOfCustomer;
	private double savingsBalance = 0;
	private double BalanceOfCheckings = 0;
	Scanner input = new Scanner(System.in);
	DecimalFormat AmountOfMoney = new DecimalFormat("'$'###,##0.00");

	public Account(){}

	public Account(int NumberOfCustomer, int pinCode) {
		this.pinCode = pinCode;
		this.NumberOfCustomer = NumberOfCustomer;
	}

	public Account(int NumberOfCustomer, int pinCode, double BalanceOfCheckings, double savingsBalance) {
		this.pinCode = pinCode;
		this.NumberOfCustomer = NumberOfCustomer;
		this.savingsBalance = savingsBalance;
		this.BalanceOfCheckings = BalanceOfCheckings;
	}

	public int setNumberOfCustomer(int NumberOfCustomer) {
		this.NumberOfCustomer = NumberOfCustomer;
		return NumberOfCustomer;
	}

	public int getNumberOfCustomer() {
		return NumberOfCustomer;
	}

	public int setpinCode(int pinCode) {
		this.pinCode = pinCode;
		return pinCode;
	}

	public int getpinCode() {
		return pinCode;
	}

	public double getBalanceOfCheckings() {
		return BalanceOfCheckings;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public double CheckingsWithrawalAmount(double amount) {
		BalanceOfCheckings -= amount;
		return BalanceOfCheckings;
	}

	public double savingsWithrawalAmount(double amount) {
		savingsBalance -= amount;
		return savingsBalance;
	}

	public double CheckingsDepositAmount(double amount) {
		BalanceOfCheckings += amount;
		return BalanceOfCheckings;
	}

	public double SavingsDepositAmount(double amount) {
		savingsBalance += amount;
		return savingsBalance;
	}

	public void CheckingsTransferAmount(double amount) {
		BalanceOfCheckings -= amount;
		savingsBalance += amount;
	}

	public void SavingsTransferAmount(double amount) {
		savingsBalance -= amount;
		BalanceOfCheckings += amount;
	}

	public void getCheckingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
				System.out.print("\nAmount you want to withdraw from Checkings Account: ");
				double amount = input.nextDouble();
				if ((BalanceOfCheckings - amount) >= 0 && amount >= 0) {
					CheckingsWithrawalAmount(amount);
					System.out.println("\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
					end = true;
				} else {
					System.out.println("\nBalance Cannot be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getsavingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
				System.out.print("\nAmount you want to withdraw from Savings Account: ");
				double amount = input.nextDouble();
				if ((savingsBalance - amount) >= 0 && amount >= 0) {
					savingsWithrawalAmount(amount);
					System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getCheckingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
				System.out.print("\nAmount you want to deposit from Checkings Account: ");
				double amount = input.nextDouble();
				if ((BalanceOfCheckings + amount) >= 0 && amount >= 0) {
					CheckingsDepositAmount(amount);
					System.out.println("\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getSavingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
				System.out.print("\nAmount you want to deposit into your Savings Account: ");
				double amount = input.nextDouble();

				if ((savingsBalance + amount) >= 0 && amount >= 0) {
					SavingsDepositAmount(amount);
					System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getTransferInput(String accType) {
		boolean end = false;
		while (!end) {
			try {
				if (accType.equals("Checkings")) {
					System.out.println("\nSelect an account you wish to tranfers funds to:");
					System.out.println("1. Savings");
					System.out.println("2. Exit");
					System.out.print("\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
						System.out.print("\nAmount you want to deposit into your Savings Account: ");
						double amount = input.nextDouble();
						if ((savingsBalance + amount) >= 0 && (BalanceOfCheckings - amount) >= 0 && amount >= 0) {
							CheckingsTransferAmount(amount);
							System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
							System.out.println(
									"\nCurrent Checkings Account Balance: " + AmountOfMoney.format(BalanceOfCheckings));
							end = true;
						} else {
							System.out.println("\nBalance Cannot Be Negative.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
					}
				} else if (accType.equals("Savings")) {
					System.out.println("\nSelect an account you wish to tranfers funds to: ");
					System.out.println("1. Checkings");
					System.out.println("2. Exit");
					System.out.print("\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nCurrent Savings Account Balance: " + AmountOfMoney.format(savingsBalance));
						System.out.print("\nAmount you want to deposit into your savings account: ");
						double amount = input.nextDouble();
						if ((BalanceOfCheckings + amount) >= 0 && (savingsBalance - amount) >= 0 && amount >= 0) {
							SavingsTransferAmount(amount);
							System.out.println("\nCurrent checkings account balance: " + AmountOfMoney.format(BalanceOfCheckings));
							System.out.println("\nCurrent savings account balance: " + AmountOfMoney.format(savingsBalance));
							end = true;
						} else {
							System.out.println("\nBalance Cannot Be Negative.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}
}


class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat AmountOfMoney = new DecimalFormat("'$'###,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();

	public void getLogin(){
		boolean end = false;
		int NumberOfCustomer = 0;
		int pinCode = 0;
		while (!end) {
			try {
				System.out.print("\nEnter your customer number: ");
				NumberOfCustomer = menuInput.nextInt();
				System.out.print("\nEnter your PIN number: ");
				pinCode = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Account acc = (Account) pair.getValue();
					if (data.containsKey(NumberOfCustomer) && pinCode == acc.getpinCode()) {
						getAccountType(acc);
						end = true;
						break;
					}
				}
				if (!end) {
					System.out.println("\nWrong Customer Number or Pin Number");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s). Only Numbers.");
			}
		}
	}

	public void getAccountType(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				System.out.println(" Type 1 - Checkings Account");
				System.out.println(" Type 2 - Savings Account");
				System.out.println(" Type 3 - Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					getChecking(acc);
					break;
				case 2:
					getSaving(acc);
					break;
				case 3:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getChecking(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCheckings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					System.out.println("\nCheckings Account Balance: " + AmountOfMoney.format(acc.getBalanceOfCheckings()));
					break;
				case 2:
					acc.getCheckingWithdrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;

				case 4:
					acc.getTransferInput("Checkings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getSaving(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSavings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("Choice: ");
				int selection = menuInput.nextInt();
				switch (selection) {
				case 1:
					System.out.println("\nSavings Account Balance: " + AmountOfMoney.format(acc.getSavingsBalance()));
					break;
				case 2:
					acc.getsavingWithdrawInput();
					break;
				case 3:
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Savings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void createAccount(){
		int cst_no = 0;
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nEnter your customer number ");
				cst_no = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					if (!data.containsKey(cst_no)) {
						end = true;
					}
				}
				if (!end) {
					System.out.println("\nThis customer number is already registered");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nEnter PIN to be registered");
		int pin = menuInput.nextInt();
		data.put(cst_no, new Account(cst_no, pin));
		System.out.println("\nYour new account has been successfuly registered!");
		System.out.println("\nRedirecting to login.............");
		getLogin();
	}

	public void mainMenu(){
		data.put(952141, new Account(952141, 191904, 1000, 5000));
		data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\n Type 1 - Login");
				System.out.println(" Type 2 - Create Account");
				System.out.print("\nChoice: ");
				int choice = menuInput.nextInt();
				switch (choice) {
				case 1:
					getLogin();
					end = true;
					break;
				case 2:
					createAccount();
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nThank You for using this atmMachine.\n");
		menuInput.close();
		System.exit(0);
	}
}

public class atmMachine {

	public static void main(String[] args){
		OptionMenu optionMenu = new OptionMenu();
		introduction();
		optionMenu.mainMenu();
	}
	public static void introduction() {
		System.out.println("Welcome to the atmMachine Project!");
	}
}
