package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.hsqldb.HsqlException;

import Utils.Type;
import entity.Benefit;
import entity.Consts;
import entity.Lottery;
import entity.Transaction;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class CreateLottery {
	private ArrayList<String> sNumbers;


	public boolean createLottery(Date date, Integer maxMiners, Integer numOfWinners, Integer numOfBenefits) {
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_LOTTERY);

			Integer i=1;
			
			Timestamp time =  new Timestamp(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), 0, 0);
			stmt2.setTimestamp(i++, time);
			stmt2.setInt(i++, maxMiners);
			stmt2.setInt(i++, numOfWinners);
			stmt2.setInt(i++, numOfBenefits);


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
		finally {

		}
		return true;
	}

	public boolean defineBenefits(String benefit) {
		Benefit b = new Benefit(getSerialNumber(), benefit);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_BENEFIT);

			Integer i=1;
			
			stmt2.setString(i++, b.getSerialNumber());
			stmt2.setString(i++, b.getDiscription());

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
	
	public String getSerialNumber() {
		getNumbersFromDB();
		String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		char a;
		int n, m;
		a = english.charAt(rand.nextInt(english.length()));
		String toRet = new String();
		toRet+= a;
		for(int i=0; i<3; i++) {
			n = rand.nextInt(9) + 1;
			toRet+=n;
		}
		if(sNumbers.contains(toRet))
			toRet = getSerialNumber();
		return toRet;
	}
	
	public void getNumbersFromDB(){
		sNumbers = new ArrayList<>();
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_BENEFIT_SERIALNUMBER);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                   sNumbers.add(rs.getString(i++));

	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	}

}
