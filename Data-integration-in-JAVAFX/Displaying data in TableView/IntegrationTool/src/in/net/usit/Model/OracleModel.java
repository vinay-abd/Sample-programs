package in.net.usit.Model;

import java.sql.SQLException;

import in.net.usit.Connector.OracleConnector;

public class OracleModel 
{
	public boolean isConnected() throws SQLException
	{
		if(!OracleConnector.isConnected().isClosed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
