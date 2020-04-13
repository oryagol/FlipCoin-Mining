package entity;

import java.sql.Date;
import java.util.Calendar;

public class Block {
	
	private String address;
	private Calendar dateOfCreation;
	private Double size;
	private Block nextBlock;
	private Miner miner;
	
	public Block(String address, Calendar dateOfCreation, Double size, Miner miner) {
		super();
		this.address = address;
		this.dateOfCreation = dateOfCreation;
		this.size = size;
		this.miner = miner;
	}
	
	public Block(String address)
	{
		this.address=address;
	}

	public Block(String address, Date dateOfCreation, Double size, Miner miner) {
		super();
		this.address = address;
		this.dateOfCreation = Calendar.getInstance();
		this.dateOfCreation.setTime(dateOfCreation);
		this.size = size;
		this.miner = miner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Calendar dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Block getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}

	public Miner getMiner() {
		return miner;
	}

	public void setMiner(Miner miner) {
		this.miner = miner;
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
		Block other = (Block) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Block [address=" + address + ", dateOfCreation=" + dateOfCreation + ", size=" + size + ", nextBlock="
				+ nextBlock + ", miner=" + miner + "]";
	}
	
	
	
	
	
	

}
