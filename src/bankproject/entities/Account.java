package bankproject.entities;

public class Account extends BankEntity {
	private Integer id;
	private String accountNumber;
	private String lastName;
	private String firstName;
	private Double solde;
	private PaysEnum pays; 
	
	
	
	public String getAccountNumber (){
			int numeroAleatoire = (int) ((Math.random()*(999999-111111)+111111));
			String str = String.valueOf(numeroAleatoire);
			String indicatif = null;
			if (pays == PaysEnum.FRANCE){
				indicatif = "FR";
			}else if (pays == PaysEnum.PAYS_BAS){
				indicatif = "NL";
			}else if (pays == PaysEnum.GRANDE_BRETAGNE){
				indicatif = "GB";
			}else if (pays == PaysEnum.ALLEMAGNE){
				indicatif = "DE";
			}else if (pays == PaysEnum.ESPAGNE){
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



	public String getLastName() {
		return lastName;
	}



public void setLastName(String lastName) {
	this.lastName = lastName;
}



public String getFirstName() {
	return firstName;
}



public void setFirstName(String firstName) {
	this.firstName = firstName;
}



public Double getSolde() {
	return solde;
}



public void setSolde(Double solde) {
	this.solde = solde;
}



public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

}
