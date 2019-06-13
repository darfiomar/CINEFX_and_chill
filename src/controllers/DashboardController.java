package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.dao.ClientsDAO;
import application.dao.TicketsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

	@FXML
	private AnchorPane dashboardp;

	@FXML
	private JFXButton adminprofile;

	@FXML
	private JFXButton logoutbutton;

	@FXML
	private Label soldtickets;

	@FXML
	private Label subscriptions;

	@FXML
	private Label chiffredaffaires;

	@FXML
	private Label todaysdate;

	@FXML
	private JFXButton ajouterfilm;

	@FXML
	private JFXButton editerfilm;

	@FXML
	private JFXButton ajouterseance;

	@FXML
	private JFXButton editerseance;

	@FXML
	private JFXButton ajouterabonne;

	@FXML
	private JFXButton editerabonne;

	@FXML
	private JFXButton listeabonnes;

	@FXML
	private JFXButton listenonabonne;

	@FXML
	void addabonne(ActionEvent event) {

	}

	@FXML
	void addmovie(ActionEvent event) {

	}

	@FXML
	void addseance(ActionEvent event) {

	}

	@FXML
	void editabonne(ActionEvent event) {

	}

	@FXML
	void editadminprofile(ActionEvent event) {

	}

	@FXML
	void editmovie(ActionEvent event) {

	}

	@FXML
	void editseance(ActionEvent event) {

	}

	@FXML
	void logout(ActionEvent event) {
		if (UserSession.getInstance() != null)
			UserSession.cleanUserSession();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Adminlogin.fxml"));
		AnchorPane adminlogin = null;
		try {
			adminlogin = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dashboardp.getChildren().clear();
		dashboardp.getChildren().setAll(adminlogin);
	}

	@FXML
	void showlistabonne(ActionEvent event) {

	}

	@FXML
	void showlistnonabonne(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		adminprofile.setText("Hi, " + UserSession.getInstance().getUserPrenom() + ".");
		DBconnection db = new DBconnection();
		TicketsDAO t = new TicketsDAO(db.getConnection());
		ClientsDAO c = new ClientsDAO(db.getConnection());
		soldtickets.setText(Integer.toString(t.soldtickts()));
		subscriptions.setText(Integer.toString(c.subcriptions()));
		chiffredaffaires.setText(Double.toString(t.ticketsmoney() + c.subsmoney()) + "Dh");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String datedaujourdhui = (String) dateFormat.format(date);
		todaysdate.setText("Le " + datedaujourdhui);
	}

}
