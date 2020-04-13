package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Utils.Combination;
import Utils.Type;
import entity.Block;
import entity.Consts;
import entity.Miner;
import entity.Transaction;
import javafx.util.Pair;

public class CombinationCreator {
	private ArrayList<Combination> combinations;
	private ArrayList<Transaction> transactions;
	private Block block;

	
	public ArrayList<Transaction> getTransactions() {
		transactions = new ArrayList<>();
		 try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_COMB_TRANSACTIONS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	                    int i = 1;
	                    transactions.add(new Transaction(rs.getString(i++), ((rs.getString(i++).equals("confirm")) ? Type.CONFIRM : Type.PAY), rs.getDouble(i++), rs.getInt(i++),
	                    		null,rs.getDate(i++)));
	                 
	                }
	            		
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 return transactions;
		
	}
	
	
	public void setBlock(Block block) {
		this.block = block;
	}

	public ArrayList<Block> getReleventBlocks(String address)
	{
		ArrayList<Block> blocks=new ArrayList<Block>();
		Calendar c=Calendar.getInstance();
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
           
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_RELEVENT_BLOCKS);
                    stmt.setString(1, address);
                    ResultSet rs = stmt.executeQuery();	               
            		while (rs.next()) {
                    int i = 3;
                    c.setTime(rs.getDate(2));
                    blocks.add(new Block(rs.getString(1), c, rs.getDouble(i++),new Miner(rs.getString(i++))));
                    
                }
            		
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		for(int i = 0; i<blocks.size();i++) {
			if(!blocks.get(i).getMiner().equals(LoginLogic.getMiner()))
					blocks.remove(blocks.get(i));
		}
		
		return blocks;
	}

	public ArrayList<Combination> getCombinations()
	{
		combinations = new ArrayList<Combination>();
		transactions=getTransactions();
		for(Transaction t:transactions)
		{
			for(int i=0;i<transactions.size();i++) {
				if(t.equals(transactions.get(i)))
					continue;
			Combination c = new Combination(t, transactions.get(i));
		
			 if(combinations.contains(c) == false && block.getSize()>=c.getSize())
				 combinations.add(c);
			}
		}
		return combinations;
	}


}
