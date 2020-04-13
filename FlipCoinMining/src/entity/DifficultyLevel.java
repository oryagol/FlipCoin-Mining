package entity;

public class DifficultyLevel {
	private String difficulty;
	private Integer level;
	private Double blockSize;
	
	public DifficultyLevel(String difficulty, Integer level, Double blockSize) {
		super();
		this.difficulty = difficulty;
		this.level = level;
		this.blockSize = blockSize;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(Double blockSize) {
		this.blockSize = blockSize;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DifficultyLevel other = (DifficultyLevel) obj;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DifficultyLevel [difficulty=" + difficulty + ", level=" + level + ", blockSize=" + blockSize + "]";
	}
	
	
	

}
