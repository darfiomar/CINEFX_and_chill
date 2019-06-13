package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AboutController implements Initializable{

    @FXML
    private Label abouttheproject;

    @FXML
    private Label credits;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		credits.setText("R�alisation :\r\n" + 
				" - Darfi Omar\r\n" + 
				" - Touil Zakaria\r\n" + 
				" - ElMahfoudi Badr\r\n" + 
				" - Dbib Abdellah\r\n" + 
				"\r\n" + 
				"Encadrement :\r\n" + 
				" - Abdellaziz Walid");
		abouttheproject.setText("	Ce travail a �t� r�alis� dans le cadre de notre mini projet de 30 jours au sein de l'Ecole Nationale des Sciences Appliqu�es d'Agadir. Durant le cours et TP nous avons acquis de nouvelles informations et techniques bien sur le plan th�orique que pratique et surtout durant la r�alisation du projet. En effet, nous avons eu l�opportunit� de mettre en �uvre une grande partie de ce qu'on a appris et de d�couvrir les technologies utilis�es dans la r�alisation des applications similaires � celle que nous avons r�alis�. Nous avons eu aussi l�occasion d�exploiter au mieux les outils de conception tels qu�UML et pour l��laboration des diff�rents diagrammes.\r\n" + 
				"- Apports du projet en terme scientifique :\r\n" + 
				"Ce projet nous a aid� a apprendre beaucoup d'aspects au niveau de l'analyse, conception et architecture logiciel. Grace a Visual Paradigm on a pu mod�liser des diagrammes qui r�pondent aux besoins indiqu�es dans le cahier de charge, il s'agit donc de l'une des pratiques professionnelles.\r\n" + 
				"- Apports du projet en terme technique : \r\n" + 
				"Nous avons appris � g�rer les diff�rentes parties du code logiciel gr�ce � l�architecture MVC. Ceci dit, le pattern DAO nous a permis de communiquer avec la base de donn�es au niveau �Models�. Il est aussi int�ressant de mentionner que nous avons appris a cr�er nos interfaces graphique (GUI) gr�ce au javafx/FXML et les charger dans les classes �Views�, ainsi la partie �Controllers� via nos contr�leurs qui r�agissent pour r�pondre aux actions des acteurs.\r\n" + 
				"- Apports du projet au niveau humain :\r\n" + 
				"Encore une fois, on s'est inspir� du l'origine d'MVC pour g�rer ce c�t�. Ceci se pr�sente dans les taches ind�pendantes que les membres d'�quipe ont fait, puis mont� par un membre contr�leur qui, � son r�le, intervient dans tous les c�t�s du projets et communique avec les diff�rents membre de l'�quipe.\r\n");
	}

}
