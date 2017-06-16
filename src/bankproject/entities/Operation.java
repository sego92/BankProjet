package bankproject.entities;

import java.util.Date;

public class Operation extends BankEntity {
	private Integer id;
	private Account account;
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
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

}
