package bankproject.entities;

import java.util.Date;

public class Operation extends BankEntity {
	private Integer id;
	private String accountNumber;
	private Double creditDebit;
	private Date dateOperation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getCreditDebit() {
		return creditDebit;
	}
	public void setCreditDebit(Double creditDebit) {
		this.creditDebit = creditDebit;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
