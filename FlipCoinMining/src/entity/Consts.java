package entity;

import java.net.URLDecoder;

public class Consts {
	public static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://"+DB_FILEPATH+";COLUMNORDER=DISPLAY";
	
		
	//insert queries
	  public static final String SQL_INS_MINER ="INSERT INTO  tblMiner( Address,FirstName, LastName, Password, Email,DigitalProfit)\n" +
			  "VALUES(?,?,?,?,?,?);";
	 
	  public static final String SQL_INS_BUSINESS_MINER ="INSERT INTO  tblBusinessMiner( Address,ContactPhone, ContactEmail, ContactFirstName, ContactLastName)\n" +
			  "VALUES(?,?,?,?,?);";
	  public static final String SQL_INS_TRANSACTIONS ="INSERT INTO tblTransaction (Id,Type,Fee,Size,BlockAddress,AdditionDate)\n"+ "VALUES (?,?,?,?,?,?);";
	  public static final String SQL_INS_RIDDLES ="INSERT INTO tblRiddle (Id,PublishDate,Discription,TimeToSolve,SolvingStatus, Difficulty, Level)\n"+ "VALUES (?,?,?,?,?,?,?);";
	  public static final String SQL_INS_SOLUTIONS ="INSERT INTO tblSolution (Id,RiddleId,Solution)\n"+ "VALUES (?,?,?);";
	  public static final String SQL_INS_DIFFICULTY_LEVEL = "INSERT INTO tblDifficultyLevel (Difficulty, Level, BlockSize)\n"+"VALUES (?,?,?);";
	  public static final String SQL_INS_LOTTERY = "INSERT INTO tblLottery (date, maxMiners, numOfWinners, numOfBenefits)\n"+"VALUES (?,?,?,?);";
	  public static final String SQL_INS_BENEFIT = "INSERT INTO tblBenefit (serialNumber, discription)\n"+"VALUES (?,?);";
	  public static final String SQL_INS_LOTBENMIN = "INSERT INTO tblLotteryBenefitMiner (LotteryId, SerialNumber, MinerAddress)\n"+"VALUES (?,?,?);";
	  public static final String SQL_INS_SOLVINGMINER =  "INSERT INTO tblSolvingMiner (MinerAddress, RiddleId, Difficulty, Level, RegisterTime)\n"+"VALUES (?,?,?,?,?);";
	  public static final String SQL_INS_BLOCK = "INSERT INTO tblBlock (Address, DateOfCreation, Size, NextBlockAddress, MinerAddress)\n"+"VALUES(?,?,?,?,?);";
	  public static final String SQL_INS_MINER_TO_LOTTERY = "INSERT INTO tblLotteryMiner (LotteryId, MinerAddress)\n"+"VALUES(?,?);";
	  
