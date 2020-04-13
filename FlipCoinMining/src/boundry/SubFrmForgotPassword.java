package boundry;


import java.net.URL;
import java.util.ResourceBundle;

import control.LoginLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import entity.Miner;


public class SubFrmForgotPassword  implements Initializable {

	@FXML
	private TextField ForgetUser;

	@FXML
	private TextField ForgetEmail;

	@FXML
	private Button SendPass;

	private Miner miner;


	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void btnForgotPassword(ActionEvent e) throws Exception {
		LoginLogic logic = new LoginLogic();
		miner=logic.forgotPasswordVerify(ForgetUser.getText(), ForgetEmail.getText());
		if(miner!=null)
		{
			// Recipient's email ID needs to be mentioned.
			String to = ForgetEmail.getText();

			// Sender's email ID needs to be mentioned
			String from = "flipcoinappteam@gmail.com";


			// Get system properties
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


			// Get the default Session object.

			//Session session = Session.getDefaultInstance(props);

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("flipcoinappteam@gmail.com","kursizuv10");
				}
			});

			try{
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Password Retrieval For FlipCoinMining App");

				// Now set the actual message
				message.setText("Dear "+ miner.getFirstName()+" "+miner.getLastName() +"!\n"+"Your password is: "+miner.getPassword()+".\n"+"\n\n\n\n Glad We Could Help,\n FlipCoin Team.");

				Transport.send(message);
				System.out.println("Sent message successfully....");
				Stage oldStage = (Stage) SendPass.getScene().getWindow();
				oldStage.close();
				Verificator.getInsatnce().successSound();
				Verificator.getInsatnce().successSentMailAlert();
		
				



			}catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
		else
		{
			Verificator.getInsatnce().errorSound();
			Verificator.getInsatnce().failSentMailAlert();

		}
	}




}





