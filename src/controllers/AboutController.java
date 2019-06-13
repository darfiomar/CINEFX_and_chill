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
		credits.setText("Réalisation :\r\n" + 
				" - Darfi Omar\r\n" + 
				" - Touil Zakaria\r\n" + 
				" - ElMahfoudi Badr\r\n" + 
				" - Dbib Abdellah\r\n" + 
				"\r\n" + 
				"Encadrement :\r\n" + 
				" - Abdellaziz Walid");
		abouttheproject.setText("	Ce travail a été réalisé dans le cadre de notre mini projet de 30 jours au sein de l'Ecole Nationale des Sciences Appliquées d'Agadir. Durant le cours et TP nous avons acquis de nouvelles informations et techniques bien sur le plan théorique que pratique et surtout durant la réalisation du projet. En effet, nous avons eu l’opportunité de mettre en œuvre une grande partie de ce qu'on a appris et de découvrir les technologies utilisées dans la réalisation des applications similaires à celle que nous avons réalisé. Nous avons eu aussi l’occasion d’exploiter au mieux les outils de conception tels qu’UML et pour l’élaboration des différents diagrammes.\r\n" + 
				"- Apports du projet en terme scientifique :\r\n" + 
				"Ce projet nous a aidé a apprendre beaucoup d'aspects au niveau de l'analyse, conception et architecture logiciel. Grace a Visual Paradigm on a pu modéliser des diagrammes qui répondent aux besoins indiquées dans le cahier de charge, il s'agit donc de l'une des pratiques professionnelles.\r\n" + 
				"- Apports du projet en terme technique : \r\n" + 
				"Nous avons appris à gérer les différentes parties du code logiciel grâce à l’architecture MVC. Ceci dit, le pattern DAO nous a permis de communiquer avec la base de données au niveau ‘Models’. Il est aussi intéressant de mentionner que nous avons appris a créer nos interfaces graphique (GUI) grâce au javafx/FXML et les charger dans les classes ‘Views’, ainsi la partie ‘Controllers’ via nos contrôleurs qui réagissent pour répondre aux actions des acteurs.\r\n" + 
				"- Apports du projet au niveau humain :\r\n" + 
				"Encore une fois, on s'est inspiré du l'origine d'MVC pour gérer ce côté. Ceci se présente dans les taches indépendantes que les membres d'équipe ont fait, puis monté par un membre contrôleur qui, à son rôle, intervient dans tous les côtés du projets et communique avec les différents membre de l'équipe.\r\n");
	}

}
