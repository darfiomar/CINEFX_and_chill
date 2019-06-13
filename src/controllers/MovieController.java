package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Films;
import application.Seances;
import application.dao.FilmsDAO;
import application.dao.SeancesDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//import javafx.scene.control.skin.ScrollPaneSkin;
public class MovieController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane moviepane;

	@FXML
	private WebView movieimage;

	@FXML
	private Label movietitle;

	@FXML
	private Label movieimdb;

	@FXML
	private Label moviesynopsis;

	@FXML
	private Label moviegenre;

	@FXML
	private Label moviepays;

	@FXML
	private Label movierelease;

	@FXML
	private JFXButton movieinfo;

	@FXML
	private JFXButton movieticket;

	@FXML
	void handleplusdinformation(ActionEvent event) {

	}

	@FXML
	void handlereserverticket(ActionEvent event) {
		DBconnection db = new DBconnection();
		FilmsDAO film = new FilmsDAO(db.getConnection());
		SeancesDAO seance = new SeancesDAO(db.getConnection());
		if(seance.findbymovie(film.findbyname(movietitle.getText())).size() > 0 ) {
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
			AnchorPane t = (AnchorPane) moviepane.getParent().getParent().getParent().getParent().getParent();
			t.getChildren().clear();
			t.getChildren().setAll(ticketpane);
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			messagebox(alert, "Séances indisponibles.", "- Il paraît qu'il n y a aucune séance pour le film que vous avez choisi. :(\n"
					+ "- Suivez nos actualités, une séance pour ce film sera probablement programmée!");
			alert.showAndWait();
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
	public void setMovieimage(String movieimagelink) {
		this.movieimage.getEngine().load(movieimagelink);
		this.movieimage.setPrefSize(180, 270);
	}

	public void setMovietitle(String movietitletext) {
		this.movietitle.setText(movietitletext);
	}

	public void setMovieimdb(double movieimdbtext) {
		this.movieimdb.setText(Double.toString(movieimdbtext));
	}

	public void setMoviesynopsis(String moviesynopsistext) {
		this.moviesynopsis.setText(moviesynopsistext);
	}

	public void setMoviegenre(String moviegenretext) {
		this.moviegenre.setText(moviegenretext);
	}

	public void setMoviepays(String moviepaystext) {
		this.moviepays.setText(moviepaystext);
	}

	public void setMovierelease(int moviereleasetext) {
		this.movierelease.setText(Integer.toString(moviereleasetext));
	}

	public void setMovieticket() {
		
	}

	public void startNewScene(AnchorPane rootpane) throws IOException {
		Scene scene = new Scene(rootpane);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
		stage.setScene(scene);
		stage.setTitle("CINEFX and Chill");
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.show();
	}


	public void setMovieinfo(int id) {
		this.movieinfo.setOnAction((ActionEvent) -> {
			DBconnection db2 = new DBconnection();
			FilmsDAO films = new FilmsDAO(db2.getConnection());
			Films film = films.find(id);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/Moviedetails.fxml"));
				AnchorPane moviedpane = (AnchorPane) loader.load();
				MoviedetailsController moviecontroller = loader.getController();
				moviecontroller.setMovieimage(film.getImageFilm());
				moviecontroller.setMovietitle(film.getNomFilm());
				moviecontroller.setMoviegenre(film.getGenresFilm());
				moviecontroller.setMovierelease(film.getReleaseFilm());
				moviecontroller.setMovieimdb(film.getImdbFilm());
				moviecontroller.setMoviesynopsis(film.getSynopsisFilm());
				moviecontroller.setMoviepays(film.getLepays().getPays());
				moviecontroller.setMovietrailer(film.getTrailerFilm());
				moviecontroller.setMovierealisateur(film.getRealisateurFilm());
				moviecontroller.setMovieacteurs(film.getActeursFilm());
				moviecontroller.setMovieduree(film.getDureeFilm());
				moviecontroller.setMovielangue(film.getLalangue().getLangue());
				moviecontroller.setMovienote(film.isProjectable() ? "Sur nos écrans." : "Indisponible");

				ScrollPane sp = (ScrollPane) moviepane.getParent().getParent().getParent().getParent();
				sp.setVbarPolicy(ScrollBarPolicy.NEVER);

				VBox parent = (VBox) moviepane.getParent();
				parent.getChildren().clear();
				parent.getChildren().setAll(moviedpane);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
