package bankproject.entities;

public class Account extends BankEntity {
	private Integer id;
	private String accountNumber;
	private Customer customer;
	private Double solde;
	private String pays; 
	
	
	
	public String generateAccountNumber (){
			int numeroAleatoire = (int) ((Math.random()*(999999-111111)+111111));
			String str = String.valueOf(numeroAleatoire);
			String indicatif = null;
			if (pays.compareTo("France")==0){
				indicatif = "FR";
			}else if (pays.compareTo("Pays-Bas")==0){
				indicatif = "NL";
			}else if (pays.compareTo("Grande-Bretagne")==0){
				indicatif = "GB";
			}else if (pays.compareTo("Allemagne")==0){
				indicatif = "DE";
			}else if (pays.compareTo("Espagne")==0){
				indicatif = "ES";
			}
			accountNumber = indicatif + str;	
			// si le n° de compte existe deja recommencer peut etre avec un while
			return accountNumber;
	}





	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAccountNumber(){
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}





	public Double getSolde() {
		return solde;
	}





	public void setSolde(Double solde) {
		this.solde = solde;
	}





	public Customer getCustomer() {
		return customer;
	}





	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




}
