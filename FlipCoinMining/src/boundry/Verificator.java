package boundry;

import java.io.File;
import java.net.URLDecoder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import entity.Consts;
import javafx.scene.control.Alert;

public class Verificator {
	
	private final static String badSound = Consts.getPath("Sound/error.wav");
	private final static String goodSound = Consts.getPath("Sound/toggle.wav");
	private static Verificator verificator;
	
	
	public static Verificator getInsatnce(){
		if(verificator == null)
			verificator = new Verificator();
		return verificator;
	}
	
	public void errorSound() throws Exception{
	    File yourFile = new File(badSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}

	public void successSound() throws Exception{
	    File yourFile = new File(goodSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	
	public void successAlert() throws Exception{
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Success");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failAlert() throws Exception{
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Fail");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void success() throws Exception {
		successSound();
		successAlert();
	}
	
	public void fail() throws Exception {
		errorSound();
		failAlert();
		}
	

	
	public void failLoginAlert() throws Exception{
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Login Failed!\n Wrong Password Or UserName");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successSentMailAlert() throws Exception{
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Your Password Retrival Mail Was Sent Successfuly,Please Check Your Inbox!");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSentMailAlert() throws Exception{
		errorSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("The User Name or Email Don't Exist!");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failTransAdd() throws Exception{
		Alert al = new Alert(Alert.AlertType.ERROR);
		errorSound();
		al.setContentText("Can't add transaction without choosing a block to add to!");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	public void importFail() throws Exception{
		errorSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Import Failed,One or more transactions you tried to import already exist in the DataBase!");
		al.setTitle("System Message");
		al.setResizable(false);
		al.showAndWait();
	}
	
}
