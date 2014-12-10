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
	public String mysqlquery(String querystring,String getstring)
	{
		if(flag==false)
			Connect();
		try {
			ResultSet resultSet=sql.executeQuery(querystring);
			resultSet.next();
			String passwordString=resultSet.getString(getstring);
			return passwordString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public ResultSet mysqlquery(String CheckSql)
	{
		if(flag==false)
			Connect();
		ResultSet resultSet;
		try {
			resultSet = sql.executeQuery(CheckSql);
			return resultSet;
				
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		
	}
	
	public boolean insert(String string){
		try {
			if(sql.executeUpdate(string)!=0)
				return true;
			else
			return false;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * 
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
	


