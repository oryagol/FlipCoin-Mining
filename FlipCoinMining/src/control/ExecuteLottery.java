package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hsqldb.HsqlException;

import Utils.Type;
import boundry.Verificator;
import entity.Benefit;
import entity.Consts;
import entity.Lottery;
import entity.Miner;
import entity.Transaction;
import exceptions.ParticipentsException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.ucanaccess.jdbc.UcanaccessSQLException;

public class ExecuteLottery {
	private ArrayList<Lottery> lotteries;

	public String executeLottery(Lottery l) throws ParticipentsException {
		ArrayList<Miner> participents = new ArrayList<>();
		ArrayList<Benefit> benefits =  new ArrayList<>();
		benefits = getBenefits();
		participents = getParticipents(l.getLotteryId());
		if(participents.size() == 0)
			throw new ParticipentsException();
		HashMap<Integer, Miner> parById = new HashMap<>();
		HashMap<Integer,Benefit> benByNum = new HashMap<>();
		int i = 1;
		for(Miner m : participents) {
			parById.put(i++, m);
		}
		i=1;
		for(Benefit b : benefits) {
			benByNum.put(i++, b);
		}
		ArrayList<Miner> winners = new ArrayList<>();
		ArrayList<Benefit> benOfLottery =  new ArrayList<>();
		int n;
		System.out.println(participents.size());
		for(int j=0;j<l.getNumOfWinners();) {
			n = getWinner(participents.size());
			if(!winners.contains(parById.get(n))) {   //checks if we already got this winner
				winners.add(parById.get(n));
				j++;
			}
		}
		for(int j=0;j<l.getNumOfBenefits();) {
			n = getWinner(benefits.size());
			if(!benOfLottery.contains(benByNum.get(n))) {
				benOfLottery.add(benByNum.get(n)); //checks if we already got this benefit
				j++;
			}
		}
		String win = new String();
		for(Miner m : winners) {
			win+= "Congratulations to "+m.getFirstName()+" "+m.getLastName()+" "+m.getEmail()+"!\n";
			updateWinners(m.getAddress());
			for(int z=0;z<benOfLottery.size();z++) {
				updateBenefitForWinner(l, benOfLottery.get(z), m);
			}
		}
		win+= "They won the benefits: ";
		for(int z=0; z<benOfLottery.size();z++)
			if(z == 0)
				win+=benOfLottery.get(z).getDiscription();
			else
				win+=" ,"+benOfLottery.get(z).getDiscription();
		updateFinishedLottery(l.getLotteryId());
		int a = 0;
		for(Miner m : winners) {
			if(sendEmail(l.getLotteryId(), m, benOfLottery))
				a++;
		}
		if(a == winners.size())
		{
			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setContentText("a Messege Has Been Sent To All Of The Winners");
			al.setTitle("System Message");
			al.setResizable(false);
			al.showAndWait();
		}
		return win;
	}

	public ArrayList<Lottery> getLotteries(){
		lotteries = new ArrayList<>();
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

	public boolean updateFinishedLottery(Integer lotteryId) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_LOTTERY);

			Integer i=1;
			stmt2.setInt(i++, lotteryId);

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

	public ArrayList<Benefit> getBenefits(){
		ArrayList<Benefit> benefits = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_BENEFIT);
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
		return benefits;
	}
	public boolean sendEmail(int lotteryId, Miner m, ArrayList<Benefit> benefits) {
		// Recipient's email ID needs to be mentioned.
		String to = m.getEmail();

		// Sender's email ID needs to be mentioned
		String from = "flipcoinappteam@gmail.com";


		// Get system properties
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		// Get the default Session object.

		//Session session = Session.getDefaultInstance(props);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("flipcoinappteam@gmail.com","kursizuv10");
			}
		});

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("You have Won Lottery Number "+lotteryId);

			String mes = "Dear "+m.getFirstName()+", We Are Glad To Tell You That You Have Won Lottery Number "+lotteryId+
					" And The Benefits are: ";
			int i = 0;
			for(Benefit b : benefits) {
				if(i == 0)
					mes+= " "+b.getDiscription();
				else
					mes+= " ,"+b.getDiscription();
				i++;
			}
			mes+= "\nYours Dearly, FlipCoinApp Team.";
			// Now set the actual message
			message.setText(mes);

			Transport.send(message);
			System.out.println("Sent message successfully....");

		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
		catch (NoClassDefFoundError e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public boolean updateBenefitForWinner(Lottery l, Benefit b, Miner m) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_LOTBENMIN);

			Integer i=1;
			stmt2.setInt(i++, l.getLotteryId());
			stmt2.setString(i++, b.getSerialNumber());
			stmt2.setString(i++, m.getAddress());
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

	public boolean updateWinners(String minerAdd) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_UPD_WINNERS);

			Integer i=1;
			stmt2.setString(i++, minerAdd);
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

	public ArrayList<Miner> getParticipents(Integer lotteryId){
		ArrayList<Miner> miners = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_LOTTERY_PARTICIPENTS);
			stmt.setInt(1, lotteryId);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;
				miners.add(new Miner(rs.getString(i++), rs.getString(i++), rs.getString(i++),rs.getString(i++),rs.getString(i++)));
			}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return miners;
	}

	public Integer getWinner(int size) {
		Random rand = new Random();
		return rand.nextInt(size)+1;
	}

}
