package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainviewController implements Initializable {
	String mp3path = getClass().getResource("../resources/multimedia/music.mp3").toString();
    Media media = new Media(mp3path);
    MediaPlayer mp3 = new MediaPlayer(media);

	@FXML
    private AnchorPane rootp;

	@FXML
    private AnchorPane rootanchorpane;
	
	public AnchorPane getRootanchorpane() {
		return rootanchorpane;
	}

	public void setRootanchorpane(AnchorPane rootanchorpane) {
		this.rootanchorpane = rootanchorpane;
	}
	@FXML
    private AnchorPane menupane;
	
    @FXML
    private JFXButton movies;

    @FXML
    private JFXButton about;

    @FXML
    private JFXButton ticket;

    @FXML
    private JFXButton subscribe;

    @FXML
    private JFXButton admin;
    
    @FXML
    private JFXButton closebutton;
    
    @FXML
    private JFXButton minimizebutton;
    
    @FXML
    private JFXButton musicbutton;
    
    @FXML
    void loadaboutpane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/About.fxml"));
    	AnchorPane aboutpane = (AnchorPane) loader.load();
    	rootanchorpane.getChildren().setAll(aboutpane);
    }

    @FXML
    void loadadminpane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Adminlogin.fxml"));
    	AnchorPane adminpane = (AnchorPane) loader.load();
    	rootanchorpane.getChildren().setAll(adminpane);
    }

    @FXML
    void loadbuyticketpane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Buyticket.fxml"));
    	AnchorPane ticketpane = (AnchorPane) loader.load();
    	rootanchorpane.getChildren().setAll(ticketpane);
    }

    @FXML
    void loadmoviespane(ActionEvent event) throws IOException {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Movies.fxml"));
    		ScrollPane moviesp = (ScrollPane) loader.load();
    		rootanchorpane.getChildren().setAll(moviesp);
    }

    @FXML
    void loadsubscribepane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/Subscribe.fxml"));
    	AnchorPane subscribepane = (AnchorPane) loader.load();
    	rootanchorpane.getChildren().setAll(subscribepane);
    }
    @FXML
    void closepstage(ActionEvent event) throws IOException {
    	
        System.out.println("this should close this window");
        Platform.exit();
    }
    @FXML
    void minimzepstage(ActionEvent event) throws IOException{
    	Stage stage  = (Stage)this.minimizebutton.getScene().getWindow();
    	stage.setIconified(true);
    	System.out.println("this should minimize this window");
    }
    
    @FXML
    void musicplayer(ActionEvent event) throws IOException{
    	if(this.mp3.getStatus() == MediaPlayer.Status.PLAYING) { mp3.pause();System.out.println("Music Paused...");}
    	else { mp3.play();System.out.println("Music Played...");}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		mp3.setCycleCount(24);
		mp3.play();System.out.println("Playing music...");
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Movies.fxml"));
		ScrollPane moviesp = (ScrollPane) loader.load();
		rootanchorpane.getChildren().setAll(moviesp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}
	

}
