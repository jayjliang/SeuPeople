package SQLoperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlOpeartion {
	String url;
	String username;
	String password;
	Connection conn;
	Statement sql;
	private boolean flag;
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public mysqlOpeartion(String url,String username,String password) {
		this.url=url;
		this.username=username;
		this.password=password;
		flag=false;
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	 
	public void Connect(){
		flag=true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC error?");
			
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			sql=(Statement)conn.createStatement();
			//if (!conn.isClosed())
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connect to sql error");
			
		}

	}
	/*
	 * 
	 * 
	 * 
	 */
	public Connection getConnection(){
		return conn;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public String Operation(String querystring)
	{
		if(flag==false)
			Connect();
		try {
			if(sql.execute(querystring)){
			ResultSet resultSet=sql.getResultSet();
			resultSet.next();
			String passwordString=resultSet.getString(1);
			return passwordString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void close(){
		try {
			conn.close();
			sql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	


