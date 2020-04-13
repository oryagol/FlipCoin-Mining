package boundry;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuForUser implements Initializable  {


    @FXML
    private BorderPane borderMain;
    @FXML
    private ToggleButton menuReport;

    @FXML
    private ToggleButton menuLogOff;

    @FXML
    private ToggleButton menuExit;
 
    @FXML
    private ToggleButton menuMining;

    @FXML
    private ToggleButton menuLottery;
    @FXML
    private AnchorPane menuScreen;
    
   

	public void initialize(URL arg0, ResourceBundle arg1) {
		menuLogOff.setSelected(false);
		menuExit.setSelected(false);
		menuLottery.setSelected(false);
		menuMining.setSelected(false);
		menuReport.setSelected(false);
		
	}
	
	
	@ FXML public void btnExit() {
		
		menuLogOff.setSelected(false);
		menuLottery.setSelected(false);
		menuMining.setSelected(false);
		menuReport.setSelected(false);
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to exit the program?");
		al.setTitle("Exit Progrem");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			System.exit(0);
		}
	}

	@ FXML	public void btnLogOff() {
	
		menuExit.setSelected(false);
		menuLottery.setSelected(false);
		menuMining.setSelected(false);
		menuReport.setSelected(false);;
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to log out of the system?");
		al.setTitle("Log out");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
		
			try {
				
				Stage oldStage = (Stage) menuLogOff.getScene().getWindow();
				StackPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
				pane.setPrefSize(oldStage.getWidth(), oldStage.getHeight());
				oldStage.close();
				Stage stage=new Stage();
				stage.setScene(new Scene(pane));
				stage.setResizable(false);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
				
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@ FXML 
	public void btnCreateReport() {
		menuLogOff.setSelected(false);
		menuExit.setSelected(false);
		menuLottery.setSelected(false);
		menuMining.setSelected(false);
		
		try {
			if(menuReport.isSelected())
			{
				
				AnchorPane pane=FXMLLoader.load(getClass().getResource("/boundry/FrmReportsForUser.fxml"));
				pane.setPrefSize(menuScreen.getWidth(), menuScreen.getHeight());
				menuScreen.getChildren().removeAll(menuScreen.getChildren());
				menuScreen.getChildren().add(pane);
			}
			else
			{
				menuScreen.getChildren().removeAll(menuScreen.getChildren());
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}


	
	}
   @FXML
	public void btnMining() {
	   menuLogOff.setSelected(false);
		menuExit.setSelected(false);
		menuLottery.setSelected(false);
		
		menuReport.setSelected(false);
		try {
		if(menuMining.isSelected())
		{
			
			TabPane pane=FXMLLoader.load(getClass().getResource("/boundry/MiningForUser.fxml"));
			pane.setPrefSize(menuScreen.getWidth(), menuScreen.getHeight());
			menuScreen.getChildren().removeAll(menuScreen.getChildren());
			menuScreen.getChildren().add(pane);
		}
		else
		{
			menuScreen.getChildren().removeAll(menuScreen.getChildren());
		}

	} catch (IOException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}

	}

   @FXML
	public void btnLottery() {
	  menuLogOff.setSelected(false);
		menuExit.setSelected(false);
		menuMining.setSelected(false);
		menuReport.setSelected(false);
		try {
		if(menuLottery.isSelected())
		{
			
			AnchorPane pane=FXMLLoader.load(getClass().getResource("/boundry/FrmLotteryUser.fxml"));
			pane.setPrefSize(menuScreen.getWidth(), menuScreen.getHeight());
			menuScreen.getChildren().removeAll(menuScreen.getChildren());
			menuScreen.getChildren().add(pane);
		}
		else
		{
			menuScreen.getChildren().removeAll(menuScreen.getChildren());
		}

	} catch (IOException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}

	}
}
