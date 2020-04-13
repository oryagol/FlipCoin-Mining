package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import control.LoginLogic;
import control.UserLottery;
import entity.Benefit;
import entity.Lottery;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FrmLotteryUser  implements Initializable {
	@FXML
	private ComboBox<String> available;

	@FXML
	private ComboBox<String> myLotteries;

	@FXML
	private ComboBox<String> wonLottery;

	@FXML
	private TextArea prizesBox;

	private HashSet<Lottery> lotteryNotSigned = new HashSet<>();
	private HashSet<Lottery> lotterySigned = new HashSet<>();
	private HashSet<Lottery> lotteryWon = new HashSet<>();
	private ArrayList<Benefit> benefits = new ArrayList<>();
	private UserLottery u = new UserLottery();

	@FXML
	void cancel(ActionEvent event) {
		if(u.cancelSignToLottery(Integer.parseInt(myLotteries.getSelectionModel().getSelectedItem())))
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

	@FXML
	void showPrizes(ActionEvent event) {
		prizesBox.setText(u.showLotteryBenefits(Integer.parseInt(wonLottery.getSelectionModel().getSelectedItem())));
	}

	@FXML
	void signUp(ActionEvent event) {
		if(u.signToLottery(Integer.parseInt(available.getSelectionModel().getSelectedItem())))
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lotteryNotSigned = u.getLotteryUnsigned();
		lotterySigned = u.getLotterySigned();
		lotteryWon = u.getWonLotteries();
		ObservableList<String> aList = FXCollections.observableArrayList();
		ObservableList<String> bList = FXCollections.observableArrayList();
		ObservableList<String> cList = FXCollections.observableArrayList();

		for(Lottery i : lotteryNotSigned) {
			if(i != null)
				aList.add(i.getLotteryId().toString());
		}


		for(Lottery i : lotterySigned) {
			if(i != null)
				bList.add(i.getLotteryId().toString());
		}


		for(Lottery i : lotteryWon) {
			if(i != null)
				cList.add(i.getLotteryId().toString());
		}
		available.setItems(aList);
		myLotteries.setItems(bList);
		wonLottery.setItems(cList);

	}


}
