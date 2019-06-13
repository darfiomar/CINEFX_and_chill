package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import application.*;

public class TicketsDAO extends DAO<Tickets> {

	public TicketsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Tickets obj) {
		// TODO Auto-generated method stub
		boolean isdone = false;
		String query = "INSERT INTO `tickets`(`ClientsID`, `CartesbancairesID`, `SeancesID`, `PrixTicket`) VALUES ( ? , ? , ? , ? , ? )";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, obj.getLeclient().getIDCLIENT());
			statement.setInt(2, obj.getLacarte().getIDCARTE());
			statement.setInt(3, obj.getLaseance().getIDSEANCE());
			statement.setDouble(4, obj.getPrixTicket());
			// ResultSet result = statement.executeQuery();
			isdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdone;
	}

	public Tickets createandget(Tickets obj) {
		// TODO Auto-generated method stub
		Tickets ticket = new Tickets();
		boolean insertisdone =false;
		SeancesDAO laseance = new SeancesDAO(this.connect);
		ClientsDAO leclient = new ClientsDAO(this.connect);
		CartesbancairesDAO lacarte = new CartesbancairesDAO(this.connect);
		String query = "INSERT INTO `tickets` (`ClientsID`, `CartesbancairesID`, `SeancesID`, `PrixTicket`) VALUES ( ? , ? , ? , ? )";
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			statement.setInt(1, obj.getLeclient().getIDCLIENT());
			statement.setInt(2, obj.getLacarte().getIDCARTE());
			statement.setInt(3, obj.getLaseance().getIDSEANCE());
			statement.setDouble(4, obj.getPrixTicket());
			insertisdone = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insertisdone) {
			String query2 = "SELECT * FROM `tickets` ORDER BY ID DESC LIMIT 1 ";
			try {
				java.sql.PreparedStatement statement2 = this.connect.prepareStatement(query2);
				ResultSet result = statement2.executeQuery();
				if (result.first()) {
					String strDate;
					if (result.getTimestamp("tempsTicket") == null) {
						strDate = null;
					} else {
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à hh:mm:ss");
						strDate = dateFormat.format(result.getTimestamp("tempsTicket"));
					}

					ticket = new Tickets(laseance.find(result.getInt("SeancesID")),
							leclient.find(result.getInt("ClientsID")), lacarte.find(result.getInt("CartesbancairesID")),
							result.getInt("ID"), strDate, 1000000 - result.getInt("ID"), result.getDouble("PrixTicket"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticket;
	}

	@Override
	public boolean delete(Tickets obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Tickets obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tickets find(int id) {
		// TODO Auto-generated method stub
		Tickets ticket = new Tickets();
		return ticket;
	}
	
	public int soldtickts() {
		String query = "SELECT COUNT(*) as sales FROM tickets";
		int sales = 0;
		try {
			java.sql.PreparedStatement statement = this.connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if (result.first()) {

				sales = result.getInt("sales");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	public double ticketsmoney() {
		String query = "SELECT SUM(PrixTicket) as money FROM tickets";
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
