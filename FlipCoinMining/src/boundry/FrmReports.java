package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import control.MarketPrediction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class FrmReports  implements Initializable {
	
	@FXML
    private DatePicker startMonth;

    @FXML
    private DatePicker endMonth;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void dominantMinerReport()
	{
		
	}
	
	public void marketPredictionReport()
	{
		MarketPrediction m = new MarketPrediction();
		m.initiateMarketPredictionsReport();
	}

}
