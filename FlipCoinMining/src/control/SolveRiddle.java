package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.hsqldb.HsqlException;

import boundry.Verificator;
import entity.Block;
import entity.Consts;
import entity.DifficultyLevel;
import entity.Miner;
import entity.Riddle;
import entity.Solution;
import entity.SolvingMiner;
import exceptions.AnswerException;
import exceptions.RegisterException;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class SolveRiddle {
	private ArrayList<DifficultyLevel>levels = new ArrayList<>();
	private ArrayList<Riddle> riddles = new ArrayList<>();

	public ArrayList<DifficultyLevel> getDifficultyLevels() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_DIFFICULTY_LEVELS);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;
				levels.add(new DifficultyLevel(rs.getString(i++), rs.getInt(i++), rs.getDouble(i++)));

			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return levels;
	}

	public ArrayList<Riddle> getRiddles() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_RIDDLES);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;

				riddles.add(new Riddle(rs.getInt(i++), rs.getDate(i++), rs.getString(i++),
						rs.getDate(i++), rs.getString(i++),rs.getString(i++), rs.getInt(i++)));


			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return riddles;
	}

	public ArrayList<SolvingMiner> getSolvingMiners(){
		ArrayList<SolvingMiner> solveMiners = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_SOLVING_MINER);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;

				solveMiners.add(new SolvingMiner(new Miner(rs.getString(i++)), new Riddle(rs.getInt(i++)), rs.getBoolean(i++)));


			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return solveMiners;
	}

	public ArrayList<Solution> getSolution(int riddleId){
		ArrayList<Solution> solutions = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_SOLUTIONS);
			stmt.setInt(1, riddleId);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;

				solutions.add(new Solution(rs.getInt(i++), new Riddle(rs.getInt(i++)), rs.getString(i++)));


			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return solutions;
	}


	public boolean registerToRiddle(Miner m, Riddle r) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_SOLVINGMINER);

			Integer i=1;

			Date today = new Date();
			Timestamp time =  new Timestamp(today.getYear(), today.getMonth(), today.getDate(), today.getHours(), today.getMinutes(), 0, 0);
			stmt2.setString(i++, m.getAddress());
			stmt2.setInt(i++, r.getId());
			stmt2.setString(i++, r.getDifficultyLevel().getDifficulty());
			stmt2.setInt(i++, r.getDifficultyLevel().getLevel());
			stmt2.setTimestamp(i++, time);

			stmt2.executeUpdate();
			return true;
			}catch(UcanaccessSQLException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(HsqlException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(SQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	public boolean isRegistered(String address, int riddleId) {
		ArrayList<SolvingMiner> registers = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_IS_REGISTERED);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;

				registers.add(new SolvingMiner(new Miner(rs.getString(i++)), new Riddle(rs.getInt(i++))));

			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		SolvingMiner check = new SolvingMiner(new Miner(address), new Riddle(riddleId));
		if(registers.contains(check))
			return true;
		else
			return false;
	}


	public boolean solveRiddle(Riddle r, String solution) throws RegisterException, AnswerException {
		if(!isRegistered(LoginLogic.getMiner().getAddress(), r.getId()))
			throw new RegisterException();
		ArrayList<Solution> solutions = new ArrayList<>();
		solutions = getSolution(r.getId());
		boolean isCorrect = false;
		for(Solution s : solutions) {
			if(s.getSolution().equals(solution))
				isCorrect = true;
		}
		if(isCorrect == false) {
			throw new AnswerException();
		}
		else {
			if(!changeRiddleStatus(r.getId()))
				return false;
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt2 = 
						conn.prepareStatement(Consts.SQL_UPD_CORRECT_SOLUTION);
				
				Integer i=1;

				Date today = new Date();
				Timestamp time =  new Timestamp(today.getYear(), today.getMonth(), today.getDate(), today.getHours(), today.getMinutes(), 0, 0);
				stmt2.setTimestamp(i++, time);
				stmt2.setString(i++, LoginLogic.getMiner().getAddress());
				stmt2.setInt(i++, r.getId());

				stmt2.executeUpdate();
				return true;
				}catch(UcanaccessSQLException e)
				{
					e.printStackTrace();
					return false;
				}
				catch(HsqlException e)
				{
					e.printStackTrace();
					return false;
				}
				catch(SQLIntegrityConstraintViolationException e)
				{
					e.printStackTrace();
					return false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}

	public boolean changeRiddleStatus(int riddleId) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_RIDDLE_SOLVED);

			Integer i=1;

			stmt2.setInt(i++, riddleId);

			stmt2.executeUpdate();
			return true;
			}catch(UcanaccessSQLException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(HsqlException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(SQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	public Date getRiddleFinishDate(int riddleId) {
		Date date = new Date();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_RIDDLE_TIME_LEFT);
			stmt.setInt(1, riddleId);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;
				date = rs.getDate(i++);
				


			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 

		return date;
	}
	
	public Block createBlock(DifficultyLevel d) {
		Calendar cal = Calendar.getInstance();
		Block b = new Block(getAddress(), cal, d.getBlockSize(), LoginLogic.getMiner());
		insertBlock(b);
		return b;
	}
	
	public boolean insertBlock(Block b) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_BLOCK);

			Integer i=1;
			Date today = new Date();
			Timestamp time =  new Timestamp(today.getYear(), today.getMonth(), today.getDate(), today.getHours(), today.getMinutes(), 0, 0);
			
			stmt2.setString(i++, b.getAddress());
			stmt2.setTimestamp(i++, time);
			stmt2.setDouble(i++, b.getSize());
			stmt2.setString(i++, null);
			stmt2.setString(i++, b.getMiner().getAddress());


			stmt2.executeUpdate();
			if(!updatePrevBlockAdd(b.getAddress()))
				return false;
			return true;
			}catch(UcanaccessSQLException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(HsqlException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(SQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				return false;
			}

			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updatePrevBlockAdd(String address) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_NEXT_BLOCK_ADD);

			Integer i=1;
			
			stmt2.setString(i++, address);
			stmt2.setString(i++, address);

			stmt2.executeUpdate();
			}catch(UcanaccessSQLException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(HsqlException e)
			{
				e.printStackTrace();
				return false;
			}
			catch(SQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	public String getAddress() {
		ArrayList<Block> blocks = getBlocks();
		ArrayList<String> adds = new ArrayList<String>();
		for(Block b : blocks) {
			adds.add(b.getAddress());
		}
		String toRet = new String();
		String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char a;
		Random rand = new Random();
		long n;
		for(int i = 0 ; i<6 ; i++) {
			n = rand.nextInt(9);
			a = english.charAt(rand.nextInt(english.length()));
			if(i%2==0) {
				toRet = toRet+a;
			}
			else {
				toRet = toRet+n;
			}
		}
		if(adds.contains(toRet)) {
			toRet = getAddress();
		}
		return toRet;
	}
	
	public ArrayList<Block> getBlocks(){
		ArrayList<Block> blocks = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_BLOCKS);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;

				blocks.add(new Block(rs.getString(i++), rs.getDate(i++), rs.getDouble(i++), new Miner(rs.getString(i++))));


			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return blocks;
	}


}
