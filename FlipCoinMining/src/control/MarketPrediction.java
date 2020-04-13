package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import entity.Consts;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class MarketPrediction {

	public void initiateMarketPredictionsReport() {
		JFrame myFrame = compileMarketPredictionReport();
		myFrame.setVisible(true);	
	}
	
	
	public JFrame compileMarketPredictionReport() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				JasperPrint print = JasperFillManager.fillReport(

						getClass().getResourceAsStream("../database/MarketPrediction.jasper"), 
						new HashMap<String, Object>(), conn);
				JFrame frame = new JFrame("Market Prediction");
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;

			}
			catch (SQLException | JRException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
