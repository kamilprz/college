//package banking;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bank implements BankInterface {
	// static BankCustomer[] customers = new BankCustomer[100];
	private HashMap<Long, BankCustomer> bankCustomers;
	private static long accountNumber;
	private static int sortCode;

	Bank() {
		accountNumber = 1000100L;
		sortCode = 1000;
		bankCustomers = new HashMap<Long, BankCustomer>();
	}

	public static void main(String[] args) {
		Bank bank = new Bank();
		long accountNumber = bank.createAccountNumber();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BankCustomer customer;
		try {
			System.out.println("Welcome to bank!");
			customer = bank.createAccount(accountNumber, 1234, "Eugene Krabs", "Pearse Street 12, Dublin 1",
					"sample@example.com", formatter.parse("10/01/1999"));
			bank.printBalance(customer.getAccountNumber());
			bank.creditAccount(customer.getAccountNumber(), 1000);
			bank.printBalance(customer.getAccountNumber());
			bank.debitAccount(customer.getAccountNumber(), 500);
			bank.printBalance(customer.getAccountNumber());
			System.out.println("Testing search engine!");
			BankCustomer anotherOne = bank.findCustomer("Eugene Krabs", formatter.parse("10/01/1999"),
					"Pearse Street 12, Dublin 1");
			bank.debitAccount(anotherOne.getAccountNumber(), 155);
			bank.printBalance(anotherOne.getAccountNumber());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bank.readCustomersFromFile("src/banking/customers.txt");
		/*
		 * Iterator<Map.Entry<Long, BankCustomer>> iterator =
		 * bank.bankCustomers.entrySet().iterator(); while(iterator.hasNext()) {
		 * Map.Entry<Long, BankCustomer> entry = iterator.next();
		 * System.out.println(entry.getValue()); bank.printStatement(entry.getKey()); }
		 */
		bank.createAccountFromConsole();
		ArrayList<Long> accountNumbers = bank.getCustomerAccountNumbers();
		for (int i = 0; i < accountNumbers.size(); i++) {
			System.out.println(accountNumbers.get(i));
		}

	}

	public void createAccountFromConsole() {
		Scanner reader = new Scanner(System.in);
		try {
			System.out.print("Supply account name > ");
			String name = reader.next();
			System.out.print("Supply address > ");
			String address = reader.next();
			System.out.print("Supply email > ");
			String email = reader.next();
			System.out.print("Supply date (dd/mm/yyy) > ");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse(reader.next());
			createAccount(name, address, email, date);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	

	}

	public void readCustomersFromFile(String path) {
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			BufferedReader bf = new BufferedReader(fr);
			boolean EOFFound = false;
			while (!EOFFound) {
				String line = bf.readLine();
				if (line == null) {
					EOFFound = true;
				} else {
					String[] customer = line.split(",");
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formatter.parse(customer[5]);
					BankCustomer newCustomer = createAccount(Long.parseLong(customer[0]), Integer.parseInt(customer[1]),
							customer[2], customer[3], customer[4], date);
					bankCustomers.put(newCustomer.getAccountNumber(), newCustomer);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public BankCustomer createAccount(long accountNumber, int sortCode, String customerName, String customerAddress,
			String customerEmail, Date customerDateOfBirth) {
		/*
		 * BankCustomer newCustomer = new BankCustomer();
		 * newCustomer.setAccountNumber(accountNumber);
		 * newCustomer.setSortCode(sortCode); newCustomer.setCustomerName(customerName);
		 * newCustomer.setCustomerAddress(customerAddress);
		 * newCustomer.setCustomerEmail(customerEmail);
		 * newCustomer.setCustomerDateOfBirth(customerDateOfBirth);
		 * bankCustomers.put(accountNumber, newCustomer);
		 */
		BankCustomer newCustomer = new BankCustomer(accountNumber, sortCode, customerName, customerAddress,
				customerEmail, customerDateOfBirth);
		bankCustomers.put(accountNumber, newCustomer);
		return newCustomer;
	}
	
	public BankCustomer createAccount(String customerName, String customerAddress, String customerEmail,
			String customerDateOfBirth) {
		Date birthDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			birthDate = formatter.parse(customerDateOfBirth);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		BankCustomer newCustomer = createAccount(customerName, customerAddress, customerEmail, birthDate);
		return newCustomer;
	}

	public BankCustomer createAccount(String customerName, String customerAddress, String customerEmail,
			Date customerDateOfBirth) {
		BankCustomer newCustomer = new BankCustomer(createAccountNumber(), createSortCode(), customerName,
				customerAddress, customerEmail, customerDateOfBirth);
		bankCustomers.put(newCustomer.getAccountNumber(), newCustomer);
		return newCustomer;
	}

	@Override
	public BankCustomer findCustomer(long accountNumber) {
		/*
		 * BankCustomer customer = null; boolean isFound = false; for (int i = 0; i <
		 * customers.length && !isFound; i++) { if
		 * (customers[i].getCustomerName().equals(name)) { customer = customers[i];
		 * isFound = true; } }
		 */
		BankCustomer customer = bankCustomers.get(accountNumber);
		return customer;
	}

	@Override
	public BankCustomer findCustomer(String name, Date date, String address) {
		BankCustomer customer = null;
		boolean isFound = false;
		/*
		 * for (int i = 0; i < customers.length && !isFound; i++) { if
		 * (customers[i].getCustomerName().equals(name) &&
		 * customers[i].getCustomerAddress().equals(address) &&
		 * (customers[i].getCustomerDateOfBirth().compareTo(date) == 0 ? true : false))
		 * { customer = customers[i]; isFound = true; } }
		 */
		/*
		 * for (Map.Entry<Long, BankCustomer> cust: bankCustomers.entrySet()) {
		 * 
		 * }
		 */
		Iterator<Map.Entry<Long, BankCustomer>> iterator = bankCustomers.entrySet().iterator();
		while (iterator.hasNext() && !isFound) {
			Map.Entry<Long, BankCustomer> entry = iterator.next();
			BankCustomer cust = entry.getValue();
			if (cust.getCustomerName().equals(name) && cust.getCustomerDateOfBirth().equals(date)
					&& cust.getCustomerAddress().equals(address)) {
				isFound = true;
				customer = cust;
			}

		}
		return customer;
	}

	@Override
	public long createAccountNumber() {
		return accountNumber++;
	}

	public int createSortCode() {
		return sortCode++;
	}

	@Override
	public boolean debitAccount(long accountNumber, int amount) {
		BankCustomer customer = findCustomer(accountNumber);

		boolean accountDebited = false;
		if (customer != null && customer.getBalance() >= amount) {
			/*
			 * Transactions newTransaction = new Transactions();
			 * newTransaction.setTransactionType(Transactions.DEBIT);
			 * newTransaction.setTransactionAmount(amount);
			 * newTransaction.setTransactionDate(new Date());
			 * customer.setBalance(customer.getBalance() - amount);
			 * newTransaction.setClosingBalance(customer.getBalance());
			 * customer.getTransactions().add(newTransaction);
			 */
			customer.setBalance(customer.getBalance() - amount);
			Transactions newTransaction = new Transactions(new Date(), Transactions.DEBIT, amount,
					customer.getBalance());
			customer.getTransactions().add(newTransaction);
			accountDebited = true;
		}
		return accountDebited;
	}

	@Override
	public boolean creditAccount(long accountNumber, int amount) {
		BankCustomer customer = findCustomer(accountNumber);
		boolean accountCredited = false;
		if (customer != null) {
			/*
			 * Transactions newTransaction = new Transactions();
			 * newTransaction.setTransactionType(Transactions.CREDIT);
			 * newTransaction.setTransactionAmount(amount);
			 * newTransaction.setTransactionDate(new Date());
			 * customer.setBalance(customer.getBalance() + amount);
			 * newTransaction.setClosingBalance(customer.getBalance());
			 * customer.getTransactions().add(newTransaction);
			 */
			customer.setBalance(customer.getBalance() + amount);
			Transactions newTransaction = new Transactions(new Date(), Transactions.CREDIT, amount,
					customer.getBalance());
			customer.getTransactions().add(newTransaction);
			accountCredited = true;
		}
		return accountCredited;
	}

	@Override
	public void printBalance(long accountNumber) {
		BankCustomer customer = findCustomer(accountNumber);
		if (customer != null)
			System.out.printf("Balance of customer %s: $%10d%n", customer.getCustomerName(), customer.getBalance());

	}

	public void printStatement(long accountNumber) {
		BankCustomer customer = findCustomer(accountNumber);
		ArrayList<Transactions> transactions = customer.getTransactions();
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println(transactions.get(i));
		}

	}

	public ArrayList<Long> getCustomerAccountNumbers() {
		ArrayList<Long> customerAccountNumbers = new ArrayList<Long>();
		Iterator<Map.Entry<Long, BankCustomer>> iterator = bankCustomers.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long, BankCustomer> entry = iterator.next();
			customerAccountNumbers.add(entry.getKey());
		}
		return customerAccountNumbers;
	}
}
