package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class PaysDAO extends DAO<Pays> {

	public PaysDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Pays obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pays obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pays obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pays find(int id) {
		// TODO Auto-generated method stub
		Pays pays = new Pays();
		String query = "SELECT * FROM pays WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				pays = new Pays(id, result.getString("Pays"),result.getString("codePays"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pays;
	}
	
}
