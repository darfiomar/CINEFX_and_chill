package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class SallesDAO extends DAO<Salles> {

	public SallesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Salles obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Salles obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Salles obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Salles find(int id) {
		// TODO Auto-generated method stub
		Salles salle = new Salles();
		String query = "SELECT * FROM salles WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				salle = new Salles(id, result.getString("Salle"),result.getInt("Nplaces"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}
	public Salles findbyroom(String room) {
		// TODO Auto-generated method stub
		Salles salle = new Salles();
		String query = "SELECT * FROM salles WHERE Salle = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, room);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				salle = new Salles(result.getInt("ID"), room,result.getInt("Nplaces"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}
}
