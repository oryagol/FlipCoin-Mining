package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Consts;
import entity.Miner;

public class LoginLogic {

	
	private HashMap<String, Miner> miners;
	
	private static Miner miner;

	
	public static Miner getMiner() {
		return miner;
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	

	public Miner verifyUser(String userName, String password) {
		miners = getMiners();
		Miner m = miners.get(userName);
		if(m==null)
			return null;
		if(m.getPassword().equals(password)) {
			miner = m;
			return m;
		}
		else
			return null;
	}
	
	public Miner forgotPasswordVerify(String userName,String email)
	{
		miners=getMiners();
		Miner u=miners.get(userName);
		if(u==null)
			return null;
		if(u.getEmail().equals(email))
			return u;
		return null;
		
		
	}
	

	public HashMap<String, Miner> getMiners() {
			 ArrayList<Miner> miners= new ArrayList<Miner>();
		        try {
		            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		            
		            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
		                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_MINERS);
		                    ResultSet rs = stmt.executeQuery();	               
		            		while (rs.next()) {
		                    int i = 1;
		                    miners.add(new Miner(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
		                    		rs.getString(i++) ,rs.getBoolean(i++)));
		                    
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		        HashMap<String,Miner> userVerify = new HashMap<String,Miner>();
		        for(Miner m : miners) {
		        	userVerify.put(m.getAddress(), m);
		        }
			return userVerify;
		}
	}
