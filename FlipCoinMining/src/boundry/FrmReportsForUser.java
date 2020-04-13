package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import control.MarketPrediction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class FrmReportsForUser implements Initializable {
	@FXML
    private ComboBox<String> startMonth;

    @FXML
    private ComboBox<String> endMonth;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void marketPredictionReport()
	{
		MarketPrediction m = new MarketPrediction();
		m.initiateMarketPredictionsReport();
	}
}
