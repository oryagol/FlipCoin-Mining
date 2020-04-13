package entity;

public class Solution {
	
	private Riddle riddle;
	private Integer id;
	private String solution;
	
	public Solution(Integer id, Riddle riddle, String solution) {
		super();
		this.riddle = riddle;
		this.id = id;
		this.solution = solution;
	}
	
	public Solution() {

	}

	public Riddle getRiddle() {
		return riddle;
	}

	public void setRiddle(Riddle riddle) {
		this.riddle = riddle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (riddle == null) {
			if (other.riddle != null)
				return false;
		} else if (!riddle.equals(other.riddle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Solution [riddle=" + riddle + ", id=" + id + ", solution=" + solution + "]";
	}
	
	
	

}
