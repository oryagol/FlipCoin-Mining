
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Utils.Type;
import control.CombinationCreator;
import control.CreateLottery;
import control.DataHandler;
import control.ExecuteLottery;
import control.ImportRiddles;
import control.LoginLogic;
import control.RegisterLogic;
import control.SolveRiddle;
import control.TransactionSelection;
import control.UpdateIrreleventStatus;
import control.UpdateLevel;
import control.UserLottery;
import entity.Benefit;
import entity.Block;
import entity.Consts;
import entity.DifficultyLevel;
import entity.Lottery;
import entity.Miner;
import entity.Riddle;
import entity.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
	
	
	public static void main(String[] args) {

		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("boundry/Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(900);
		stage.setHeight(640);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
	}

}