	  //selection queries
	  public static final String SQL_GET_CHOSEN_TRANSACTIONS = "SELECT tblTransaction.Id, tblTransaction.Type, tblTransaction.Fee,tblTransaction.Size, tblTransaction.AdditionDate\n"
				 + "FROM tblTransaction WHERE tblTransaction.BlockAddress IS NOT NULL ;";
	  public static final String SQL_GET_COMB_TRANSACTIONS = "SELECT tblTransaction.Id, tblTransaction.Type, tblTransaction.Fee,tblTransaction.Size, tblTransaction.AdditionDate\n"
				 + "FROM tblTransaction WHERE tblTransaction.BlockAddress IS NULL ;";
	  public static final String SQL_GET_ALL_MINERS= "SELECT tblMiner.Address, tblMiner.FirstName, tblMiner.LastName , tblMiner.Password,tblMiner.Email, tblMiner.isEmployee\n"
			 + "FROM tblMiner;";
	  public static final String SQL_GET_ALL_BUSINESS_MINERS="SELECT tblBusinessMiner.Address,tblBusinessMiner.ContactPhone,tblBusinessMiner.ContactEmail,tblBusinessMiner.ContactFirstName,tblBusinessMiner.ContactLastName\n"+"FROM tblBusinessMiner;";
	  public static final String SQL_GET_RELEVENT_BLOCKS="SELECT tblBlock.Address,tblBlock.DateOfCreation,tblBlock.Size,tblBlock.NextBlockAddress,MinerAddress\n"+"FROM tblBlock\n WHERE tblBlock.MinerAddress = ?;";
	  public static final String SQL_GET_ALL_BLOCKS="SELECT tblBlock.Address,tblBlock.DateOfCreation,tblBlock.Size,tblBlock.NextBlockAddress,MinerAddress\n"+"FROM tblBlock;";
	  public static final String SQL_GET_ALL_DIFFICULTY_LEVELS = "SELECT tblDifficultyLevel.Difficulty, tblDifficultyLevel.Level, tblDifficultyLevel.BlockSize\n "
	  		+ "FROM tblDifficultyLevel;";
	  public static final String SQL_GET_ALL_LOTTERIES = "SELECT tblLottery.Id, tblLottery.Date, tblLottery.MaxMiners, tblLottery.NumOfWinners, tblLottery.NumOfBenefits\n"+"FROM tblLottery\n"
	  		+ "WHERE tblLottery.isFinished = false;";
	  public static final String SQL_GET_LOTTERY_PARTICIPENTS = "SELECT tblMiner.Address, tblMiner.FirstName, tblMiner.LastName, tblMiner.Password, tblMiner.Email\n"+
	  "FROM tblMiner INNER JOIN tblLotteryMiner ON tblMiner.Address = tblLotteryMiner.MinerAddress WHERE tblLotteryMiner.LotteryId = ?;";
	  public static final String SQL_GET_BENEFIT_SERIALNUMBER = "SELECT tblBenefit.SerialNumber\n FROM tblBenefit";
	  public static final String SQL_GET_BENEFIT = "SELECT tblBenefit.SerialNumber, tblBenefit.Discription\n FROM tblBenefit";
	  public static final String SQL_GET_RIDDLES = "SELECT tblRiddle.Id, tblRiddle.PublishDate, tblRiddle.Discription, tblRiddle.TimeToSolve, tblRiddle.SolvingStatus, "
	  		+ "tblRiddle.Difficulty, tblRiddle.Level\n"+"FROM tblRiddle WHERE tblRiddle.SolvingStatus != 'irrelevent';";
	  public static final String SQL_GET_RIDDLES_NOT_SOLVED = "SELECT tblRiddle.Id, tblRiddle.PublishDate, tblRiddle.Discription, tblRiddle.TimeToSolve, tblRiddle.SolvingStatus, "
		  		+ "tblRiddle.Difficulty, tblRiddle.Level\n"+"FROM tblRiddle WHERE tblRiddle.SolvingStatus != 'solved';";
	  public static final String SQL_GET_SOLVING_MINER = "SELECT tblSolvingMiner.MinerAddress, tblSolvingMiner.RiddleId, tblSolvingMiner.IsSolved\n"+
	  		"FROM tblSolvingMiner;";
	  public static final String SQL_GET_SOLUTIONS = "SELECT tblSolution.Id, tblSolution.RiddleId, tblSolution.Solution\n FROM tblSolution\n"
	  		+ "WHERE tblSolution.RiddleId = ?;";
	  public static final String SQL_IS_REGISTERED = "SELECT tblSolvingMiner.MinerAddress, tblSolvingMiner.RiddleId\n"
	  		+ "FROM tblSolvingMiner;";
	  public static final String SQL_GET_RIDDLE_TIME_LEFT = "SELECT tblRiddle.TimeToSolve\n FROM tblRiddle\n WHERE tblRiddle.Id = ?;";
	  public static final String SQL_GET_MINER_PREV_PROFIT = "SELECT tblMiner.DigitalProfit\n FROM tblMiner\n WHERE tblMiner.Address = ?;";
	  public static final String SQL_GET_OTHER_MINERS= "SELECT tblMiner.Address, tblMiner.FirstName, tblMiner.LastName , tblMiner.Password,tblMiner.Email,tblMiner.DigitalProfit, tblMiner.isEmployee\n"
				 + "FROM tblMiner\n WHERE NOT tblMiner.Address = ?;";
	  public static final String SQL_GET_LOTTRIES_AND_MINERS = "SELECT tblLotteryMiner.LotteryId, tblLotteryMiner.MinerAddress, tblLotteryMiner.HasWon\n"
	  		+ "FROM tblLotteryMiner;";
	  public static final String SQL_GET_ALL_LOTTERIES_FINISHED = "SELECT tblLottery.Id, tblLottery.Date, tblLottery.MaxMiners, tblLottery.NumOfWinners, tblLottery.NumOfBenefits\n"+"FROM tblLottery\n"
		  		+ "WHERE tblLottery.isFinished = True;";
	  public static final String SQL_GET_LOTTERY_BENEFITS = "SELECT tblBenefit.SerialNumber, tblBenefit.Discription \n"+
			  "FROM tblBenefit INNER JOIN tblLotteryBenefitMiner ON tblBenefit.SerialNumber = tblLotteryBenefitMiner.SerialNumber \n"
			  +"WHERE tblLotteryBenefitMiner.LotteryId = ?;";
	  
