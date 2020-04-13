package boundry;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;



import control.CreateLottery;
import control.ExecuteLottery;
import entity.Lottery;
import exceptions.ParticipentsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateTimeStringConverter;

public class FrmLotteryEmployee implements Initializable {
	@FXML
    private TextArea lotteryWinners;

    @FXML
    private TextField lotteryBenefitDiscription;

	@FXML
	private TextField lotteryMaxMiner;

	@FXML
	private TextField LotteryNumOfWinners;

	@FXML
	private TextField lotteryBenefits;

	@FXML
	private ComboBox<String> lotteries;

	private CreateLottery c;
	
	private ExecuteLottery e;
	
	private ArrayList<Lottery> lotteriesArray;
	
	private HashMap<Integer, Lottery> lotteryById;
	
	@FXML
    private DatePicker dateOfLottery;

    @FXML
    private TextField hourOfLottery;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		e = new ExecuteLottery();
		lotteriesArray = new ArrayList<>();
		ObservableList<String> lList=FXCollections.observableArrayList();
		lotteriesArray.addAll(e.getLotteries());
		for(Lottery l : lotteriesArray) {
			lList.add(l.getLotteryId().toString());
		}
		lotteries.setItems(lList);
		lotteryById = new HashMap<>();
		for(Lottery l:lotteriesArray) {
			lotteryById.put(l.getLotteryId(), l);
		}

	}

	public void createLottery() {
		c = new CreateLottery();
		LocalDate d = dateOfLottery.getValue();
		String time[] = hourOfLottery.getText().split(":");
		Date date = new Date(d.getYear()-1900, d.getMonth().getValue()-1, d.getDayOfMonth(),Integer.parseInt(time[0]), Integer.parseInt(time[1]));
		c.createLottery(date,Integer.parseInt(lotteryMaxMiner.getText()), Integer.parseInt(LotteryNumOfWinners.getText()), 
				Integer.parseInt(lotteryBenefits.getText()));
		try {
			Verificator.getInsatnce().success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void ExcecuteLottery() {
		try {
			lotteryWinners.setText(e.executeLottery(lotteryById.get(Integer.parseInt(lotteries.getValue()))));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParticipentsException e) {
			try {
				Verificator.getInsatnce().errorSound();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setContentText("There Are No Participents In This Lottery");
			al.setTitle("System Message");
			al.setResizable(false);
			al.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
		}
	}
	

	public void addBenefit()
	{
		c = new CreateLottery();
		if(c.defineBenefits(lotteryBenefitDiscription.getText()))
			try {
				Verificator.getInsatnce().success();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				Verificator.getInsatnce().fail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}



