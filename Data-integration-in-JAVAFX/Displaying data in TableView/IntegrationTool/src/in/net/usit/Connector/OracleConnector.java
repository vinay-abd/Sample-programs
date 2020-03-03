package in.net.usit.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnector 
{
	public static Connection isConnected() throws SQLException 
	{
		Connection connection;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			if(connection!=null)
			{
				return connection;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


}
