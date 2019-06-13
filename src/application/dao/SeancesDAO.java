package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import application.*;

public class SeancesDAO extends DAO<Seances> {

	public SeancesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Seances obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Seances obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Seances obj) {
		// TODO Auto-generated method stub
		return false;
	}
	Date todayDate = new Date();
	@Override
	public Seances find(int id) {
		// TODO Auto-generated method stub
		Seances seance = new Seances();
		FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		SallesDAO salle = new SallesDAO(this.connect);
		DatesDAO date = new DatesDAO(this.connect);
		HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				seance = new Seances(id, film.find(result.getInt("FilmsID")), admin.find(result.getInt("AdministrateursID")), salle.find(result.getInt("SallesID")), date.find(result.getInt("DatesID")), horaire.find(result.getInt("HorairesID")),result.getDouble("PrixSeance"),result.getInt("NReservation"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}

	public Set<Seances> findAll() {
		// TODO Auto-generated method stub
		Set<Seances> seances = new HashSet<Seances>();
		Seances seance = new Seances();
		FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		SallesDAO salle = new SallesDAO(this.connect);
		DatesDAO date = new DatesDAO(this.connect);
		HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				
				seance = new Seances(result.getInt("ID"), film.find(result.getInt("FilmsID")), admin.find(result.getInt("AdministrateursID")), salle.find(result.getInt("SallesID")), date.find(result.getInt("DatesID")), horaire.find(result.getInt("HorairesID")),result.getDouble("PrixSeance"),result.getInt("NReservation"));
				seances.add(seance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
	}
	public Set<Seances> findbymovie(Films movie) {
		// TODO Auto-generated method stub
		Set<Seances> seances = new LinkedHashSet<Seances>();
		Seances seance = new Seances();
		//FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		SallesDAO salle = new SallesDAO(this.connect);
		DatesDAO date = new DatesDAO(this.connect);
		HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances WHERE FilmsID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, movie.getIDFILM());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				seance = new Seances(result.getInt("ID"), movie, admin.find(result.getInt("AdministrateursID")), salle.find(result.getInt("SallesID")), date.find(result.getInt("DatesID")), horaire.find(result.getInt("HorairesID")),result.getDouble("PrixSeance"),result.getInt("NReservation"));
				
				Date datedelaseance = null;
				try {
					datedelaseance = new SimpleDateFormat("dd/MM/yyyy").parse(seance.getLadate().getDates());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(datedelaseance.compareTo(todayDate) > 0) {
					seances.add(seance);
				}
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
	}
	public Set<Seances> findbymovieanddate(Films movie, Dates day) {
		// TODO Auto-generated method stub
		Set<Seances> seances = new LinkedHashSet<Seances>();
		Seances seance = new Seances();
		//FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		SallesDAO salle = new SallesDAO(this.connect);
		//DatesDAO date = new DatesDAO(this.connect);
		HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances WHERE FilmsID = ? AND DatesID = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, movie.getIDFILM());
			statement.setInt(2, day.getIDDATES());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				seance = new Seances(result.getInt("ID"), movie, admin.find(result.getInt("AdministrateursID")), salle.find(result.getInt("SallesID")), day, horaire.find(result.getInt("HorairesID")),result.getDouble("PrixSeance"),result.getInt("NReservation"));
				Date datedelaseance = null;
				try {
					datedelaseance = new SimpleDateFormat("dd/MM/yyyy").parse(seance.getLadate().getDates());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(datedelaseance.compareTo(todayDate) > 0) {
					seances.add(seance);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
	}
	public Set<Seances> findbymoviedateandhoraire(Films movie, Dates day, Horaires time) {
		// TODO Auto-generated method stub
		Set<Seances> seances = new LinkedHashSet<Seances>();
		Seances seance = new Seances();
		//FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		SallesDAO salle = new SallesDAO(this.connect);
		//DatesDAO date = new DatesDAO(this.connect);
		//HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances WHERE FilmsID = ? AND DatesID = ? AND HorairesID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, movie.getIDFILM());
			statement.setInt(2, day.getIDDATES());
			statement.setInt(3, time.getIDHORAIRE());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				
				seance = new Seances(result.getInt("ID"), movie, admin.find(result.getInt("AdministrateursID")), salle.find(result.getInt("SallesID")), day, time,result.getDouble("PrixSeance"),result.getInt("NReservation"));
				Date datedelaseance = null;
				try {
					datedelaseance = new SimpleDateFormat("dd/MM/yyyy").parse(seance.getLadate().getDates());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(datedelaseance.compareTo(todayDate) > 0) {
					seances.add(seance);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
	}
	public Seances findbymoviedatehoraireandsalle(Films movie, Dates day, Horaires time, Salles room) {
		// TODO Auto-generated method stub
		Seances seance = new Seances();
		//FilmsDAO film = new FilmsDAO(this.connect);
		AdministrateursDAO admin = new AdministrateursDAO(this.connect);
		//SallesDAO salle = new SallesDAO(this.connect);
		//DatesDAO date = new DatesDAO(this.connect);
		//HorairesDAO horaire = new HorairesDAO(this.connect);
		String query = "SELECT * FROM seances WHERE FilmsID = ? AND DatesID = ? AND HorairesID = ? AND SallesID = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, movie.getIDFILM());
			statement.setInt(2, day.getIDDATES());
			statement.setInt(3, time.getIDHORAIRE());
			statement.setInt(4, room.getIDSALLE());
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				seance = new Seances( result.getInt("ID"), movie, admin.find(result.getInt("AdministrateursID")), room, day, time, result.getDouble("PrixSeance"), result.getInt("NReservation"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}
	public void setReservation(Seances s) {
		String query= "UPDATE `seances` SET `NReservation` = ?  WHERE ID = ?";
		
			try {
				java.sql.PreparedStatement statement = this.connect.prepareStatement(query);

				statement.setInt(1, s.getNReservation()+1);
				statement.setInt(2, s.getIDSEANCE());

				boolean isdone = statement.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
}
