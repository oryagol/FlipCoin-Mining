package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.hsqldb.HsqlException;

import entity.Benefit;
import entity.Block;
import entity.Consts;
import entity.Transaction;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class TransactionSelection {

	public boolean relateTransactionBlock(Transaction t,Block b) {


		String query= "UPDATE tblTransaction SET tblTransaction.BlockAddress ="+ "'"+b.getAddress()+"'"+  
				" WHERE tblTransaction.Id="+ "'"+ t.getId()+"'";
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(query);

			stmt2.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if(!updateDigitalProfit(t,getPrevProfit()))
			return false;

		return true;
	}
	
	public Double getPrevProfit() {
		Double prevFee=0.0;
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_MINER_PREV_PROFIT);
	                    stmt.setString(1, LoginLogic.getMiner().getAddress());
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    
	                    prevFee = rs.getDouble(i++);
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 return prevFee;
	}
	
	public boolean updateDigitalProfit(Transaction t, Double prevFee) {
		Double feeUpdate = prevFee+t.getFee();
		String user = LoginLogic.getMiner().getAddress();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_DIGITAL_PROFIT);

			Integer i=1;

			stmt2.setDouble(i++, feeUpdate);
			stmt2.setString(i++, user);
			
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
}