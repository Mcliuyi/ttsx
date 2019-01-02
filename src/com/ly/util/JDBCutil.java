package com.ly.util;

import java.sql.*;

/*
 * 鏈接數據庫
 * */
public class JDBCutil {
	//鏈接配置
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://139.199.222.121:3306/ttsx?useUnicode=true&characterEncoding=UTF-8";
	private final static String NAME = "ttsx";
	private final static String PASSWORD = "ttsx";

	private Connection conn = null;
	private PreparedStatement pstat = null;
	//數據庫查詢結果集
	private ResultSet rs = null;
	
	public JDBCutil() {
		getConnect();
	}
	
	/*
	 * 鏈接數據庫
	 * */
	public void getConnect() {
		
		try {
			// 加載驅動
			Class.forName(DRIVER);
			//鏈接
			this.conn = DriverManager.getConnection(this.URL, this.NAME, this.PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败失败");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("数据库链接失败");
			e.printStackTrace();
		}
	}
	
	/*
	 * 關閉鏈接
	 * */
	public void close() {
		
		try {
			this.pstat.close();
			this.conn.close();
		} catch (SQLException e) {
			System.out.println("关闭链接失败");
			e.printStackTrace();
		}
	}
	
	/*
	 * 查詢
	 * */
	public ResultSet query(String sql, Object... object) throws SQLException {

		this.pstat = this.conn.prepareStatement(sql);

		System.out.println(object);
		for(int i = 1; i <object.length +1;i++) {
			this.pstat.setObject(i, object[i-1]);
		}
		System.out.println("query sql: "+ this.pstat.toString());
		this.rs = this.pstat.executeQuery();

		return rs;
	}
	
	/*
	 * 修改
	 * */
	public boolean update(String sql, Object... object) throws SQLException {

		int rlt;
		System.out.println("JDBCutil update方法");
		this.pstat = this.conn.prepareStatement(sql);
		for(int i = 1; i <object.length +1;i++) {
			this.pstat.setObject(i, object[i-1]);
		}
		System.out.println("update sql: "+ this.pstat.toString());
		rlt = this.pstat.executeUpdate();
		if(rlt > 0) {
			return true;
		}else {
			return false;
		}
	}

	
	
}
