package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;  
import java.util.Calendar;

import application.*;

public class ClientsDAO extends DAO<Clients> {

	public ClientsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

 
	@Override
	public boolean create(Clients obj) {
		// TODO Auto-generated method stub
		boolean isdone = false;
		String query= "INSERT INTO `clients`(`EmailClient`, `NomClient`, `PrenomClient`, `NAchats`, `FlagAbonne`) VALUES ( ? , ? , ? , ? , ? )";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, obj.getEmailClient());
			statement.setString(2, obj.getNomClient());
			statement.setString(3, obj.getPrenomClient());
			statement.setInt(4, 1);
			statement.setBoolean(5, false);
			//ResultSet result = statement.executeQuery();
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdone;
	}
	
	public Clients createnewabonne(Clients obj) {
		// TODO Auto-generated method stub
		boolean isdone = false;
		String query= "INSERT INTO `clients`(`EmailClient`, `NomClient`, `PrenomClient`, `NAchats`, `FlagAbonne`,`ForfaitAbonne`,`FinAbonnement`,`AdresseAbonne`, `PhoneAbonne`, `fraisabonnements`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, obj.getEmailClient());
			statement.setString(2, obj.getNomClient());
			statement.setString(3, obj.getPrenomClient());
			statement.setInt(4, 0);
			statement.setBoolean(5, true);
			statement.setInt(6, obj.getForfaitAbonne());
			
			Date todayDate = new Date();
			
			Calendar c = Calendar.getInstance();
			
				c.setTime(todayDate);
				c.add(Calendar.DATE, obj.getForfaitAbonne()); 
			
			statement.setObject(7,  c.getTime().toInstant().atZone(ZoneId.of( "Europe/Paris" )).toLocalDate());
			statement.setString(8, obj.getAdresseAbonne());
			statement.setString(9, obj.getPhoneAbonne());
			statement.setDouble(10, obj.getFraisabonnements());
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Clients client = new Clients();
		if(isdone) {
			client = findbyemail(obj.getEmailClient());
		}
		return client;
	}
	public Clients updateclienttoabonne(Clients obj) {
		// TODO Auto-generated method stub
		boolean isdone = false;
		String query= "UPDATE `clients` SET `FlagAbonne` = ? , `ForfaitAbonne` = ? , `FinAbonnement` = ? ,`AdresseAbonne` = ? , `PhoneAbonne` = ? , `fraisabonnements` = ? WHERE EmailClient = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			
			statement.setBoolean(1, true);
			statement.setInt(2, obj.getForfaitAbonne());
			Date todayDate = new Date();
			
			Calendar c = Calendar.getInstance();
			
				c.setTime(todayDate);
				c.add(Calendar.DATE, obj.getForfaitAbonne());
			statement.setObject(3,  c.getTime().toInstant().atZone(ZoneId.of( "Europe/Paris" )).toLocalDate());
			statement.setString(4, obj.getAdresseAbonne());
			statement.setString(5, obj.getPhoneAbonne());
			statement.setDouble(6, obj.getFraisabonnements());
			statement.setString(7, obj.getEmailClient());
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Clients client = new Clients();
		if(isdone) {
			client = findbyemail(obj.getEmailClient());
		}
		return client;
	}
	public Clients updateabonne(Clients obj) {
		// TODO Auto-generated method stub
		Clients temp = findbyemail(obj.getEmailClient());
		boolean isdone = false;
		String query= "UPDATE `clients` SET `FlagAbonne` = ? , `ForfaitAbonne` = ? , `FinAbonnement` = ? , `fraisabonnements` = ? WHERE EmailClient = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			
			statement.setBoolean(1, true);
			statement.setInt(2, temp.getForfaitAbonne()+obj.getForfaitAbonne());
			Date todayDate = new Date();
			Date findabonnement = null;
			try {
				findabonnement = new SimpleDateFormat("dd/MM/yyyy").parse(temp.getFinAbonnement());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar c = Calendar.getInstance();
			if(findabonnement.compareTo(todayDate) < 0) {
				c.setTime(todayDate);
				c.add(Calendar.DATE, obj.getForfaitAbonne()); 
			}else {
				c.setTime(findabonnement);
				c.add(Calendar.DATE, obj.getForfaitAbonne()); 
			}
			statement.setObject(3,c.getTime().toInstant().atZone(ZoneId.of( "Europe/Paris" )).toLocalDate());
			statement.setDouble(4, temp.getFraisabonnements() + obj.getFraisabonnements());
			statement.setString(5, obj.getEmailClient());
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Clients client = new Clients();
		if(isdone) {
			client = findbyemail(obj.getEmailClient());
		}
		return client;
	}
	@Override
	public boolean delete(Clients obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Clients obj) {
		// TODO Auto-generated method stub
		boolean isdone = false;
		String query= "UPDATE `clients` SET `NAchats` = ? WHERE EmailClient = ?";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, obj.getNAchats()+1);
			statement.setString(2, obj.getEmailClient());
			
			//ResultSet result = statement.executeQuery();
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdone;
	}

	@Override
	public Clients find(int id) {
		// TODO Auto-generated method stub
		Clients client = new Clients();
		String query = "SELECT * FROM clients WHERE ID = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				String strDate;
				if(result.getDate("FinAbonnement") == null ) {
					strDate=null;
				}else {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					strDate = dateFormat.format(result.getDate("FinAbonnement"));
				}
				client = new Clients(id, result.getInt("ForfaitAbonne"), result.getString("EmailClient"), strDate,result.getString("NomClient"), result.getString("PrenomClient"), result.getString("AdresseAbonne"), result.getString("PhoneAbonne"), result.getInt("NAchats"), result.getBoolean("FlagAbonne"),result.getDouble("fraisabonnements"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public Clients findbyemail(String email) {
		// TODO Auto-generated method stub
		Clients client = new Clients();
		String query = "SELECT * FROM clients WHERE EmailClient = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				String strDate;
				if(result.getDate("FinAbonnement") == null ) {
					strDate=null;
				}else {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					strDate = dateFormat.format(result.getDate("FinAbonnement"));
				}
				client = new Clients(result.getInt("ID"), result.getInt("ForfaitAbonne"), email, strDate,result.getString("NomClient"), result.getString("PrenomClient"), result.getString("AdresseAbonne"), result.getString("PhoneAbonne"), result.getInt("NAchats"), result.getBoolean("FlagAbonne"),result.getDouble("fraisabonnements"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public int status(String email) {
		Clients client = new Clients();
		String query = "SELECT * FROM clients WHERE EmailClient = ? ";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			if (result.first()) {
				String strDate;
				if(result.getDate("FinAbonnement") == null ) {
					strDate=null;
				}else {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					strDate = dateFormat.format(result.getDate("FinAbonnement"));
				}
				
				client = new Clients(result.getInt("ID"), result.getInt("ForfaitAbonne"), email, strDate,result.getString("NomClient"), result.getString("PrenomClient"), result.getString("AdresseAbonne"), result.getString("PhoneAbonne"), result.getInt("NAchats"), result.getBoolean("FlagAbonne"),result.getDouble("fraisabonnements"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(client.getEmailClient() == null) { return 0;}
		else {
			if(!client.isFlagAbonne()) {return 1;}
			else {
				Date findabonnement = null;
				try {
					findabonnement = new SimpleDateFormat("dd/MM/yyyy").parse(client.getFinAbonnement());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date todayDate = new Date();
				if(!(findabonnement.compareTo(todayDate) > 0))  return 2;
				else return 3;
			}
		}
		
	}
	
	public int subcriptions() {
		String query = "SELECT COUNT(*) as subs FROM clients WHERE 	FlagAbonne = true";
		int subs = 0;
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if (result.first()) {

				subs = result.getInt("subs");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subs;
	}
	public double subsmoney() {
		String query = "SELECT SUM(fraisabonnements) as money FROM clients WHERE 	FlagAbonne = true";
		double sales = 0;
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if (result.first()) {

				sales = result.getDouble("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
}
