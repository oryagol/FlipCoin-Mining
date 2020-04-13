package entity;

public class LotteryMiner {
	
	private Lottery lottery;
	private Miner miner;
	private boolean hasWon;
	
	public LotteryMiner(Lottery lottery, Miner miner, boolean hasWon) {
		super();
		this.lottery = lottery;
		this.miner = miner;
		this.hasWon = hasWon;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public Miner getMiner() {
		return miner;
	}

	public void setMiner(Miner miner) {
		this.miner = miner;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LotteryMiner other = (LotteryMiner) obj;
		if (lottery == null) {
			if (other.lottery != null)
				return false;
		} else if (!lottery.equals(other.lottery))
			return false;
		if (miner == null) {
			if (other.miner != null)
				return false;
		} else if (!miner.equals(other.miner))
			return false;
		return true;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	@Override
	public String toString() {
		return "LotteryMiner [lottery=" + lottery + ", miner=" + miner + ", hasWon=" + hasWon + "]";
	}
	
	
	
	

}
