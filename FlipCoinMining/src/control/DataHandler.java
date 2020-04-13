package control;

import Utils.Type;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hsqldb.HsqlException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Consts;

import entity.Transaction;

import net.ucanaccess.jdbc.UcanaccessSQLException;


public class DataHandler {
	private ArrayList<Transaction> transcations;
	private ArrayList<Transaction> chosenTrans;
	private boolean tExist = false;

	public boolean importTransactions(){
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(Consts.getPath("../WaitingTransactions.json")))
		{

			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray transList = (JSONArray) obj;


			//Iterate over tran array
			transList.forEach( tran ->	parseTranObject((JSONObject) tran ));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	private void parseTranObject(JSONObject tran)
	{
		tExist = false;
		//Get tran object within list
		JSONObject tranObject = (JSONObject) tran.get("transaction");
		String id = (String) tranObject.get("id");   
		String type = (String) tranObject.get("type"); 
		Long temp = (Long) tranObject.get("size");   
		Integer size = temp.intValue();
		Double fee = (Double) tranObject.get("fee");
		Calendar cal = Calendar.getInstance();
		Transaction t = new Transaction(id, ((type.equals("confirm")) ? Type.CONFIRM : Type.PAY), fee, size,null,cal);
		if(!saveTransaction(t))
			tExist = true;

	}


	public boolean saveTransaction(Transaction t)
	{
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt2 = 
					conn.prepareStatement(Consts.SQL_INS_TRANSACTIONS);

			Integer i=1;

			Date date = new Date();
			Timestamp time =  new Timestamp(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), 0, 0);
			stmt2.setString(i++, t.getId());
			stmt2.setString(i++, t.getType().toString().toLowerCase());
			stmt2.setDouble(i++,t.getFee() );
			stmt2.setInt(i++, t.getSize());
			stmt2.setString(i++,null);
			stmt2.setTimestamp(i++, time);



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


	public void exportTransactions() {
		chosenTrans=new ArrayList<Transaction>();
		chosenTrans = getChosenTransactions();



		try {
			DocumentBuilderFactory dbFactory =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("transactions");
			doc.appendChild(rootElement);
			for(int i=0;i<chosenTrans.size();i++)
			{

				// transaction element
				Element transaction = doc.createElement("transaction");
				rootElement.appendChild(transaction);

				// setting attribute to element
				Attr attr = doc.createAttribute("id");
				attr.setValue(chosenTrans.get(i).getId());
				transaction.setAttributeNode(attr);






				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(chosenTrans.get(i).getType().toString()));
				transaction.appendChild(type);

				Element size = doc.createElement("size");
				size.appendChild(doc.createTextNode(chosenTrans.get(i).getSize().toString()));
				transaction.appendChild(size);

				Element fee = doc.createElement("fee");
				fee.appendChild(doc.createTextNode(chosenTrans.get(i).getFee().toString()));
				transaction.appendChild(fee);



				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(Consts.getPath("../ChosenTransactions.xml")));
				transformer.transform(source, result);

				// Output to console for testing
				StreamResult consoleResult = new StreamResult(System.out);
				transformer.transform(source, consoleResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public ArrayList<Transaction> getChosenTransactions() {
		ArrayList<Transaction> transactions= new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_CHOSEN_TRANSACTIONS);
			ResultSet rs = stmt.executeQuery();	               
			while (rs.next()) {
				int i = 1;
				transactions.add(new Transaction(rs.getString(i++), ((rs.getString(i++).equals("confirm")) ? Type.CONFIRM : Type.PAY), rs.getDouble(i++), rs.getInt(i++),
						null,rs.getDate(i++)));

			}
			return transactions;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}


	public boolean istExist() {
		return tExist;
	}


	public void settExist(boolean tExist) {
		this.tExist = tExist;
	}




}
