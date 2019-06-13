package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Cartesbancaires;
import application.Clients;
import application.Tickets;
import application.dao.CartesbancairesDAO;
import application.dao.ClientsDAO;
import application.dao.TicketsDAO;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SubscribeController implements Initializable {
	private int joursforfait = 0;
	private double prixforfait = 0.0;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane subscribep;

	@FXML
	private JFXTextField mailabonne;

	@FXML
	private Label statutabonne;

	@FXML
	private Label actionabonne;

	@FXML
	private JFXTextField nomabonne;

	@FXML
	private JFXTextField prenomabonne;

	@FXML
	private JFXTextField adrabonne;

	@FXML
	private JFXTextField phoneabonne;

	@FXML
	private JFXRadioButton onemonth;

	@FXML
	private ToggleGroup forfait;

	@FXML
	private JFXRadioButton threemonth;

	@FXML
	private JFXRadioButton sixmonth;

	@FXML
	private JFXTextField ccabonne;

	@FXML
	private JFXTextField deabonne;

	@FXML
	private JFXTextField cryptoabonne;

	@FXML
	private JFXButton sabonner;

	@FXML
	void subscribe(ActionEvent event) {
		String error = "> Veuillez rectifier ce qui suit :\n";
		DBconnection db = new DBconnection();
		ClientsDAO client = new ClientsDAO(db.getConnection());
		boolean uncomplete = false;
		boolean emailv = false;
		boolean nomv = true;
		boolean prenomv = true;
		boolean adrv = true;
		boolean phonev = true;
		boolean jfv = false;
		boolean pfv = false;
		if (!mailisvalid(mailabonne.getText().trim())) {
			error += " - Coordonnées : Votre Email est invalide!\n";
			uncomplete = true;
		} else {
			emailv = true;
			int status = client.status(mailabonne.getText().trim());
			if (status == 0 || status == 1) {
				if(nomabonne.getText().trim().isEmpty()) {
					error += " - Coordonnées : Votre saisir votre nom.\n";
					uncomplete = true;
					nomv = false;
				}
				if(prenomabonne.getText().trim().isEmpty()) {
					error += " - Coordonnées : Votre saisir votre prénom.\n";
					uncomplete = true;
					prenomv = false;
				}
				if(adrabonne.getText().trim().isEmpty()) {
					error += " - Coordonnées : Votre saisir votre adresse.\n";
					uncomplete = true;
					adrv = false;
				}
				if(!phoneisvalid(phoneabonne.getText().trim())) {
					error += " - Coordonnées : Numéro de téléphone invalide.\n";
					uncomplete = true;
					phonev = false;
				}
			}	
		}
		if(getJoursforfait() == 0) {
			error += " - Forfait : Veuillez choisir votre forfait.\n";
			uncomplete = true;
		}else {jfv = true;}
		if(getPrixforfait() == 0.0) {
			error += " - Forfait : Veuillez choisir votre forfait.\n";
			uncomplete = true;
		}else {pfv = true;}
		CartesbancairesDAO carte = new CartesbancairesDAO(db.getConnection());
		Cartesbancaires c = new Cartesbancaires();
		if (jfv && emailv && nomv && prenomv && phonev && adrv) {
			if (ccabonne.getText().isEmpty() || deabonne.getText().isEmpty() || cryptoabonne.getText().isEmpty()) {
				error += " - Paiment : Carte Bancaire invalide.\n";
				uncomplete = true;
			} else {
				c = carte.findcard(ccabonne.getText().trim(), deabonne.getText().trim(),
						Integer.parseInt(cryptoabonne.getText().trim()));
				if (c.getNumeroCarte() == null) {
					error += " - Paiment : Carte Bancaire invalide.\n";
					uncomplete = true;
				} else {
					if (!c.payer(getPrixforfait())) {
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
			messagebox(alert, "Confimation d'abonnement.", "- Veuillez confirmer votre abonnement :");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Confirmer");
			((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Annuler");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Clients clt = new Clients(0, getJoursforfait(), mailabonne.getText().trim(), null, nomabonne.getText().trim(),
						prenomabonne.getText().trim(), adrabonne.getText().trim(), phoneabonne.getText().trim(), 0, false,getPrixforfait());
				int sts = client.status(clt.getEmailClient());
				Alert alrt = new Alert(AlertType.INFORMATION);
				switch (sts) {
				case 0:
					Clients newabonne = client.createnewabonne(clt);
					if((newabonne.getIDCLIENT()) != 0) {
						messagebox(alrt, "Abonnement activé.",
								"- Félicitation! Vous venez d'activer votre abonnement CINEFX.\n"
								+ "- Désormais, vous bénéficiez d'une reduction de -20% sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.\n"
								+ "- Un Email contenant vos détails d'abonnement vous a été envoyé.\n"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						Subscribemailer mail = new Subscribemailer(newabonne.getEmailClient(),"Abonnement activé.","<strong>Félicitation!</strong> Vous venez d'activer votre abonnement CINEFX.<br>"
								+ "- Désormais, vous bénéficiez d'une réduction de <strong>-20%</strong> sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.<br>"
								+ " > Durée d'abonnement : "+getJoursforfait()+" jours.<br>"
								+ " > Montant payé : <strong>"+getPrixforfait()+"DH</strong><br>"
								+ " > Date de fin d'abonnement : "+newabonne.getFinAbonnement()+"<br>"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						mail.sendemail();
					}
					else {System.out.println("0 - Something went wrong.");}
					break;
				case 1:
					Clients clienttoabonne = client.updateclienttoabonne(clt);
					if(clienttoabonne.getIDCLIENT() != 0) {
						messagebox(alrt, "Abonnement activé.",
								"- Félicitation! Vous venez d'activer votre abonnement CINEFX.\n"
								+ "- Désormais, vous bénéficiez d'une reduction de -20% sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.\n"
								+ "- Un Email contenant vos détails d'abonnement vous a été envoyé.\n"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						Subscribemailer mail = new Subscribemailer(clienttoabonne.getEmailClient(),"Abonnement activé.","<strong>Félicitation!</strong> Vous venez d'activer votre abonnement CINEFX.<br>"
								+ "- Désormais, vous bénéficiez d'une réduction de <strong>-20%</strong> sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.<br>"
								+ " > Durée d'abonnement : "+getJoursforfait()+" jours.<br>"
								+ " > Montant payé : <strong>"+getPrixforfait()+"DH</strong><br>"
								+ " > Date de fin d'abonnement : "+clienttoabonne.getFinAbonnement()+"<br>"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						mail.sendemail();
					}
					else {System.out.println("1 - Something went wrong.");}
					break;
				case 2:
					Clients exabonne = client.updateabonne(clt);
					if(exabonne.getIDCLIENT()  != 0 ) {
						messagebox(alrt, "Abonnement renouvelé.",
								"- Félicitation! Vous venez de renouveler votre abonnement CINEFX.\n"
								+ "- Désormais, vous bénéficiez d'une reduction de -20% sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.\n"
								+ "- Un Email contenant vos détails d'abonnement vous a été envoyé.\n"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						Subscribemailer mail = new Subscribemailer(exabonne.getEmailClient(),"Abonnement renouvelé.","<strong>Félicitation!</strong> Vous venez de renouveler votre abonnement CINEFX.<br>"
								+ "- Désormais, vous bénéficiez d'une réduction de <strong>-20%</strong> sur tous vos tickets achetés jusqu'à la fin de votre durée d'abonnement.<br>"
								+ " > Durée d'abonnement : "+getJoursforfait()+" jours.<br>"
								+ " > Montant payé : <strong>"+getPrixforfait()+"DH</strong><br>"
								+ " > Date de fin d'abonnement : "+exabonne.getFinAbonnement()+"<br>"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						mail.sendemail();
					}
					else {System.out.println("2 - Something went wrong.");}
					break;
				case 3:
					Clients currentabonne = client.updateabonne(clt);
					if(currentabonne.getIDCLIENT() != 0) {
						messagebox(alrt, "Abonnement prolongé.",
								"- Félicitation! Vous venez de prolonger votre abonnement CINEFX.\n"
								+ "- Vous bénéficiez d'une reduction de -20% sur tous vos tickets achetés jusqu'au nouvelle fin de votre durée d'abonnement.\n"
								+ "- Un Email contenant vos détails d'abonnement vous a été envoyé.\n"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						Subscribemailer mail = new Subscribemailer(currentabonne.getEmailClient(),"Abonnement prolongé.","<strong>Félicitation!</strong> Vous venez de prolonger votre abonnement CINEFX.<br>"
								+ "- Vous bénéficiez d'une réduction de <strong>-20%</strong> sur tous vos tickets achetés jusqu'au nouvelle fin de votre durée d'abonnement.<br>"
								+ " > Durée du prolongement d'abonnement : "+getJoursforfait()+" jours.<br>"
								+ " > Montant payé : <strong>"+getPrixforfait()+"DH</strong><br>"
								+ " > Nouvelle date de fin d'abonnement : "+currentabonne.getFinAbonnement()+"<br>"
								+ "- CINEFX vous souhaite une visualisation agréable!");
						mail.sendemail();
					}
					else {System.out.println("3 - Something went wrong.");}
					break;
				default:
					System.out.println("Status unknown.");
				}
				carte.transaction(c, getPrixforfait());
				alrt.showAndWait();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Subscribe.fxml"));
				AnchorPane newticketpane = null;
				try {
					newticketpane = (AnchorPane) loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				subscribep.getChildren().clear();
				subscribep.getChildren().setAll(newticketpane);
			} else {
				// ... user chose CANCEL or closed the dialog
			}
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
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(this.getClass().getResource("../resources/images/icon.png").toString()));

	}
	public void lockcoordonnees(boolean free) {
		nomabonne.clear();
		prenomabonne.clear();
		adrabonne.clear();
		phoneabonne.clear();
		nomabonne.setDisable(free);
		prenomabonne.setDisable(free);
		adrabonne.setDisable(free);
		phoneabonne.setDisable(free);
	}
	public String computestatus(String email) {
		if (email.trim().isEmpty()) {
			nomabonne.setDisable(true);
			prenomabonne.setDisable(true);
			adrabonne.setDisable(true);
			phoneabonne.setDisable(true);
			return email;
		}else {
			if (mailisvalid(email)) {
				DBconnection db = new DBconnection();
				ClientsDAO client = new ClientsDAO(db.getConnection());
				return statushandler(client.status(email));
			}
			else {
				nomabonne.setDisable(true);
				prenomabonne.setDisable(true);
				adrabonne.setDisable(true);
				phoneabonne.setDisable(true);
				return "Invalid E-mail!";
			}
		}
	}
	public String statushandler(int status) {
		switch (status) {
		case 0:
			lockcoordonnees(false);return "Premiere visite.";
		case 1:
			lockcoordonnees(false);return "Non abonné.";
		case 2:
			lockcoordonnees(true);return "Non abonné. <EXPIRED>";
		case 3:
			lockcoordonnees(true);return "Vous êtes abonné.";
		default:
			lockcoordonnees(true);return "Unkown";
		}
	}
	public String computeaction(String status) {
		switch (status) {
		case "Premiere visite." :
			return "Completez vos coordonnées :";
		case "Non abonné.":
			return "Completez vos coordonnées :";
		case "Non abonné. <EXPIRED>":
			return "Renouvelez votre abonnement ->";
		case "Vous êtes abonné.":
			return "Prolongez votre abonnement ->";
		default:
			return "-------------------------------------";
		}
	}
	public double getPrixforfait() {
		setPrixforfait();
		return this.prixforfait;
	}
	public void setPrixforfait() {
		JFXRadioButton selectedRadioButton = (JFXRadioButton) forfait.getSelectedToggle();
		String toogleGroupValue = selectedRadioButton.getText();
		switch(toogleGroupValue) {
		case "1 Mois - 299DH" :
			this.prixforfait = 299.00;break;
		case "3 Mois - 799DH":
			this.prixforfait = 799.00;break;
		case "6 Mois - 1499DH":
			this.prixforfait = 1499.00;break;
		default:
			this.prixforfait = 0.0;break;
		}
	}
	public int getJoursforfait() {
		setJoursforfait();
		return joursforfait;
	}
	public void setJoursforfait() {
		JFXRadioButton selectedRadioButton = (JFXRadioButton) forfait.getSelectedToggle();
		String toogleGroupValue = selectedRadioButton.getText();
		switch(toogleGroupValue) {
		case "1 Mois - 299DH" :
			this.joursforfait = 31;break;
		case "3 Mois - 799DH":
			this.joursforfait = 92;break;
		case "6 Mois - 1499DH":
			this.joursforfait = 184;break;
		default:
			this.joursforfait = 0;break;
		}
	}
	private boolean mailisvalid(String email) {
		// TODO Auto-generated method stub
		String regex = "^[\\w\\d][\\w\\d\\_\\-\\.]{0,64}[\\w\\d]\\@[\\w\\d]{2,64}\\.[\\w\\d\\-]{2,10}$";
		return email.matches(regex);
	}
	private boolean phoneisvalid(String phone) {
		// TODO Auto-generated method stub
		String regex = "^[\\d]{10,12}$";
		return phone.matches(regex);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(UserSession.getInstance() != null) {
			if(!UserSession.getInstance().isRememberme()) {UserSession.cleanUserSession();}
		}
		
		statutabonne.textProperty().set("");
		actionabonne.textProperty().set("-------------------------------------");
		StringBinding getstatus = new StringBinding() {
			{
				bind(mailabonne.textProperty());
			}

			@Override
			protected String computeValue() {
				// TODO Auto-generated method stub
				//System.out.println(mailabonne.getText());
				return computestatus(mailabonne.getText());
			}

		};
		StringBinding getaction = new StringBinding() {
			{
				bind(statutabonne.textProperty());
			}

			@Override
			protected String computeValue() {
				// TODO Auto-generated method stub
				return computeaction(statutabonne.getText());
			}

		};
		statutabonne.textProperty().bind(getstatus);
		actionabonne.textProperty().bind(getaction);

	}

}
