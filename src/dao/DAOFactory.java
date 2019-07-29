package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
	
	public static Connection connection() throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/agenda_contatos";
		String user = "root";
		String password = "lucas@1234";
		
		return DriverManager.getConnection(url, user, password);
	}

}
