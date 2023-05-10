//package banking;

import java.util.Date;

public interface BankInterface {
	public long createAccountNumber();
	public BankCustomer createAccount(long accountNumber, int sortCode, String customerName, String customerAddress,
			String customerEmail, Date customerDateOfBirth);
	public BankCustomer findCustomer(long accountNumber);
	public BankCustomer findCustomer(String name, Date dateOfBIrth, String address);
	public boolean debitAccount(long accountNumber, int amount);
	public boolean creditAccount(long accountNumber, int amount);
	public void printBalance(long accountNumber);
}
