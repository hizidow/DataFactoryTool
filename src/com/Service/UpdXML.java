package com.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.UI.DataFactoryPage;

public class UpdXML {

	public Document GetDoc(String filename) {
		Document document = null;
		try {
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(new File(filename));  //读取XML文件,获得document对象
		} catch (Exception ex) {
		ex.printStackTrace();
		}
		return document;
		}   

//	参数 column 传递要存储的字段，DataType 传递要保存的类型，Values 传递字段值
	public UpdXML(String Column,String DataType,String[] Values,String PK,String FK){
		String schema = DataFactoryPage.jTextFieldUserName.getText().trim();
		Document newDoc = GetDoc(".\\config\\" + schema +".xml"); //得到最新的doc对象
		String sufPath = "/" + schema + Column.replace(".", "/");
//		开始更新doc对象--字段配置
		if(DataType.equals("fix")){
			List  list = newDoc.selectNodes(sufPath +"/fix");
			Iterator it = list.iterator();
			while (it.hasNext()){
				Element el = (Element) it.next();
				el.setText(Values[0]);
			}
		}else if(DataType.equals("range")){
			List  listfrom = newDoc.selectNodes(sufPath +"/FromValue");
			Iterator itfrom = listfrom.iterator();
			while (itfrom.hasNext()){
				Element elfrom = (Element) itfrom.next();
				elfrom.setText(Values[0]);
			}
			List  listto = newDoc.selectNodes(sufPath +"/ToValue");
			Iterator itto = listto.iterator();
			while (itto.hasNext()){
				Element elto = (Element) itto.next();
				elto.setText(Values[1]);
			}
		}else if(DataType.equals("random")){
			List  list = newDoc.selectNodes(sufPath +"/RandomValue");
			Iterator it = list.iterator();
			while (it.hasNext()){
				Element el = (Element) it.next();
				el.setText(Values[0]);
			}
		}else if(DataType.equals("randomChinese")){
			List  list = newDoc.selectNodes(sufPath +"/RandomChineseValue");
			Iterator it = list.iterator();
			while (it.hasNext()){
				Element el = (Element) it.next();
				el.setText(Values[0]);
			}
		}
//		更新doc对象结束--字段配置
//		开始更新doc对象--主外键字段
		if(PK.length() == 0 || PK.equals("如没有主外键关系，此处不要输入内容")){
//			do nothing
		}else{
			List  list = newDoc.selectNodes(sufPath +"/PK");
			Iterator it = list.iterator();
			while (it.hasNext()){
				Element el = (Element) it.next();
				el.setText(PK);
			}
		}
		
		if(FK.length() == 0 || FK.equals("如没有主外键关系，此处不要输入内容")){
//			do nothing
		}else{
			List  list = newDoc.selectNodes(sufPath +"/FK");
			Iterator it = list.iterator();
			while (it.hasNext()){
				Element el = (Element) it.next();
				el.setText(FK);
			}
		}
//		更新doc对象结束--主外键字段
		
//		开始把更新完成的doc对象写回到xml文件
			try {
			XMLWriter writer = new XMLWriter( new OutputStreamWriter(new FileOutputStream(".\\config\\" + schema +".xml"),"UTF-8"));
			writer.write(newDoc);
			writer.close();
			} catch (Exception ex) {
			ex.printStackTrace();
			}
//		结束更新完成的doc对象写回到xml文件
	}

}
