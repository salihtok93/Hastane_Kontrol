package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User{
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}
	public boolean register(String tcno,String password,String name) throws SQLException {
		int key = 0;
		int count = 0;
		String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno= '"+ tcno +"'");
			while (rs.next()) {
				count++;
				Helper.showMsg("Bu TC numarası zaten kayıtlı");
				break;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();

				key = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;

	}
	
}
