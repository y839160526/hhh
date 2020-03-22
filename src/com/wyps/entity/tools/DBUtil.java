package com.wyps.entity.tools;

import com.wyps.entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public User login(User u){
		User user=new User();
		String sql = "select id,username,password,vip from user where username='"+u.getUsername()+"' and password='"+u.getPassword()+"'";
		this.initialConnection();
		try {
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				user.setLogSuc(true);
				user.setId(rs.getString(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
                                user.setVip(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
        public User register(User u){
		User user=new User();
		String sql = "insert into user(username,password,email) values('"+u.getUsername()+"','"+u.getPassword()+"','"+u.getEmail()+"')";
		this.initialConnection();
		try {
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	// 自定义的初始化数据库连接的方法
	public void initialConnection() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://103.56.17.93/hams?useUnicode=true&characterEncoding=UTF-8",
							"root", "chenyi1389210");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 关闭连接的方法
	public void closeConn() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
