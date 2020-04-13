package boundry;


import java.io.File;
import java.io.IOException; 
import java.net.URL;
import java.util.ResourceBundle;

import entity.Consts;

import entity.Miner;

import control.LoginLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class FrmLogin implements Initializable{
	private final static String video = Consts.getPath("blocks.mp4");
	@FXML
	private StackPane root;

	@FXML
	private Button loginPageExit;

	@FXML
	private MediaView media;


	@FXML
	private BorderPane screen;

	@FXML
	private PasswordField password;

	@FXML
	private TextField userName;

	public void initialize(URL arg0, ResourceBundle arg1) {
		Media mv=new Media(new File(video).toURI().toString());
		MediaPlayer mp=new MediaPlayer(mv);
		media.setMediaPlayer(mp);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mp.play();

	}
	public void btnForgotPassword(ActionEvent e) throws Exception {
		try {

			FXMLLoader loader=new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
			Parent root1=(Parent)loader.load();
			Stage stage=new Stage();
			stage.setTitle("Forgot Password");
			stage.setScene(new Scene(root1));
			stage.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}





	public void btnExit(ActionEvent e) {

		System.exit(0);

	}


	public void btnLogin(ActionEvent e) throws Exception {
		BorderPane pane;

		LoginLogic logic = new LoginLogic();
		String userNa = userName.getText();
		String userPa = password.getText();
		Miner m = logic.verifyUser(userNa, userPa);
		try {
			if(m.isEmpolyee()) {
				Verificator.getInsatnce().successSound();
				pane = FXMLLoader.load(getClass().getResource("FrmMenu.fxml"));
				pane.setPrefSize(root.getWidth(), root.getHeight());
				screen.getChildren().removeAll(screen.getChildren());
				screen.getChildren().add(pane);
			}
			else if(!m.isEmpolyee()) {
				Verificator.getInsatnce().successSound();
				pane = FXMLLoader.load(getClass().getResource("MenuForUser.fxml"));
				pane.setPrefSize(root.getWidth(), root.getHeight());
				screen.getChildren().removeAll(screen.getChildren());
				screen.getChildren().add(pane);
			}
			else
			{
				Verificator.getInsatnce().errorSound();
				Verificator.getInsatnce().failLoginAlert();

			}
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(NullPointerException e2) {
			Verificator.getInsatnce().errorSound();
			Verificator.getInsatnce().failLoginAlert();
		}
	}


	public void btnRegister(ActionEvent e)
	{
		try {

			FXMLLoader loader=new FXMLLoader(getClass().getResource("register.fxml"));
			Parent root1=(Parent)loader.load();
			Stage stage=new Stage();
			stage.setTitle("Register");
			stage.setScene(new Scene(root1));
			stage.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
