package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Cartesbancaires;
import application.Clients;
import application.Films;
import application.Seances;
import application.Tickets;
import application.dao.CartesbancairesDAO;
import application.dao.ClientsDAO;
import application.dao.DatesDAO;
import application.dao.FilmsDAO;
import application.dao.HorairesDAO;
import application.dao.SallesDAO;
import application.dao.SeancesDAO;
import application.dao.TicketsDAO;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BuyticketController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	private AnchorPane buyticketp;

	@FXML
	private JFXComboBox<String> selectmovie;

	@FXML
	private JFXComboBox<String> selectdate;

	@FXML
	private JFXComboBox<String> selecthoraire;

	@FXML
	private JFXComboBox<String> selectsalle;

	@FXML
	private Label prixseance;

	@FXML
	private Label places;

	@FXML
	private JFXTextField mailclient;

	@FXML
	private Label statutclient;

	@FXML
	private JFXTextField nomclient;

	@FXML
	private JFXTextField prenomclient;

	@FXML
	private JFXTextField ccclient;

	@FXML
	private JFXTextField declient;

	@FXML
	private JFXTextField cryptoclient;

	@FXML
	private Label discount;
	@FXML
	private JFXButton buyticket;

	@FXML
	void choosemovie(ActionEvent event) {
		selectdate.getItems().clear();
		DBconnection db = new DBconnection();
		SeancesDAO seances = new SeancesDAO(db.getConnection());
		FilmsDAO film = new FilmsDAO(db.getConnection());
		Set<Seances> s = new LinkedHashSet<Seances>(seances.findbymovie(film.findbyname(selectmovie.getValue())));
		Set<String> items = new LinkedHashSet<String>();
		for (Seances seance : s) {
			items.add(seance.getLadate().getDates());
		}
		prixseance.setText("");
		places.setText("");
		selectdate.getSelectionModel().clearSelection();
		selecthoraire.getSelectionModel().clearSelection();
		selectsalle.getSelectionModel().clearSelection();
		selectdate.getItems().setAll(items);
		selectdate.setDisable(false);
		selecthoraire.setDisable(true);
		selectsalle.setDisable(true);
	}

	@FXML
	void choosedate(ActionEvent event) {
		selecthoraire.getItems().clear();
		DBconnection db = new DBconnection();
		SeancesDAO seances = new SeancesDAO(db.getConnection());
		FilmsDAO film = new FilmsDAO(db.getConnection());
		DatesDAO date = new DatesDAO(db.getConnection());
		Set<Seances> s = new LinkedHashSet<Seances>(seances.findbymovieanddate(film.findbyname(selectmovie.getValue()),
				date.findbydate(selectdate.getValue())));
		Set<String> items = new LinkedHashSet<String>();
		for (Seances seance : s) {
			items.add(seance.getLhoraire().getHoraire());
		}
		prixseance.setText("");
		places.setText("");
		selecthoraire.getSelectionModel().clearSelection();
		selectsalle.getSelectionModel().clearSelection();
		selecthoraire.getItems().setAll(items);
		selecthoraire.setDisable(false);
		selectsalle.setDisable(true);
	}

	@FXML
	void choosehoraire(ActionEvent event) {
		selectsalle.getItems().clear();
		DBconnection db = new DBconnection();
		SeancesDAO seances = new SeancesDAO(db.getConnection());
		FilmsDAO film = new FilmsDAO(db.getConnection());
		DatesDAO date = new DatesDAO(db.getConnection());
		HorairesDAO horaire = new HorairesDAO(db.getConnection());
		Set<Seances> s = new LinkedHashSet<Seances>(
				seances.findbymoviedateandhoraire(film.findbyname(selectmovie.getValue()),
						date.findbydate(selectdate.getValue()), horaire.findbyhoraire(selecthoraire.getValue())));
		Set<String> items = new LinkedHashSet<String>();
		for (Seances seance : s) {
			items.add(seance.getLasalle().getSalle());
		}
		prixseance.setText("");
		places.setText("");
		selectsalle.getSelectionModel().clearSelection();
		selectsalle.getItems().setAll(items);
		selectsalle.setDisable(false);
	}

	@FXML
	void choosesalle(ActionEvent event) {
		prixseance.setText("");
		places.setText("");
		if (selectmovie.getValue() != null && selectdate.getValue() != null && selecthoraire.getValue() != null) {
			DBconnection db = new DBconnection();
			SeancesDAO seance = new SeancesDAO(db.getConnection());
			FilmsDAO film = new FilmsDAO(db.getConnection());
			DatesDAO date = new DatesDAO(db.getConnection());
			SallesDAO salle = new SallesDAO(db.getConnection());
			HorairesDAO horaire = new HorairesDAO(db.getConnection());
			Seances s = seance.findbymoviedatehoraireandsalle(film.findbyname(selectmovie.getValue()),
					date.findbydate(selectdate.getValue()), horaire.findbyhoraire(selecthoraire.getValue()),
					salle.findbyroom(selectsalle.getValue()));
			prixseance.setText(Double.toString(s.getPrixSeance()) + " DH");
			int nombredeplaces = s.getLasalle().getNplaces() - s.getNReservation();
			places.setText((nombredeplaces > 0) ? Integer.toString(nombredeplaces) : "Aucun!");
		}
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
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons()
				.add(new Image(this.getClass().getResource("../resources/images/icon.png").toString()));

	}

	@FXML
	void acheterticket(ActionEvent event) {
		String error = "> Veuillez rectifier ce qui suit :\n";
		DBconnection db = new DBconnection();
		boolean uncomplete = false;
		boolean scv = false;
		boolean emailv = false;
		boolean nomv = false;
		boolean prenomv = false;
		SeancesDAO seance = new SeancesDAO(db.getConnection());
		FilmsDAO film = new FilmsDAO(db.getConnection());
		DatesDAO date = new DatesDAO(db.getConnection());
		SallesDAO salle = new SallesDAO(db.getConnection());
		HorairesDAO horaire = new HorairesDAO(db.getConnection());
		CartesbancairesDAO carte = new CartesbancairesDAO(db.getConnection());
		ClientsDAO client = new ClientsDAO(db.getConnection());
		Seances s = new Seances();
		if (selectmovie.getSelectionModel().isEmpty()) {
			error += " - Seance : Veuillez choisir un film.\n";
			uncomplete = true;
		} else {
			if (selectdate.getSelectionModel().isEmpty()) {
				error += " - Seance : Veuillez choisir une date.\n";
				uncomplete = true;
			} else {
				if (selecthoraire.getSelectionModel().isEmpty()) {
					error += " - Seance : Veuillez choisir un horaire.\n";
					uncomplete = true;
				} else {
					if (selectsalle.getSelectionModel().isEmpty()) {
						error += " - Seance : Veuillez choisir une salle.\n";
						uncomplete = true;
					} else {

						s = seance.findbymoviedatehoraireandsalle(film.findbyname(selectmovie.getValue()),
								date.findbydate(selectdate.getValue()), horaire.findbyhoraire(selecthoraire.getValue()),
								salle.findbyroom(selectsalle.getValue()));
						if (s.getIDSEANCE() == 0) {
							error += " - Seance : Seance introuvable.\n";
							uncomplete = true;
						} else {
							int nombredeplaces = s.getLasalle().getNplaces() - s.getNReservation();
							if (nombredeplaces <= 0) {
								error += " - Seance : Les tickets sont tous vendus!\n";
								uncomplete = true;
							} else
								scv = true;
						}
					}
				}
			}
		}
		if (!mailisvalid(mailclient.getText().trim())) {
			error += " - Coordonnées : Votre Email est invalide!\n";
			uncomplete = true;
		} else {
			emailv = true;
		}
		if (nomclient.getText().isEmpty()) {
			error += " - Coordonnées : Votre saisir votre nom.\n";
			uncomplete = true;
		} else {
			nomv = true;
		}
		if (prenomclient.getText().isEmpty()) {
			error += " - Coordonnées : Votre saisir votre prénom.\n";
			uncomplete = true;
		} else {
			prenomv = true;
		}
		Cartesbancaires c = new Cartesbancaires();
		if (scv && emailv && nomv && prenomv) {
			if (ccclient.getText().isEmpty() || declient.getText().isEmpty() || cryptoclient.getText().isEmpty()) {
				error += " - Paiment : Carte Bancaire invalide.\n";
				uncomplete = true;
			} else {
				c = carte.findcard(ccclient.getText().trim(), declient.getText().trim(),
						Integer.parseInt(cryptoclient.getText().trim()));
				if (c.getNumeroCarte() == null) {
					error += " - Paiment : Carte Bancaire invalide.\n";
					uncomplete = true;
				} else {
					if (!c.payer(s.getPrixSeance())) {
						error += " - Paiment : Votre solde est insuffisant.\n";
						uncomplete = true;
					}
				}
			}
		}

		if (uncomplete) {
			Alert alert = new Alert(AlertType.WARNING);
			messagebox(alert, "Informations invalides!", error);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			messagebox(alert, "Confimation d'achat.", "- Veuillez confirmer l'achat de votre ticket :");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Confirmer");
			((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Annuler");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				double price = 0;
				TicketsDAO ticket = new TicketsDAO(db.getConnection());
				Clients clt = new Clients(mailclient.getText().trim(), nomclient.getText().trim(),
						prenomclient.getText().trim());
				Clients rclt = new Clients();
				int status = client.status(clt.getEmailClient());
				switch (status) {
				case 0:
					price = s.getPrixSeance();
					if (!client.create(clt)) {
						System.out.println("Something went wrong.");
					} else {
						rclt = client.findbyemail(clt.getEmailClient());
					}
					break;
				case 1:
					price = s.getPrixSeance();
					rclt = client.findbyemail(clt.getEmailClient());
					if (!client.update(rclt)) {
						System.out.println("Something went wrong. (update client)");
					}
					break;
				case 2:
					price = s.getPrixSeance();
					rclt = client.findbyemail(clt.getEmailClient());
					if (!client.update(rclt)) {
						System.out.println("Something went wrong. (update client)");
					}
					break;
				case 3:
					price = s.getPrixSeance() - s.getPrixSeance() * 0.2;
					rclt = client.findbyemail(clt.getEmailClient());
					if (!client.update(rclt)) {
						System.out.println("Something went wrong. (update client)");
					}
					break;
				default:
					System.out.println("Something went wrong. (create or update client)");
				}
				Tickets temp = new Tickets(s, rclt, c, price);
				seance.setReservation(s);
				carte.transaction(c, price);
				Tickets t = ticket.createandget(temp);
				System.out.println(t.getLeclient().getEmailClient() + t.getTempsTicket()
						+ Double.toString(t.getPrixTicket()) + String.format("%06d", t.getCodeBarreTicket())
						+ t.getLaseance().getLasalle().getSalle() + "Fx-" + Integer.toString(t.getIDTICKET())
						+ t.getLaseance().getLefilm().getNomFilm() + t.getLaseance().getLhoraire().getHoraire()
						+ t.getLaseance().getLadate().getDates());
				Ticketgenerator htmlticket = new Ticketgenerator(t.getLeclient().getEmailClient(), t.getTempsTicket(),
						Double.toString(t.getPrixTicket()), String.format("%06d", t.getCodeBarreTicket()),
						t.getLaseance().getLasalle().getSalle(), "Fx-" + Integer.toString(t.getIDTICKET()),
						t.getLaseance().getLefilm().getNomFilm(), t.getLaseance().getLhoraire().getHoraire(),
						t.getLaseance().getLadate().getDates());
				try {
					htmlticket.genrate();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Alert alrt = new Alert(AlertType.INFORMATION);
				messagebox(alrt, "Achat effectué.",
						"- L'achat de votre ticket est effectué.\n- Un Email contenant votre ticket vous a été envoyé.\n- CINEFX vous souhaite une visualisation agréable!");
				alrt.showAndWait();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Buyticket.fxml"));
				AnchorPane newticketpane = null;
				try {
					newticketpane = (AnchorPane) loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				buyticketp.getChildren().clear();
				buyticketp.getChildren().setAll(newticketpane);
			} else {
				// ... user chose CANCEL or closed the dialog
			}
		}

	}

	public void setSelectmovie(String movie) {

		this.selectmovie.setValue(movie);
		choosemovie(new ActionEvent());
		// this.selectmovie.fireEvent(null);
	}

	public void setSelectdate(JFXComboBox<String> selectdate) {
		this.selectdate = selectdate;
	}

	public void setSelecthoraire(JFXComboBox<String> selecthoraire) {
		this.selecthoraire = selecthoraire;
	}

	public void setSelectsalle(JFXComboBox<String> selectsalle) {
		this.selectsalle = selectsalle;
	}

	private boolean mailisvalid(String email) {
		// TODO Auto-generated method stub
		String regex = "^[\\w\\d][\\w\\d\\_\\-\\.]{0,64}[\\w\\d]\\@[\\w\\d]{2,64}\\.[\\w\\d\\-]{2,10}$";
		return email.matches(regex);
	}

	public String computestatus(String email) {

		if (email.trim().isEmpty()) {

			return email;
		} else {
			if (mailisvalid(email)) {
				DBconnection db = new DBconnection();
				ClientsDAO client = new ClientsDAO(db.getConnection());
				return statushandler(client.status(email));
			} else {
				return "Invalid E-mail!";
			}
		}
	}

	public String statushandler(int status) {
		switch (status) {
		case 0:
			return "Premiere visite.";
		case 1:
			return "Non abonné.";
		case 2:
			return "Non abonné. <EXPIRED>";
		case 3:
			return "Vous êtes abonné.";
		default:
			return "Unkown";
		}
	}

	public String computediscount(String status) {
		if (status == "Vous êtes abonné.")
			return "- 20%";
		else
			return "";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(UserSession.getInstance() != null) {
			if(!UserSession.getInstance().isRememberme()) {UserSession.cleanUserSession();}
		}
		selectmovie.getItems().clear();
		selectdate.getItems().clear();
		selecthoraire.getItems().clear();
		selectsalle.getItems().clear();
		prixseance.setText("");
		places.setText("");
		selectmovie.getSelectionModel().clearSelection();
		selectdate.getSelectionModel().clearSelection();
		selecthoraire.getSelectionModel().clearSelection();
		selectsalle.getSelectionModel().clearSelection();
		DBconnection db = new DBconnection();
		SeancesDAO seances = new SeancesDAO(db.getConnection());
		Set<Seances> s = new LinkedHashSet<Seances>(seances.findAll());
		Set<String> items = new LinkedHashSet<String>();
		for (Seances seance : s) {
			items.add(seance.getLefilm().getNomFilm());
		}
		selectmovie.getItems().setAll(items);
		selectdate.setDisable(true);
		selecthoraire.setDisable(true);
		selectsalle.setDisable(true);
		StringBinding getstatus = new StringBinding() {
			{
				bind(mailclient.textProperty());
			}

			@Override
			protected String computeValue() {
				// TODO Auto-generated method stub
				// System.out.println(mailabonne.getText());
				return computestatus(mailclient.getText().trim());
			}

		};
		StringBinding getdiscout = new StringBinding() {
			{
				bind(statutclient.textProperty());
			}

			@Override
			protected String computeValue() {
				// TODO Auto-generated method stub
				// System.out.println(mailabonne.getText());
				return computediscount(statutclient.getText().trim());
			}

		};
		statutclient.textProperty().bind(getstatus);
		discount.textProperty().bind(getdiscout);
	}

}
