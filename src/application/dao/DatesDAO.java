package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class DatesDAO extends DAO<Dates> {

	public DatesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Dates obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Dates obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Dates obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dates find(int id) {
		// TODO Auto-generated method stub
		Dates date = new Dates();
		String query = "SELECT * FROM dates WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				date = new Dates(id, result.getString("Dates"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
	public Dates findbydate(String d) {
		// TODO Auto-generated method stub
		Dates date = new Dates();
		String query = "SELECT * FROM dates WHERE Dates = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, d);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				date = new Dates(result.getInt("ID"), d);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
}
