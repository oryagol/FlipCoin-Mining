package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.hsqldb.HsqlException;

import entity.Benefit;
import entity.Consts;
import entity.Lottery;
import entity.LotteryMiner;
import entity.Miner;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class UserLottery {
	private ArrayList<LotteryMiner> l = new ArrayList<>();
	
	public void getLotteryMiner(){
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_LOTTRIES_AND_MINERS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    l.add(new LotteryMiner(new Lottery(rs.getInt(i++)), new Miner(rs.getString(i++)), rs.getBoolean(i++)));
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public ArrayList<Lottery> getLotteries(){
		ArrayList<Lottery> lotteries = new ArrayList<>();
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_LOTTERIES);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    lotteries.add(new Lottery(rs.getInt(i++),rs.getDate(i++), rs.getInt(i++),rs.getInt(i++),rs.getInt(i++)));
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 return lotteries;
	}
	
	public ArrayList<Lottery> getLotteriesFinished(){
		ArrayList<Lottery> lotteries = new ArrayList<>();
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_LOTTERIES_FINISHED);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    lotteries.add(new Lottery(rs.getInt(i++),rs.getDate(i++), rs.getInt(i++),rs.getInt(i++),rs.getInt(i++)));
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 return lotteries;
	}
	
	public HashSet<Lottery> getLotteryUnsigned(){
		getLotteryMiner();
		ArrayList<Lottery> allLotteries = new ArrayList<>();
		allLotteries = getLotteries();
		HashSet<Integer> toRet = new HashSet<>();
		HashMap<Integer, Lottery> lotById = new HashMap<>();
		HashSet<Lottery> lotteryToRet = new HashSet<>();
		for(Lottery lot : allLotteries) {
			toRet.add(lot.getLotteryId());
			lotById.put(lot.getLotteryId(), lot);
		}
		for(LotteryMiner lot : l) {
			if(lot.getMiner().getAddress().equals(LoginLogic.getMiner().getAddress())) {
				toRet.remove(lot.getLottery().getLotteryId());
			}
		}

		for(Integer i : toRet) {
			lotteryToRet.add(lotById.get(i));
		}

		return lotteryToRet;
	}
	
	public HashSet<Lottery> getLotterySigned(){
		ArrayList<Lottery> allLotteries = new ArrayList<>();
		allLotteries = getLotteries();
		HashMap<Integer, Lottery> lotById = new HashMap<>();
		HashSet<Lottery> toRet = new HashSet<>();
		for(Lottery lot : allLotteries) {
			lotById.put(lot.getLotteryId(), lot);
		}
		for(LotteryMiner m : l) {
			if(m.getMiner().getAddress().equals(LoginLogic.getMiner().getAddress()))
				toRet.add(lotById.get(m.getLottery().getLotteryId()));
		}

		return toRet;
	}
	
	public boolean signToLottery(int lotteryId) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_MINER_TO_LOTTERY);

			Integer i=1;
			
			stmt2.setInt(i++, lotteryId);
			stmt2.setString(i++, LoginLogic.getMiner().getAddress());

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
	
	
	public boolean cancelSignToLottery(int lotteryId) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_DLT_MINER_FROM_LOTTERY);

			Integer i=1;
			
			stmt2.setInt(i++, lotteryId);
			stmt2.setString(i++, LoginLogic.getMiner().getAddress());
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
	
	public HashSet<Lottery> getWonLotteries(){
		ArrayList<Lottery> allLotteries = new ArrayList<>();
		allLotteries = getLotteriesFinished();
		HashMap<Integer, Lottery> lotById = new HashMap<>();
		HashSet<Lottery> toRet = new HashSet<>();
		for(Lottery lot : allLotteries) {
			lotById.put(lot.getLotteryId(), lot);
		}
		for(LotteryMiner m : l) {
			if(m.getMiner().getAddress().equals(LoginLogic.getMiner().getAddress()) && m.isHasWon()) {
				toRet.add(lotById.get(m.getLottery().getLotteryId()));
			}
		}

		return toRet;
	}
	
	public String showLotteryBenefits(int lotteryId){
		ArrayList<Benefit> benefits = new ArrayList<>();
		String toRet = "";
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_LOTTERY_BENEFITS);
	                    stmt.setInt(1, lotteryId);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    benefits.add(new Benefit(rs.getString(i++), rs.getString(i++)));
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 int i = 1;
		 for(Benefit b : benefits) {
			 toRet+= i+". "+b.getDiscription()+" ";
			 i++;
					 
		 }
		 return toRet;
	}
}
