package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.dao.FilmsDAO;
import application.dao.SeancesDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MoviedetailsController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane moviedpane;

	@FXML
	private Label movietitle;

	@FXML
	private Label movieimdb;

	@FXML
	private WebView movietrailer;

	@FXML
	private WebView movieimage;

	@FXML
	private JFXButton moviebooker;

	@FXML
	private Label moviesynopsis;

	@FXML
	private Label moviegenre;

	@FXML
	private Label movierealisateur;

	@FXML
	private Label movieacteurs;

	@FXML
	private Label movienote;

	@FXML
	private Label movierelease;

	@FXML
	private Label movielangue;

	@FXML
	private Label moviepays;

	@FXML
	private Label movieduree;

	public void setMovietitle(String movietitletext) {
		this.movietitle.setText(movietitletext);
	}

	public void setMovieimdb(Double movieimdbtext) {
		this.movieimdb.setText(Double.toString(movieimdbtext));
	}

	public void setMovietrailer(String movietrailerurl) {
		this.movietrailer.getEngine().load(movietrailerurl);
		this.movietrailer.setPrefSize(560, 315);
	}

	public void setMovieimage(String movieimageurl) {
		this.movieimage.getEngine().load(movieimageurl);
		this.movieimage.setPrefSize(170, 255);
	}

	public void setMoviebooker(String moviebookerid) {
		// this.moviebooker.setText(moviebookerid);
	}

	public void setMoviesynopsis(String moviesynopsistext) {
		this.moviesynopsis.setText(moviesynopsistext);
	}

	public void setMoviegenre(String moviegenretext) {
		this.moviegenre.setText(moviegenretext);
	}

	public void setMovierealisateur(String movierealisateurtext) {
		this.movierealisateur.setText(movierealisateurtext);
	}

	public void setMovieacteurs(String movieacteurstext) {
		this.movieacteurs.setText(movieacteurstext);
	}

	public void setMovienote(String movienotetext) {
		this.movienote.setText(movienotetext);
	}

	public void setMovierelease(int moviereleasetext) {
		this.movierelease.setText(Integer.toString(moviereleasetext));
	}

	public void setMovielangue(String movielanguetext) {
		this.movielangue.setText(movielanguetext);
	}

	public void setMoviepays(String moviepaystext) {
		this.moviepays.setText(moviepaystext);
	}

	public void setMovieduree(int moviedureetext) {
		this.movieduree.setText(Integer.toString(moviedureetext) + " min");
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
	void bookthemovie(ActionEvent event) {
		DBconnection db = new DBconnection();
		FilmsDAO film = new FilmsDAO(db.getConnection());
		SeancesDAO seance = new SeancesDAO(db.getConnection());
		if (seance.findbymovie(film.findbyname(movietitle.getText())).size() > 0) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/Buyticket.fxml"));
			AnchorPane ticketpane = null;
			try {
				ticketpane = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BuyticketController buyticket = loader.getController();
			buyticket.setSelectmovie(movietitle.getText());
			AnchorPane t = (AnchorPane) moviedpane.getParent().getParent().getParent().getParent().getParent()
					.getParent();
			t.getChildren().clear();
			t.getChildren().setAll(ticketpane);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			messagebox(alert, "Séances indisponibles.",
					"- Il paraît qu'il n y a aucune séance pour le film que vous avez choisi. :(\n"
							+ "- Suivez nos actualités, une séance pour ce film sera probablement programmée!");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
