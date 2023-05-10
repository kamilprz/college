//package banking;

import java.util.Date;

public class Transactions {
	private Date transactionDate;
	private String transactionType;
	private double transactionAmount;
	private double closingBalance;
	public static final String DEBIT = "Debit";
	public static final String CREDIT = "Credit";
	
	Transactions(Date transactionDate, String transactionType, double transactionAmount, double closingBalance){
		setTransactionDate(transactionDate);
		setTransactionType(transactionType);
		setTransactionAmount(transactionAmount);
		setClosingBalance(closingBalance);
	}
	
	Transactions(){
		
		
	}
	
	@Override
	public String toString() {
		return "Transactions \nTransactionDate=" + transactionDate + "\nTransactionType=" + transactionType
				+ "\nTransactionAmount=" + transactionAmount + "\n ClosingBalance=" + closingBalance + "\n";
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	
}
