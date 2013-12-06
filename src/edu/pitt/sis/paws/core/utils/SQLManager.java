package edu.pitt.sis.paws.core.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLManager
{
	private DataSource ds;
	
	public SQLManager(String context)
	{
	try
		{
			Context initCtx = new InitialContext();
			Object obj =  initCtx.lookup(context);
			ds = (DataSource)obj;
			
			if (ds == null)
				throw new Exception("PAWS-Core: Failure to Create Datasource");
		}
		catch(SQLException ex)
		{
			while (ex != null)
			{
				System.out.println ("SQL Exception: " + ex.getMessage ());
				ex = ex.getNextException ();
			}
		}
		catch(NamingException ex) { ex.printStackTrace(); }
		catch(Exception ex) { ex.printStackTrace(); }
	}// end of -- SQLManager
	
	public Connection getConnection() throws SQLException
	{
		if(ds == null)
			throw new SQLException("KT: Failure to Create Datasource");
		return ds.getConnection();
	}

//	public static ResultSet executeStatement(PreparedStatement statement)
//			throws SQLException
//	{
//		return statement.executeQuery();
//	}
//	
//	public static void executeUpdate(Connection conn, String query)
//			throws SQLException
//	{
//		PreparedStatement statement = conn.prepareStatement(query);
//		statement.executeUpdate();
//		statement.close();
//		statement = null;
//	}
	
//	public void freeConnection(Connection conn)
//	{
//		try { if (conn != null) conn.close(); }
//		catch (SQLException e) { e.printStackTrace(System.err); }
//	}
	
	
	/** Function makes string safe for the database by escaping quotes etc.
	 * @param _str - incoming string
	 * @return
	 */
	public static String stringUnquote(String _str)
	{
		return _str.replaceAll("'", "\\\\'").replaceAll("\\x88", "").replaceAll("\\x80", "");
	}

	/** Function makes string safe for the database by removing line breaks
	 * @param _str
	 * @return
	 */
	public static String stringUnbreak(String _str)
	{
		return _str.replaceAll("\\r*\\n*", "");
	}	 

	/** Function turns null string into empty
	 * @param _str
	 * @return
	 */
	public static String stringUnnull(String _str)
	{
		return (_str == null)?"":_str;
	}
	
	/*
	public static void recycleObjects(ArrayList<Connection> conn, ArrayList<PreparedStatement> stmt, ArrayList<ResultSet> rs)
	{
		if(rs!=null && rs.size()>0)
		{// if rs not empty
			
			for(Iterator<ResultSet> iter = rs.iterator();iter.hasNext();)
			{
				ResultSet _rs = iter.next();
				if (_rs != null)
				{
					try { _rs.close(); } catch (SQLException e) { ; }
					_rs = null;
				}
			}
		}// end of -- if rs not empty

		if(stmt!=null && stmt.size()>0)
		{// if stmt not empty
			
			for(Iterator<PreparedStatement> iter = stmt.iterator();iter.hasNext();)
			{
				PreparedStatement _stt = iter.next();
				if (_stt != null)
				{
					try { _stt.close(); } catch (SQLException e) { ; }
					_stt = null;
				}
			}
		}// end of -- if stmt not empty
		
		if(conn!=null && conn.size()>0)
		{// if conn not empty
			
			for(Iterator<Connection> iter = conn.iterator();iter.hasNext();)
			{
				Connection _con = iter.next();
				if (_con != null)
				{
					try { _con.close(); } catch (SQLException e) { ; }
					_con = null;
				}
			}
		}// end of -- if conn not empty
	}
	/**/
	
	public static void recycleObjects(ArrayList<Connection> conn, ArrayList<Statement> stmt, ArrayList<ResultSet> rs)
	{
		if(rs!=null && rs.size()>0)
		{// if rs not empty
			
			for(Iterator<ResultSet> iter = rs.iterator();iter.hasNext();)
			{
				ResultSet _rs = iter.next();
				if (_rs != null)
				{
					try { _rs.close(); } catch (SQLException e) { ; }
					_rs = null;
				}
			}
		}// end of -- if rs not empty

		if(stmt!=null && stmt.size()>0)
		{// if stmt not empty
			
			for(Iterator<Statement> iter = stmt.iterator();iter.hasNext();)
			{
				Statement _stt = iter.next();
				if (_stt != null)
				{
					try { _stt.close(); } catch (SQLException e) { ; }
					_stt = null;
				}
			}
		}// end of -- if stmt not empty
		
		if(conn!=null && conn.size()>0)
		{// if conn not empty
			
			for(Iterator<Connection> iter = conn.iterator();iter.hasNext();)
			{
				Connection _con = iter.next();
				if (_con != null)
				{
					try { _con.close(); } catch (SQLException e) { ; }
					_con = null;
				}
			}
		}// end of -- if conn not empty
	}
	
	
	public static void recycleObjects(Connection _conn, PreparedStatement _stmt, ResultSet _rs)
	{
		if (_rs != null)
		{
			try { _rs.close(); } catch (SQLException e) { ; }
			_rs = null;
		}
		if (_stmt != null)
		{
			try { _stmt.close(); } catch (SQLException e) { ; }
			_stmt = null;
		}
		if (_conn != null)
		{
			try { _conn.close(); } catch (SQLException e) { ; }
			_conn = null;
		}
	}

	public static void recycleObjects(Connection _conn, Statement _stmt, ResultSet _rs)
	{
		if (_rs != null)
		{
			try { _rs.close(); } catch (SQLException e) { ; }
			_rs = null;
		}
		if (_stmt != null)
		{
			try { _stmt.close(); } catch (SQLException e) { ; }
			_stmt = null;
		}
		if (_conn != null)
		{
			try { _conn.close(); } catch (SQLException e) { ; }
			_conn = null;
		}
	}
}
