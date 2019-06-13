package application.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import application.*;

public class FilmsDAO extends DAO<Films> {
	public FilmsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean create(Films obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Films obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Films obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Films find(int id) {
		Films film = new Films();
		
		String query = "SELECT * FROM films WHERE ID = ? AND Projectable = 1";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				int paysid = result.getInt("PaysID");
				int langueid = result.getInt("LanguesID");
				int adminid = result.getInt("AdministrateursID");
				PaysDAO paysDAO = new PaysDAO(this.connect);
				LanguesDAO langueDAO = new LanguesDAO(this.connect);
				AdministrateursDAO adminDAO = new AdministrateursDAO(this.connect);
				SeancesDAO seance = new SeancesDAO(this.connect);
				film = new Films(id, result.getString("nomFilm"), result.getString("synopsisFilm"),
						result.getString("trailerFilm"), result.getString("imageFilm"), result.getString("acteursFilm"),
						result.getString("realisateurFilm"), result.getInt("dureeFilm"), result.getString("genresFilm"),
						result.getDouble("imdbFilm"), result.getInt("releaseFilm"), result.getBoolean("projectable"),
						paysDAO.find(paysid), langueDAO.find(langueid), adminDAO.find(adminid));
				film.setLesseancesf(seance.findbymovie(film));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	public Films findbyname(String name) {
		Films film = new Films();
		
		String query = "SELECT * FROM films WHERE NomFilm = ? AND Projectable = 1";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				int paysid = result.getInt("PaysID");
				int langueid = result.getInt("LanguesID");
				int adminid = result.getInt("AdministrateursID");
				PaysDAO paysDAO = new PaysDAO(this.connect);
				LanguesDAO langueDAO = new LanguesDAO(this.connect);
				AdministrateursDAO adminDAO = new AdministrateursDAO(this.connect);
				SeancesDAO seance = new SeancesDAO(this.connect);
				film = new Films(result.getInt("ID"), name, result.getString("synopsisFilm"),
						result.getString("trailerFilm"), result.getString("imageFilm"), result.getString("acteursFilm"),
						result.getString("realisateurFilm"), result.getInt("dureeFilm"), result.getString("genresFilm"),
						result.getDouble("imdbFilm"), result.getInt("releaseFilm"), result.getBoolean("projectable"),
						paysDAO.find(paysid), langueDAO.find(langueid), adminDAO.find(adminid));
				film.setLesseancesf(seance.findbymovie(film));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	public Set<Films> findall() {
		Set<Films> set = new HashSet<Films>();
		Films film = new Films();
		String query ="SELECT * FROM films WHERE Projectable = 1";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			PaysDAO paysDAO = new PaysDAO(this.connect);
			LanguesDAO langueDAO = new LanguesDAO(this.connect);
			AdministrateursDAO adminDAO = new AdministrateursDAO(this.connect);
			while(result.next()) {
				film = new Films(result.getInt("ID"), result.getString("nomFilm"), result.getString("synopsisFilm"),
						result.getString("trailerFilm"), result.getString("imageFilm"), result.getString("acteursFilm"),
						result.getString("realisateurFilm"), result.getInt("dureeFilm"), result.getString("genresFilm"),
						result.getDouble("imdbFilm"), result.getInt("releaseFilm"), result.getBoolean("projectable"),
						paysDAO.find(result.getInt("PaysID")), langueDAO.find(result.getInt("LanguesID")), adminDAO.find(result.getInt("AdministrateursID")));
				set.add(film);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
	

}
