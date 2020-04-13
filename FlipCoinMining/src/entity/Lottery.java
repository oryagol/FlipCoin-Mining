package entity;

import java.sql.Date;
import java.util.Calendar;

public class Lottery {
	
	
	private Integer lotteryId;
	private Calendar date;
	private Integer maxMiners;
	private Integer numOfWinners;
	private Integer numOfBenefits;
	
	public Lottery(Integer lotteryId, Calendar date, Integer maxMiners, Integer numOfWinners, Integer numOfBenefits) {
		super();
		this.lotteryId = lotteryId;
		this.date = date;
		this.maxMiners = maxMiners;
		this.numOfWinners = numOfWinners;
		this.numOfBenefits = numOfBenefits;
	}

	public Lottery(int lotteryId, Date date, int maxMiners, int numOfWinners, int numOfBenefits) {
		super();
		this.lotteryId = lotteryId;
		this.date = Calendar.getInstance();
		this.date.setTime(date);
		this.maxMiners = maxMiners;
		this.numOfWinners = numOfWinners;
		this.numOfBenefits = numOfBenefits;
	}
	
	public Lottery(int lotteryId) {
		super();
		this.lotteryId = lotteryId;
	}

	public Integer getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Integer getMaxMiners() {
		return maxMiners;
	}

	public void setMaxMiners(Integer maxMiners) {
		this.maxMiners = maxMiners;
	}

	public Integer getNumOfWinners() {
		return numOfWinners;
	}

	public void setNumOfWinners(Integer numOfWinners) {
		this.numOfWinners = numOfWinners;
	}

	public Integer getNumOfBenefits() {
		return numOfBenefits;
	}

	public void setNumOfBenefits(Integer numOfBenefits) {
		this.numOfBenefits = numOfBenefits;
	}



	@Override
	public boolean equals(Object obj) {
		Lottery other = (Lottery) obj;
		if(this.lotteryId == other.getLotteryId())
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Lottery [lotteryId=" + lotteryId + "]";
	}
	
	
	
	

}
