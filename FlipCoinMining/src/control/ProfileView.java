package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Consts;
import entity.Miner;

public class ProfileView {
	
	
	public ArrayList<Miner> getOtherMiners(String address){
		 ArrayList<Miner> miners= new ArrayList<Miner>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_OTHER_MINERS);
	                    stmt.setString(1, address);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    miners.add(new Miner(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
	                    		rs.getString(i++),rs.getDouble(i++) ,rs.getBoolean(i++)));
	                    
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return miners;
	}
	
	public String organizeProfileDate(Miner m) {
		String profile = "";
		profile+= "Address: "+m.getAddress()+"\n\n";
		profile+= "First Name: "+m.getFirstName()+"\n\n";
		profile+= "Last Name: "+m.getLastName()+"\n\n";
		profile+= "Email: "+m.getEmail()+"\n\n";
		profile+= "Digital Profit: "+m.getDigitalProfit()+" BTC"+"\n";
		
		return profile;
		
	}

}
