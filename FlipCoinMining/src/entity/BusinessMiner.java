package entity;

public class BusinessMiner extends Miner {
	
	private String contactFirstName;
	private String contactLastName;
	private String contactPhone;
	private String contactEmail;
	
	
	public BusinessMiner(String address, String firstName, String lastName,String password, String email, String contactFirstName,
			String contactLastName, String contactPhone, String contactEmail) {
		super(address, firstName, lastName,password, email, false);
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}


	public String getContactFirstName() {
		return contactFirstName;
	}


	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}


	public String getContactLastName() {
		return contactLastName;
	}


	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}


	public String getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	

}
