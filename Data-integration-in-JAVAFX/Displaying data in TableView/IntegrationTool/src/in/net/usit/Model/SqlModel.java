package in.net.usit.Model;

import java.sql.SQLException;

import in.net.usit.Connector.MySqlConnector;

public class SqlModel
{
	public boolean isConnected() throws SQLException
	{
		if(!MySqlConnector.isConnected().isClosed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
