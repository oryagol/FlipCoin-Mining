package control;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import com.healthmarketscience.jackcess.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import entity.Consts;
import entity.Miner;

public class RegisterLogic {

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param phoneNum
	 * @param email
	 * @throws IOException 
	 */
	
	public Boolean register(String firstName,String lastName, String password, String verifyPass , String email)  {
		if(!(password.equals(verifyPass)))
			return false;
		
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt2 = 
                            conn.prepareStatement(Consts.SQL_INS_MINER);
                    
                    int i=1;
                    
               
                    stmt2.setString(i++, getAddress());
                    stmt2.setString(i++, firstName);
                    stmt2.setString(i++,lastName );
                    stmt2.setString(i++, password);
                    stmt2.setString(i++, email);
                    stmt2.setDouble(i++, 0);
                    
              
                    
                    stmt2.executeUpdate();
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	public Boolean registerBussines(String address,String phoneNum, String email,String firstName,String lastName) {
		
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt2 = 
                            conn.prepareStatement(Consts.SQL_INS_BUSINESS_MINER);
             
                    int i=1;
                    
               
                    stmt2.setString(i++, address);
                    stmt2.setString(i++, phoneNum);
                    stmt2.setString(i++, email);
                    stmt2.setString(i++, firstName);
                    stmt2.setString(i++,lastName );
                   
                   
                    
                    stmt2.executeUpdate();
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		return true;
	}

	public void reset() {
		// TODO - implement registerLogic.reset
		throw new UnsupportedOperationException();
	}
	
	public String getAddress() {
		ArrayList<Miner> miners = getMiners();
		ArrayList<String> adds = new ArrayList<String>();
		for(Miner m : miners) {
			adds.add(m.getAddress());
		}
		String toRet = new String();
		String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char a;
		Random rand = new Random();
		long n;
		for(int i = 0 ; i<4 ; i++) {
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
	
	
	
	public ArrayList<Miner> getMiners() {
		 ArrayList<Miner> users= new ArrayList<Miner>();
	        
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = 
	                            conn.prepareStatement(Consts.SQL_GET_ALL_MINERS);
	                    ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    int i = 1;
	                    users.add(new Miner(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
	                    		rs.getString(i++)));
	                    
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        return users;
	}

}