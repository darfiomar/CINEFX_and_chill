
package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.List;
import application.Films;
import application.dao.FilmsDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MoviesController implements Initializable {
	String datedaujourdhui = "Le ";
	
	@FXML
	private ScrollPane moviespane;

	public ScrollPane getMoviespane() {
		return moviespane;
	}

	public void setMoviespane(ScrollPane moviespane) {
		this.moviespane = moviespane;
	}

	@FXML
	private VBox mainvb;

	public VBox getMainvb() {
		return mainvb;
	}

	public void setMainvb(VBox mainvb) {
		this.mainvb = mainvb;
	}

	@FXML
	private Label currentdate;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(UserSession.getInstance() != null) {
			if(!UserSession.getInstance().isRememberme()) {UserSession.cleanUserSession();}
		}
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	datedaujourdhui += (String) dateFormat.format(date);
    	currentdate.setText(datedaujourdhui);
    	DBconnection db = new DBconnection();
    	FilmsDAO films = new FilmsDAO(db.getConnection());
    	Set<Films> filmset = new HashSet<Films>(films.findall());
		for(Films film : filmset){
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/Movie.fxml"));
				AnchorPane movie = (AnchorPane) loader.load();
				MovieController moviecontroller = loader.getController();
				moviecontroller.setMovieimage(film.getImageFilm());
				moviecontroller.setMovietitle(film.getNomFilm());
				moviecontroller.setMoviegenre(film.getGenresFilm());
				moviecontroller.setMovierelease(film.getReleaseFilm());
				moviecontroller.setMovieimdb(film.getImdbFilm());
				moviecontroller.setMoviesynopsis(film.getSynopsisFilm());
				moviecontroller.setMoviepays(film.getLepays().getPays());
				moviecontroller.setMovieinfo(film.getIDFILM());
				mainvb.getChildren().add(movie);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
}
