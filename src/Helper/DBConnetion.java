package Helper;
import java.sql.*;
public class DBConnetion {
	Connection c = null;
	public DBConnetion(){}
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=Strabzon61");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