	  //update queries
	  public static final String SQL_UPD_RIDDLES = "UPDATE tblRiddle SET Difficulty = ?, Level = ? WHERE tblRiddle.Id= ?\n";
	  public static final String SQL_UPD_WINNERS = "UPDATE tblLotteryMiner SET tblLotteryMiner.HasWon = True\n"+"WHERE tblLotteryMiner.MinerAddress = ?;";
	  public static final String SQL_UPD_LEVEL = "UPDATE tblDifficultyLevel SET tblDifficultyLevel.BlockSize = ?"
	  		+ "\n WHERE tblDifficultyLevel.Difficulty = ? AND tblDifficultyLevel.level = ?;";
	  public static final String SQL_UPD_LOTTERY = "UPDATE tblLottery SET tblLottery.isFinished = true\n"+
			  "WHERE tblLottery.Id = ?;";
	  public static final String SQL_UPD_CORRECT_SOLUTION = "UPDATE tblSolvingMiner SET tblSolvingMiner.SolvingTime = ?,tblSolvingMiner.isSolved = True\n"
			  +"WHERE tblSolvingMiner.MinerAddress = ? AND tblSolvingMiner.RiddleId = ?;";
	  public static final String SQL_UPD_RIDDLE_SOLVED = "UPDATE tblRiddle SET tblRiddle.SolvingStatus = 'solved'\n "
	  		+ "WHERE tblRiddle.Id = ?;";
	  public static final String SQL_UPD_NEXT_BLOCK_ADD = "UPDATE tblBlock SET tblBlock.NextBlockAddress = ?\n"
	  		+ "WHERE tblBlock.Address != ? AND tblBlock.NextBlockAddress IS NULL";
	  public static final String SQL_UPD_DIGITAL_PROFIT = "UPDATE tblMiner SET tblMiner.DigitalProfit = ?\n"
	  		+ "WHERE tblMiner.Address = ?;";
	  public static final String SQL_UPD_IRRELEVENT_RIDDLE = "UPDATE tblRiddle SET tblRiddle.SolvingStatus = 'irrelevent'\n"
	  		+ "WHERE tblRiddle.Id = ?;";
			  
	  //delete queries
	  public static final String SQL_DLT_BENEFIT = "DELETE FROM tblBenefit\n WHERE tblBenefit.SerialNumber = ?;";
	  public static final String SQL_DLT_DIFFICULTY = "DELETE FROM tblDifficultyLevel\n WHERE tblDifficultyLevel.Difficulty = ?"
			  +"AND tblDifficultyLevel.Level = ?;";
	  public static final String SQL_DLT_LOTTERY = "DELETE FROM tblLottery\n WHERE tblLottery.Id = ?;";
	  public static final String SQL_DLT_RIDDLE = "DELETE FROM tblRiddle\n WHERE tblRiddle.Id = ?;";
	  public static final String SQL_DLT_MINER_FROM_LOTTERY = "DELETE FROM tblLotteryMiner\n WHERE tblLotteryMiner.LotteryId = ? AND"
	  		+ " tblLotteryMiner.MinerAddress = ?";
	  
	  
	private static String getDBPath() {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf('/'));
		return decoded + "/database/FlipCoinMining.accdb";
		} else {
		 decoded = decoded.substring(0, decoded.lastIndexOf("FlipCoinMining/"));
		return decoded + "FlipCoinMining/src/entity/FlipCoinMining.accdb";
		}
		} catch (Exception e) {
		 e.printStackTrace();
		 return null;
		 
		}
		}
	
	public static String getPath(String s) {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf("/"))+"/"+s;
		return decoded;
		} else {
		 return s;
		}
		} catch (Exception e) {
		 e.printStackTrace();
		 return null;
		 
		}
		}

}
