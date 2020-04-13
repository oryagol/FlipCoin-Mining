package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import control.RegisterLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SubFrmRegister implements Initializable {

	 @FXML
	    private TextField regName;

	    @FXML
	    private PasswordField  regPass;

	    @FXML
	    private PasswordField  regVerPass;

	    @FXML
	    private TextField regEmail;

	    @FXML
	    private Button regReg;

	    @FXML
	    private Button regReset;

	    @FXML
	    private TextField regLastName;

	    @FXML
	    private TextField regCoConFirstName;

	    @FXML
	    private PasswordField  regCoPass;

	    @FXML
	    private PasswordField  regCoVerPass;

	    @FXML
	    private Button regCoRegister;

	    @FXML
	    private Button regCoClear;

	    @FXML
	    private TextField regCoConLasrtName;

	    @FXML
	    private TextField regCoConEmail;

	    @FXML
	    private TextField regCoConPhone;

	    @FXML
	    private TextField regCoName;

	    @FXML
	    private TextField regCoEmail;

	private RegisterLogic l;


	public void btnRegister() throws Exception {
		l = new RegisterLogic();
		if(l.register(regName.getText(), regLastName.getText(), regPass.getText() ,regVerPass.getText(), regEmail.getText()))
		{
			Stage oldStage = (Stage) regReg.getScene().getWindow();
			oldStage.close();
			Verificator.getInsatnce().success();
		}
		else
		{
			Verificator.getInsatnce().fail();
		}
	}

	public void btnReset() {
		regName.setText("");
		regPass.setText("");
		regVerPass.setText("");
		regEmail.setText("");
		
		regCoName.setText("");
		regCoPass.setText("");
		regCoVerPass.setText("");
		regCoEmail.setText("");
		
		
		regCoConFirstName.setText("");
		regCoConLasrtName.setText("");
		regCoConEmail.setText("");
		regCoConPhone.setText("");
		
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}