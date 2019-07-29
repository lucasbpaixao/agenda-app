package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        
        try {
        	FileInputStream file = new FileInputStream("./configuracoes.properties");
            props.load(file);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	
        	props.setProperty("url", "jdbc:mysql://localhost:3306/agenda_contatos");
        	props.setProperty("user", "root");
        	props.setProperty("password", "root");
		}
     
        return props;
    }
	
	public static Connection connection() throws SQLException, IOException {
		
		String url;
		String user;
		String password;
		
        Properties prop = getProp();
        
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
		
		return DriverManager.getConnection(url, user, password);
	}

}
