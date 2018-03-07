package com.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.UI.DataFactoryPage;

public class Tree2XML {
	Map<String, Collection<String>> tree = GetDBTree.ReturnDBTree();
	FileWriter fw = null;
	String username = DataFactoryPage.jTextFieldUserName.getText().trim();
	public void writeXML() {

		File configfile = new File("config");
		File xmlfile = new File(".\\config\\" + username + ".xml");
		try {
			if (configfile.exists() && configfile.isDirectory()) {
				if (xmlfile.exists() && xmlfile.isFile()) {
//					do nothing
				} else {
					fw = new FileWriter(".\\config\\" + username + ".xml");
					makeXML();
				}

			} else {
				configfile.mkdir();
				fw = new FileWriter(".\\config\\" + username + ".xml");
				makeXML();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void makeXML() {
		BufferedWriter bw = new BufferedWriter(fw);

		Set<String> table_name = tree.keySet();
		Iterator<String> table_nameIt = table_name.iterator();
		try {
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw.write("<" + username + ">");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (table_nameIt.hasNext()) {
			try {
				String tableName = table_nameIt.next();
				bw.write("\t<" + tableName + " flag =\"0\">");
				bw.newLine();
				List columnList = (List) tree.get(tableName);
				Iterator columnListIt = columnList.iterator();
				while (columnListIt.hasNext()) {
					bw.write("\t\t<");
					String[] col = columnListIt.next().toString().split(" ");
					String column = col[0].trim().substring(0);
					bw.write(column + ">");
					bw.newLine();
					bw.write("\t\t\t<PK></PK>");
					bw.newLine();
					bw.write("\t\t\t<FK></FK>");
					bw.newLine();
					bw.write("\t\t\t<fixValue></fixValue>");
					bw.newLine();
					bw.write("\t\t\t<FromValue></FromValue>");
					bw.newLine();
					bw.write("\t\t\t<ToValue></ToValue>");
					bw.newLine();
					bw.write("\t\t\t<RandomValue></RandomValue>");
					bw.newLine();
					bw.write("\t\t\t<RandomChineseValue></RandomChineseValue>");
					bw.newLine();
					bw.write("\t\t</" + column + ">");
					bw.newLine();

				}
				bw.write("\t</" + tableName + ">");
				bw.newLine();
				bw.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bw.write("</" + username + ">");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
