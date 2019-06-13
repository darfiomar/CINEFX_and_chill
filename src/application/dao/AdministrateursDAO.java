package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.*;

public class AdministrateursDAO extends DAO<Administrateurs> {

	public AdministrateursDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Administrateurs obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Administrateurs obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Administrateurs obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Administrateurs find(int id) {
		// TODO Auto-generated method stub
		Administrateurs admin = new Administrateurs();
		String query = "SELECT * FROM administrateurs WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {

				admin = new Administrateurs(id, result.getString("NomAdmin"),
						result.getString("PrenomAdmin"), result.getString("EmailAdmin"),
						result.getString("PasswordAdmin"), result.getString("PhoneAdmin"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	public Administrateurs findids(String email,String pass) {
		// TODO Auto-generated method stub
		Administrateurs admin = new Administrateurs();
		String query = "SELECT * FROM administrateurs WHERE EmailAdmin = ? AND PasswordAdmin = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, pass);
			ResultSet result = statement.executeQuery();

			if (result.first()) {

				admin = new Administrateurs(result.getInt("ID"), result.getString("NomAdmin"),
						result.getString("PrenomAdmin"), result.getString("EmailAdmin"),
						result.getString("PasswordAdmin"), result.getString("PhoneAdmin"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
