package control;



import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hsqldb.HsqlException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import entity.Consts;
import entity.Riddle;
import entity.Solution;
import entity.Transaction;
import net.ucanaccess.jdbc.UcanaccessSQLException;



public class ImportRiddles {
	private ArrayList<Riddle> riddles;
	private ArrayList<Solution> solutions;

	public boolean importSolutions() {
		solutions=new ArrayList<Solution>();
		try {
			File inputFile = new File(Consts.getPath("newSolutions.xml"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("solution");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Solution s = new Solution();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					s.setId(Integer.parseInt(eElement.getAttribute("id")));
					s.setRiddle(new Riddle(Integer.parseInt(eElement.getElementsByTagName("riddleId").item(0).getTextContent())));
					s.setSolution(eElement.getElementsByTagName("sol").item(0).getTextContent());
					solutions.add(s);
				}
			}

			for(Solution s : solutions)
			{
				if(!saveSolution(s))
					return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean importRiddles() {
		riddles=new ArrayList<Riddle>();
		try {
			File inputFile = new File(Consts.getPath("newRiddles.xml"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("riddle");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Riddle r = new Riddle();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					r.setId(Integer.parseInt(eElement.getAttribute("id")));
					r.setDiscription(eElement.getElementsByTagName("discription").item(0).getTextContent());
					riddles.add(r);
				}
			}

			for(Riddle r :riddles)
			{
				if(!saveRiddle(r))
					return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean saveRiddle(Riddle r) throws ParseException
	{
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_RIDDLES);

			Integer i=1;
			Date today = new Date();
			Timestamp time =  new Timestamp(today.getYear(), today.getMonth(), today.getDate(), today.getHours(), today.getMinutes(), 0, 0);
			Timestamp timeToSolve =  new Timestamp(today.getYear(), today.getMonth(), today.getDate()+2, today.getHours(), today.getMinutes(), 0, 0);
			
			stmt2.setInt(i++, r.getId());
			stmt2.setTimestamp(i++, time);
			stmt2.setString(i++, r.getDiscription());
			stmt2.setTimestamp(i++, timeToSolve);
			stmt2.setString(i++, "unsolved");
			stmt2.setString(i++, null);
			stmt2.setString(i++, null);


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

	public boolean saveSolution(Solution s){
		{
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt2 = 
						conn.prepareStatement(Consts.SQL_INS_SOLUTIONS);

				Integer i=1;
				
				stmt2.setInt(i++, s.getId());
				stmt2.setInt(i++, s.getRiddle().getId());
				stmt2.setString(i++, s.getSolution());       
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
}
