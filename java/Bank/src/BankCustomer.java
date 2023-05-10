//package banking;

import java.util.ArrayList;
import java.util.Date;

public class BankCustomer  {
	private long accountNumber;
	private int sortCode;
	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private Date customerDateOfBirth;
	private int balance;
	private ArrayList<Transactions> transactions = new ArrayList<Transactions>();
	public static int OPENING_BALANCE = 0;
	
	BankCustomer(){
		
	}
	
	
	
	BankCustomer(long accountNumber, int sortCode, String customername, String customerAddress,
			String customerEmail, Date customerDateOfBirth){
		this( accountNumber,  sortCode,  customername,  customerAddress,
			 customerEmail,  customerDateOfBirth, OPENING_BALANCE);
	}
	
	BankCustomer(long accountNumber, int sortCode, String customername, String customerAddress,
			String customerEmail, Date customerDateOfBirth, int balance){
		setAccountNumber(accountNumber);
		setSortCode(sortCode);
		setCustomerName(customername);
		setCustomerAddress(customerAddress);
		setCustomerEmail(customerAddress);
		setCustomerDateOfBirth(customerDateOfBirth);
		setBalance(balance);
		
	}
	
	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<Transactions> transactions) {
		this.transactions = transactions;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getSortCode() {
		return sortCode;
	}
	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Date getCustomerDateOfBirth() {
		return customerDateOfBirth;
	}
	/*public void setCustomerDateOfBirth(int year, int month, int date) {
		GregorianCalendar calendar = new GregorianCalendar(year, month, date);
		customerDateOfBirth = calendar.getTime();
	}*/
	public void setCustomerDateOfBirth(Date date) {
		customerDateOfBirth = date;
	}
	@Override
	public String toString() {
		return "BankCustomer [accountNumber=" + accountNumber + ", sortCode=" + sortCode + ", customerName="
				+ customerName + ", customerAddress=" + customerAddress + ", customerEmail=" + customerEmail
				+ ", customerDateOfBirth=" + customerDateOfBirth + ", balance=" + balance + ", transactions="
				+ transactions + "]";
	}
	
}
