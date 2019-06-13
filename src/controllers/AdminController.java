package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Administrateurs;
import application.dao.AdministrateursDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	boolean rmmbrme;
	
	@FXML
    private AnchorPane adminp;
	
    @FXML
    private JFXTextField emailinput;

    @FXML
    private JFXPasswordField passwdinput;

    @FXML
    private JFXCheckBox rememberme;

    @FXML
    private JFXButton loginbutton;

    @FXML
    void login(ActionEvent event) {
    	String error = "> Veuillez rectifier ce qui suit :\n";
		DBconnection db = new DBconnection();
		AdministrateursDAO admindao = new AdministrateursDAO(db.getConnection());
		boolean emailv = false;
		boolean passwdv = false;
		boolean uncomplete = false;
		if(!mailisvalid(emailinput.getText().trim())) {
			error += "- Veuillez saisir un email valide.\n";
			uncomplete = true;
		}else emailv = true;
		if(passwdinput.getText().trim().isEmpty()) {
			error += "- Veuillez saisir un mot de passe valide.\n";
			uncomplete = true;
		}else passwdv = true;
		Administrateurs admin = null;
		if(emailv && passwdv) {
			admin = admindao.findids(emailinput.getText().trim(), passwdinput.getText().trim());
			if(admin.getEmailAdmin() == null) {
				error += "- Email ou mot de passe incorrect!\n";
				uncomplete = true;
			}
		}
		if(uncomplete) {
			Alert alert = new Alert(AlertType.WARNING);
			messagebox(alert, "Informations invalides!", error);
			alert.showAndWait();
		}else {
			UserSession.setInstance(admin.getIDADMIN(), admin.getEmailAdmin(), admin.getNomAdmin(), admin.getPrenomAdmin(), isRmmbrme());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Dashboard.fxml"));
			AnchorPane dashboard = null;
			try {
				dashboard = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			adminp.getChildren().clear();
			adminp.getChildren().setAll(dashboard);
		}
    }
    private boolean mailisvalid(String email) {
		// TODO Auto-generated method stub
		String regex = "^[\\w\\d][\\w\\d\\_\\-\\.]{0,64}[\\w\\d]\\@[\\w\\d]{2,64}\\.[\\w\\d\\-]{2,10}$";
		return email.matches(regex);
	}
    public void messagebox(Alert alert, String header, String error) {
		alert.initStyle(StageStyle.UNDECORATED);
		alert.resizableProperty().setValue(Boolean.FALSE);
		alert.setResizable(false);
		alert.getDialogPane().getStylesheets()
				.add(getClass().getResource("../resources/css/alertbox.css").toExternalForm());
		alert.getDialogPane().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		alert.getDialogPane().setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				alert.setX(event.getScreenX() - xOffset);
				alert.setY(event.getScreenY() - yOffset);
			}
		});
		alert.setTitle("CINEFX Message Box");
		alert.setHeaderText("[CINEFX] : " + header);
		alert.setContentText(error);
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(this.getClass().getResource("../resources/images/icon.png").toString()));

	}
    
	public boolean isRmmbrme() {
		setRmmbrme();
		return rmmbrme;
	}
	public void setRmmbrme() {
		if(rememberme.isSelected()) this.rmmbrme = true;
		else this.rmmbrme = false;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(UserSession.getInstance() != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Dashboard.fxml"));
			AnchorPane dashboard = null;
			try {
				dashboard = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			adminp.getChildren().clear();
			adminp.getChildren().setAll(dashboard);
		}
	}

}
