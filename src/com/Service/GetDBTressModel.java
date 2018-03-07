package com.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.DB.ReturnCon;
import com.UI.DataFactoryPage;

public class GetDBTressModel {

	static Set<String> tabList = new HashSet<String>();
	static String url = DataFactoryPage.jTextArea1.getText().trim();
	static String username = DataFactoryPage.jTextFieldUserName.getText().trim();
	static String pwd = DataFactoryPage.jPasswordField1.getText().trim();
	static Map<String, Collection<String>> rootMap = new HashMap<String, Collection<String>>();
	static List<String> columList = new LinkedList<String>(); // save all
																// columns's
	// properties

	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;
	static DefaultMutableTreeNode schemaRoot = null ;
	
	

	public static DefaultTreeModel ReturnDBTreeModel() {
		try {
			// 设置树根节点为 用户名
			schemaRoot = new DefaultMutableTreeNode(DataFactoryPage.jTextFieldUserName.getText().trim());
			
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
			// 设置一级节点，即数据库表 二级节点，即数据库字段相关信息，展示格式（col(int,10)）
			while (tabListIterator.hasNext()) {
				String table_Name = tabListIterator.next().toUpperCase();
				DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(table_Name);
				pstmt.setString(1, table_Name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					column_info = rs.getString("column_name".toUpperCase()) + " ("
							+ rs.getString("data_type".toUpperCase()) + "," + rs.getString("data_length".toUpperCase())
							+ ")";
					// columList.add(column_info);
					tableNode.add(new DefaultMutableTreeNode(column_info));
				}
				// rootMap.put(table_Name, columList);
				schemaRoot.add(tableNode);
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
		// Map<String, Collection<String>> rootTreeMap = new TreeMap<String,
		// Collection<String>>(rootMap);
		DefaultTreeModel tree = new DefaultTreeModel(schemaRoot);
		return tree;
	}
}
