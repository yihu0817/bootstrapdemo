package xeonmic.dao.impl;


import xeonmic.dao.DemoDAO;
import xeonmic.vo.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemoDAOImpl implements DemoDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public DemoDAOImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public boolean doCreate(demo demo) {
		boolean flag = false;
		String sql = "INSERT INTO t_demo(type,pay,name,text	) VALUES(?,?,?,?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, demo.getType());
			this.pstmt.setDouble(2, demo.getPay());
			this.pstmt.setString(3, demo.getName());
			this.pstmt.setString(4, demo.getText());
			if(this.pstmt.executeUpdate()>0){
				flag = true;
			}
			this.pstmt.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<demo> doSearch(String keys) {
		// TODO Auto-generated method stub
		if (keys==null) {
			keys="";
		}
		String sql = "SELECT id,name,type,pay,text FROM t_demo "+keys;
		List<demo> all = new ArrayList<demo>();
		System.out.println(sql);
		 try {				
				this.pstmt = this.conn.prepareStatement(sql);		
				ResultSet rs = this.pstmt.executeQuery();
				demo demo = null;
				while(rs.next()){
					demo = new demo(rs.getInt("id"),rs.getInt("type"),rs.getDouble("pay"),rs.getString("name"),rs.getString("text"));					
					all.add(demo);
				}
				this.pstmt.close();			
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		return all;
	}

	@Override
	public boolean doDelete(int id) {
		boolean flag = false;
		String sql = "DELETE FROM t_demo WHERE id = ?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			if(this.pstmt.executeUpdate()>0){
				flag = true;
			}
			this.pstmt.close();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean doChange(demo demo) {
		boolean flag = false;
		String sql = "UPDATE t_demo SET type=?,pay=?,name=?,text=? WHERE id=?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(5, demo.getId());
			this.pstmt.setInt(1, demo.getType());
			this.pstmt.setDouble(2, demo.getPay());
			this.pstmt.setString(3, demo.getName());
			this.pstmt.setString(4, demo.getText());
			if(this.pstmt.executeUpdate()>0){
				flag = true;
			}
			this.pstmt.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}

}
