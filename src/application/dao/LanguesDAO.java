package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class LanguesDAO extends DAO<Langues> {

	public LanguesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Langues obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Langues obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Langues obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Langues find(int id) {
		// TODO Auto-generated method stub
		Langues langue = new Langues();
		String query = "SELECT * FROM langues WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				langue = new Langues(id, result.getString("Langue"),result.getString("Iso6391"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return langue;
	}
	
}
