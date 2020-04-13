package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

import org.hsqldb.HsqlException;

import Utils.Type;
import entity.Consts;
import entity.DifficultyLevel;
import entity.Riddle;
import entity.Transaction;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class UpdateLevel {
	private ArrayList<DifficultyLevel> levels = new ArrayList<>();
	
	public void getDifficultyLevels() {
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
	}
	
	public boolean isLevelInDB(DifficultyLevel d) {
		getDifficultyLevels();
		if(levels.contains(d))
			return true;
		else
			return false;
	}
	
	public boolean updateLevel(Riddle r,DifficultyLevel d) {
		getDifficultyLevels();
		if(!isLevelInDB(d))
			return false;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_RIDDLES);

			Integer i=1;

			stmt2.setString(i++, d.getDifficulty());
			stmt2.setInt(i++, d.getLevel());
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
	
	public boolean updateBlockSizeForLevel(DifficultyLevel d, Double blockSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_LEVEL);

			Integer i=1;
			
			stmt2.setDouble(i++, blockSize);
			stmt2.setString(i++, d.getDifficulty());
			stmt2.setInt(i++, d.getLevel());

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
	
	public boolean addLevel(DifficultyLevel d) {
		if(isLevelInDB(d))
			return false;
		 try {
	         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	         
	         try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                 PreparedStatement stmt2 = 
	                         conn.prepareStatement(Consts.SQL_INS_DIFFICULTY_LEVEL);
	          
	                 Integer i=1;
	                 
	                 stmt2.setString(i++, d.getDifficulty());
	                 stmt2.setInt(i++, d.getLevel());
	                 stmt2.setDouble(i++, d.getBlockSize());

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

}
