package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ReturnCon {

	public static Connection getORCLConnection(String url,String username,String pwd) throws Exception {
		String Rurl = url;

		String Rusername = username;
		String Rpwd = pwd;

		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(Rurl, Rusername, Rpwd);
		return conn;
	}
	
	public static Connection getMysqlConnection_dft(String url,String username,String pwd)throws Exception {
		String Rurl = "jdbc:mysql://" + url
				+ "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";

		String Rusername = username;
		String Rpwd = pwd;

		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(Rurl, Rusername, Rpwd);
		return conn;
	}
}
