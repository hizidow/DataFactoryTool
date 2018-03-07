package com.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import com.DB.ReturnCon;
import com.UI.DataFactoryPage;

public class GetDBTree {

	// get current schema of tables and save them to a linkedList

	static Set<String> tabList = new HashSet<String>();
	static String url = DataFactoryPage.jTextArea1.getText().trim();
	static String username = DataFactoryPage.jTextFieldUserName.getText().trim();
	static String pwd = DataFactoryPage.jPasswordField1.getText().trim();
	static Map<String, Collection<String>> rootMap = new TreeMap<String, Collection<String>>();
	
	static Map<String, Collection<String>> rootTreeMap = null;  
	// properties

	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;

	public static Map<String, Collection<String>> ReturnDBTree() {
		try {

			// get connection and get all table_names and save them to tabList

			con = ReturnCon.getORCLConnection(url, username, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select table_name from user_tables");
			while (rs.next()) {
				tabList.add(rs.getString("table_name".toUpperCase()));
			}

			/**
			 * get all tables's column info(column_name,data_type,data_length)
			 * save them to columList save table_name as key and columnList as
			 * value to rootMap
			 * 
			 * @author zidow 2016-12-06 first design
			 * 
			 */
			Iterator<String> tabListIterator = tabList.iterator();
			String sql = "select column_name,data_type,data_length from user_tab_columns where table_name = ? ";
			pstmt = con.prepareStatement(sql);
			String column_info = null;

			while (tabListIterator.hasNext()) {
				String table_Name = tabListIterator.next().toUpperCase();
				pstmt.setString(1, table_Name);
				rs = pstmt.executeQuery();
				List<String> columList = new LinkedList<String>(); // save all columns's
				while (rs.next()) {
					column_info = rs.getString("column_name".toUpperCase()) + " ("
							+ rs.getString("data_type".toUpperCase()) + "," + rs.getString("data_length".toUpperCase())
							+ ")";
					columList.add(column_info);
				}
				rootMap.put(table_Name, columList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated constructor stub
		rootTreeMap = new TreeMap<String, Collection<String>>(rootMap);
		return rootTreeMap;
	}

}
