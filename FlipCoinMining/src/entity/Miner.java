package entity;

public class Miner {

	private String address;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Double digitalProfit;
	private boolean isEmpolyee;
	
	public Miner(String address, String firstName, String lastName, String password, String email) {
		super();
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}
	
	
	public Miner(String address, String firstName, String lastName, String password, String email, boolean isEmployee) {
		super();
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.isEmpolyee = isEmployee;
	}
	
	public Miner(String address, String firstName, String lastName, String password, String email,Double digitalProfit, boolean isEmployee) {
		super();
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.digitalProfit = digitalProfit;
		this.isEmpolyee = isEmployee;
	}

	public Miner(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Miner other = (Miner) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}

	public boolean isEmpolyee() {
		return isEmpolyee;
	}

	public void setEmpolyee(boolean isEmpolyee) {
		this.isEmpolyee = isEmpolyee;
	}


	public Double getDigitalProfit() {
		return digitalProfit;
	}


	public void setDigitalProfit(Double digitalProfit) {
		this.digitalProfit = digitalProfit;
	}
	
	
	
	
	
	
}
