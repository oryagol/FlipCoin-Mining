package entity;
import java.util.Calendar;
import java.util.Date;

import Utils.Type;

public class Transaction {

	private String id;
	private Type type;
	private Double fee;
	private Integer size;
	private Block block;
	private Calendar additionDate;
	
	
	public Transaction(String id, Type type, Double fee, Integer size, Block block, Calendar additionDate) {
		super();
		this.id = id;
		this.type = type;
		this.fee = fee;
		this.size = size;
		this.block = block;
		this.additionDate = additionDate;
	}
	
	public Transaction(String id, Type type, Double fee, Integer size, Block block, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.fee = fee;
		this.size = size;
		this.block = block;
		this.additionDate = Calendar.getInstance();
		this.additionDate.setTime(date);
	}
	
	
	public Transaction() {
		
	}

	public Transaction(String id) {
		this.id = id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public Double getFee() {
		return fee;
	}


	public void setFee(Double fee) {
		this.fee = fee;
	}


	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}


	public Block getBlock() {
		return block;
	}


	public void setBlock(Block block) {
		this.block = block;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	public Calendar getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Calendar additionDate) {
		this.additionDate = additionDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", type=" + type + ", fee=" + fee + ", size=" + size + "]";
	}
	
	
	
	
}
