package com.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestConn {

	public int returnResult(String url,String username,String pwd){
		int state = 0;
		try {
			Connection con = ReturnCon.getORCLConnection(url, username, pwd);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select 1 from dual");
			while(rs.next()){
				String str = rs.getString("1");
				if(str.equals("1")){
					state = 1;
				}
				else{
					state =0 ;
				}
			}
			if(!stmt.isClosed()){
				stmt.close();
			}
			if(! con.isClosed() ){
				con.close();
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state = 2;
		}
		
		return state;
	}
}
