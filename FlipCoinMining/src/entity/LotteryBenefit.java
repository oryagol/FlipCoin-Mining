package entity;

public class LotteryBenefit {
	
	private Lottery lottery;
	private Benefit benefit;
	
	public LotteryBenefit(Lottery lottery, Benefit benefit) {
		super();
		this.lottery = lottery;
		this.benefit = benefit;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LotteryBenefit other = (LotteryBenefit) obj;
		if (benefit == null) {
			if (other.benefit != null)
				return false;
		} else if (!benefit.equals(other.benefit))
			return false;
		if (lottery == null) {
			if (other.lottery != null)
				return false;
		} else if (!lottery.equals(other.lottery))
			return false;
		return true;
	}
	
	
	
	
	

}
