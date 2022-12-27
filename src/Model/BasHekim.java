package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User {
	
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public BasHekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);

	}

	public BasHekim() {
	}

	
	public ArrayList<User> getDoctorList() throws SQLException {
		
		ArrayList<User> list = new ArrayList<>();
		User obj;
		st = con.createStatement();
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;

	}
	
	public boolean addDoctor(String tcno,String password,String name) throws SQLException {
		
		String query = "INSERT INTO user "+ "(tcno,password,name,type) VALUES " + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key =true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(key == true) 
			return true;
		else 
			return false;
			
	}
	
public boolean deleteDoctor(int id) throws SQLException {
		
		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key =true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(key == true) 
			return true;
		else 
			return false;
			
	}

public boolean updateDoctor(int id,String tcno, String password,String name) throws SQLException {
	
	String query = "UPDATE user SET name = ? , tcno = ?,password = ? WHERE id = ?";
	boolean key = false;
	try {
		st = con.createStatement();
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, tcno);
		preparedStatement.setString(3, password);
		preparedStatement.setInt(4, id);
		preparedStatement.executeUpdate();
		key =true;
	}catch(Exception e){
		e.printStackTrace();
	}
	
	if(key == true) 
		return true;
	else 
		return false;
		
}
	
	
	
}
