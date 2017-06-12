package xeonmic.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	private static final String DBDRIVER="org.gjt.mm.mysql.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/bootstrap";
	private static final String DBUSER="root";
	private static final String DBPASSWORD="123456";
	private Connection conn =null;
	
	public DatabaseConnection(){
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//System.out.println("连接数据库成功");
	}
	
	public Connection getConnection(){
		return this.conn;
	}
	
	public void close(){
		if(this.conn != null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
