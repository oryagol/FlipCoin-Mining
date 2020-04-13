package entity;

import java.sql.Date;
import java.util.Calendar;

import Utils.Status;

public class Riddle {
	private Integer id;
	private Calendar publishDate;
	private String discription;
	private Calendar timeToSolve;
	private Status solvingStatus;
	private DifficultyLevel difficultyLevel;
	
	
	
	public Riddle(Integer id, Calendar publishDate, String discription, Calendar timeToSolve, Status solvingStatus,
			DifficultyLevel difficultyLevel) {
		super();
		this.id = id;
		this.publishDate = publishDate;
		this.discription = discription;
		this.timeToSolve = timeToSolve;
		this.solvingStatus = solvingStatus;
		this.difficultyLevel = difficultyLevel;
	}
	
	public Riddle(int id, Date publishDate, String discription, Date timeToSolve, String solvingStatus,
			String difficulty, int level) {
		super();
		this.id = id;
		this.publishDate = Calendar.getInstance();
		this.publishDate.setTime(publishDate);
		this.discription = discription;
		this.timeToSolve = Calendar.getInstance();
		this.timeToSolve.setTime(timeToSolve);
		if(solvingStatus.equals("solved"))
			this.solvingStatus = this.solvingStatus.SOLVED;
		else if(solvingStatus.equals("unsolved"))
			this.solvingStatus = this.solvingStatus.UNSOLVED;
		else
			this.solvingStatus = this.solvingStatus.IRRELEVANT;
		this.difficultyLevel = new DifficultyLevel(difficulty, level, null);
	}
	
	public Riddle(){
	
	}
	
	public Riddle(Integer id){
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Calendar publishDate) {
		this.publishDate = publishDate;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Calendar getTimeToSolve() {
		return timeToSolve;
	}

	public void setTimeToSolve(Calendar timeToSolve) {
		this.timeToSolve = timeToSolve;
	}

	public Status getSolvingStatus() {
		return solvingStatus;
	}

	public void setSolvingStatus(Status solvingStatus) {
		this.solvingStatus = solvingStatus;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	

	@Override
	public boolean equals(Object obj) {
		Riddle other = (Riddle) obj;
		if(this.id.equals(other.getId()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Riddle [id=" + id  + ", discription=" + discription   + "]";
	}


	

}
