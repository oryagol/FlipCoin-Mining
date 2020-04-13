package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hsqldb.HsqlException;

import entity.Consts;
import entity.Riddle;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class UpdateIrreleventStatus {
	private ArrayList<Riddle> riddles = new ArrayList<>();

	public void getRiddles() {
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
	}

	public boolean updateIrreleventRiddles() {
		getRiddles();
		ArrayList<Riddle> irRiddles = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Riddle r : riddles) {
			if(r.getTimeToSolve().compareTo(cal) == -1)
				irRiddles.add(r);
		}
		for(Riddle r : irRiddles) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt2 = 
						conn.prepareStatement(Consts.SQL_UPD_IRRELEVENT_RIDDLE);

				Integer i=1;

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
		return true;
	}
}
