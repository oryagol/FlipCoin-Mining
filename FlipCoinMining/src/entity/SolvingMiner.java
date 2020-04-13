package entity;

import java.util.Calendar;

public class SolvingMiner {
	
	private Miner miner;
	private Riddle riddle;
	private DifficultyLevel difficultyLevel;
	private Calendar registerTime;
	private Calendar solvingTime;
	private boolean isSolved;
	
	public SolvingMiner(Miner miner, Riddle riddle, DifficultyLevel difficultyLevel, Calendar registerTime,Calendar solvingTime, boolean isSolved) {
		super();
		this.miner = miner;
		this.riddle = riddle;
		this.difficultyLevel = difficultyLevel;
		this.solvingTime = solvingTime;
		this.isSolved = isSolved;
	}
	
	public SolvingMiner(Miner miner, Riddle riddle, boolean isSolved) {
		super();
		this.miner = miner;
		this.riddle = riddle;
		this.isSolved = isSolved;
	}

	public SolvingMiner(Miner miner, Riddle riddle) {
		super();
		this.miner = miner;
		this.riddle = riddle;
	}

	public Miner getMiner() {
		return miner;
	}

	public void setMiner(Miner miner) {
		this.miner = miner;
	}

	public Riddle getRiddle() {
		return riddle;
	}

	public void setRiddle(Riddle riddle) {
		this.riddle = riddle;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Calendar getSolvingTime() {
		return solvingTime;
	}

	public void setSolvingTime(Calendar solvingTime) {
		this.solvingTime = solvingTime;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolvingMiner other = (SolvingMiner) obj;
		if (difficultyLevel == null) {
			if (other.difficultyLevel != null)
				return false;
		} else if (!difficultyLevel.equals(other.difficultyLevel))
			return false;
		if (miner == null) {
			if (other.miner != null)
				return false;
		} else if (!miner.equals(other.miner))
			return false;
		if (riddle == null) {
			if (other.riddle != null)
				return false;
		} else if (!riddle.equals(other.riddle))
			return false;
		return true;
	}
	
	

}
