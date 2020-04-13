package Utils;


import entity.Transaction;

public class Combination {
	
	private Transaction first;
	private Transaction seconed;
	
	public Combination(Transaction first, Transaction seconed) {
		super();
		this.first = first;
		this.seconed = seconed;
	}
	public Transaction getFirst() {
		return first;
	}
	public void setFirst(Transaction first) {
		this.first = first;
	}
	public Transaction getSeconed() {
		return seconed;
	}
	public void setSeconed(Transaction seconed) {
		this.seconed = seconed;
	}
	
	public Double getSum() {
		return first.getFee()+seconed.getFee();
	}
	
	public String toString() {
		return first.getId()+","+seconed.getId();
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((seconed == null) ? 0 : seconed.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Combination c = (Combination) obj;
		if(this.getFirst().getId().equals(c.getFirst().getId()))
		{
			
			if(this.getSeconed().getId().equals(c.getSeconed().getId()))
				return true;
		}
		else if(this.getFirst().getId().equals(c.getSeconed().getId()))
		{
			if(this.getSeconed().getId().equals(c.getFirst().getId()))
				return true;
		}
		return false;
	}
	public Integer getSize() {
		return first.getSize()+seconed.getSize();
	}
	
	

}

