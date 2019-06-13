package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class HorairesDAO extends DAO<Horaires> {

	public HorairesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Horaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Horaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Horaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Horaires find(int id) {
		// TODO Auto-generated method stub
		Horaires horaire = new Horaires();
		String query = "SELECT * FROM horaires WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				horaire = new Horaires(id, result.getString("Horaire"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horaire;
	}
	public Horaires findbyhoraire(String h) {
		// TODO Auto-generated method stub
		Horaires horaire = new Horaires();
		String query = "SELECT * FROM horaires WHERE Horaire = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, h);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				horaire = new Horaires(result.getInt("ID"), h);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horaire;
	}
}
