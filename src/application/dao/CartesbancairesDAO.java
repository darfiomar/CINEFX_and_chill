package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import application.*;

public class CartesbancairesDAO extends DAO<Cartesbancaires> {

	public CartesbancairesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Cartesbancaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cartesbancaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cartesbancaires obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cartesbancaires find(int id) {
		// TODO Auto-generated method stub
		Cartesbancaires carte = new Cartesbancaires();
		String query = "SELECT * FROM cartesbancaires WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				carte = new Cartesbancaires(id, result.getString("NumeroCarte"),result.getString("DECarte"),result.getInt("CryptoCarte"),result.getDouble("SoldeCarte"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carte;
	}
	public Cartesbancaires findcard(String numero, String de, int crypto) {
		// TODO Auto-generated method stub
		Cartesbancaires carte = new Cartesbancaires();
		String query = "SELECT * FROM cartesbancaires WHERE NumeroCarte = ? AND DECarte = ? AND CryptoCarte = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, numero);
			statement.setString(2, de);
			statement.setInt(3, crypto);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				
				carte = new Cartesbancaires( result.getInt("ID"), numero, de, crypto, result.getDouble("SoldeCarte"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carte;
	}
	public void transaction(Cartesbancaires c, Double prix) {
		String query= "UPDATE `cartesbancaires` SET `SoldeCarte` = ?  WHERE ID = ?";
		if(c.getSoldeCarte()>prix) {
			try {
				java.sql.PreparedStatement statement = this.connect.prepareStatement(query);

				statement.setDouble(1, c.getSoldeCarte() - prix);
				statement.setInt(2, c.getIDCARTE());

				boolean isdone = statement.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
