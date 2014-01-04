package Entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
	private static Connection Connection;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetdegroupe","root","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return Connection;
	}
	public static void setConnection(Connection connection) {
		Connection = connection;
	}

}