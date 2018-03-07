package com.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.DB.TestConn;

/**
 * show config page
 * @author zidow
 * 2016-11-23 first design
 */
	
public class ConfigPage {


	

//	name config area
	static JLabel selDBTypeLabel = new JLabel("请选择数据库类型:");
	static JComboBox DBList = new JComboBox();
	
	
	static JLabel driverTypeLabel = new JLabel("请选择驱动类型:");
	static JComboBox DriverList = new JComboBox();
	
	
	static JLabel urlLabel = new JLabel("URL:");
	public final static JTextArea Url = new JTextArea(5,30);
	static JScrollPane UrlScroll = new JScrollPane(Url);
	
	static JLabel userNameLabel = new JLabel("用户名:");
	public final static JTextField userNameField = new JTextField(20);
			
	static JLabel pwdLabel = new JLabel("密码：");
	public final static JTextField pwdField = new JTextField(20);
			
	static JButton testButton = new JButton("测试连接");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScreenInfo sinfo = new ScreenInfo();		
		MainJFrame mframe = new MainJFrame();


//		begain -------  name MenuBar -----begain
		JMenu fileMenu = new JMenu("文件");
		JMenuItem openItem = new JMenuItem("打开连接配置");
		JMenuItem saveItem = new JMenuItem("保存连接配置");
		JMenuItem saveasItem = new JMenuItem("另存连接配置为");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveasItem);
		
		JMenu editMenu = new JMenu("编辑");
		JMenuItem copyItem = new JMenuItem("复制");
		JMenuItem cutItem = new JMenuItem("剪切");
		JMenuItem pasteItem = new JMenuItem("粘贴");
		editMenu.add(copyItem);
		editMenu.add(cutItem);
		editMenu.add(pasteItem);
		
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem aboutItem = new JMenuItem("关于");
		helpMenu.add(aboutItem);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
//		end -------  name MenuBar -----end
		
		DBList.addItem("oracle");
		DBList.addItem("mysql");
		
		DriverList.addItem("Oracle Thin");
		DriverList.addItem("Oracle OCI");
		
		testButton.setToolTipText("点击测试连接按钮测试数据库连接");
		testButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame testFrame = new JFrame("连接测试结果");
				testFrame.setVisible(true);
				testFrame.setSize(300, 100);
				testFrame.setResizable(false);
				testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				testFrame.setLocation((ScreenInfo.getScreenWidth()-testFrame.getWidth())/2, (ScreenInfo.getScreenHeight()-testFrame.getHeight())/2);
				
				JLabel testResultLabel = new JLabel("正在测试连接中...");
				testResultLabel.setVisible(true);
				JPanel testResultJpaenl = new JPanel();
				testResultJpaenl.add(testResultLabel);
				testFrame.add(testResultJpaenl);
				JDialog testDialog = new JDialog(testFrame, "连接测试结果");
				
				TestConn conn = new TestConn();
				String url = Url.getText().trim();
				String username = userNameField.getText().trim();
				String pwd = pwdField.getText().trim();
//				System.out.println(url+"//"+username+"//"+pwd);
				int constate = conn.returnResult(url, username, pwd);
//				System.out.println(constate);
				if (constate == 0){
					testResultLabel.setText("连接失败，请检查输入的URL等信息！");
				}
				else if(constate == 2){
					testResultLabel.setText("连接失败，请检查输入的URL等信息！");
				}
				else if(constate == 1){
					testResultLabel.setText("连接成功！");
				}
				
			}
			
		});
			
		JButton nextButton = new JButton("下一步");
		nextButton.setToolTipText("点击下一步进入造数界面");
		nextButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				open makeDataPage 
				MakeDataPage mdpage = new MakeDataPage();
				mdpage.init();				
				
			}
			
		});

//		add one panel, configPanel for config area
		Box hboxSelDBType = Box.createHorizontalBox();
		hboxSelDBType.add(selDBTypeLabel);
		hboxSelDBType.add(Box.createHorizontalStrut(50));
		hboxSelDBType.add(DBList);
		
		Box hboxDriverType = Box.createHorizontalBox();
		hboxDriverType.add(driverTypeLabel);
		hboxDriverType.add(Box.createHorizontalStrut(50));
		hboxDriverType.add(DriverList);
		
		Box hboxURL = Box.createHorizontalBox();
		hboxURL.add(urlLabel);
		hboxURL.add(Box.createHorizontalStrut(50));
		hboxURL.add(UrlScroll);
		
		Box hboxUserName = Box.createHorizontalBox();
		hboxUserName.add(userNameLabel);
		hboxUserName.add(Box.createHorizontalStrut(50));
		hboxUserName.add(userNameField);
		
		Box hboxPWD = Box.createHorizontalBox();
		hboxPWD.add(pwdLabel);
		hboxPWD.add(Box.createHorizontalStrut(50));
		hboxPWD.add(pwdField);
				
		Box hboxButton = Box.createHorizontalBox();
		hboxButton.add(testButton);
		hboxButton.add(Box.createHorizontalStrut(80));
		hboxButton.add(nextButton);
		
		Box vbox = Box.createVerticalBox();
		
		vbox.add(hboxSelDBType);
		vbox.add(Box.createVerticalStrut(40));
		vbox.add(hboxDriverType);
		vbox.add(Box.createVerticalStrut(40));
		vbox.add(hboxURL);
		vbox.add(Box.createVerticalStrut(40));
		vbox.add(hboxUserName);
		vbox.add(Box.createVerticalStrut(40));
		vbox.add(hboxPWD);
		vbox.add(Box.createVerticalStrut(80));
		vbox.add(hboxButton);
		
//		GridBagLayout gridBagLayOut = new GridBagLayout();
//		
//		GridBagConstraints con  = new  GridBagConstraints();
//		con.fill = GridBagConstraints.NONE;
//		con.anchor = GridBagConstraints.CENTER;
//		con.weightx = 0;
//		con.weighty = 0;
//		MyPanel configPanel = new MyPanel();
//		configPanel.setLayout(gridBagLayOut);
//		
//		configPanel.add(selDBTypeLabel,con,0,0,1,1);		
//		configPanel.add(DBList,con,1,0,1,1);
//		configPanel.add(driverTypeLabel,con,0,1,1,1);
//		configPanel.add(DriverList,con,1,1,1,1);
//		configPanel.add(urlLabel,con,0,2,1,1);
//		configPanel.add(Url,con,1,2,1,1);

		
//		configPanel.add(testButton);
//		configPanel.add(nextButton);
		
		JPanel configPanel = new JPanel();
		configPanel.add(vbox,BorderLayout.CENTER);
		
		mframe.setContentPane(configPanel);
		mframe.setJMenuBar(menuBar);
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.setVisible(true);
		mframe.setResizable(false);
		mframe.setLocation((sinfo.getScreenWidth()-mframe.getDEFAULT_WIDTH())/2, (sinfo.getScreenHeight()-mframe.getDEFAULT_HEIGHT())/2);
		mframe.setTitle("造数工具-----配置界面");	
		
	}

}

/**
 *  name  my panel
 * @author zidow
 * 2016-12-01 first design
 * 
 */

//class MyPanel extends JPanel{
//	
//	public void add(Component c,GridBagConstraints con,int x,int y,int w,int h){
//		con.gridx = x;
//		con.gridy = y;
//		con.gridwidth = w;
//		con.gridheight = h;
//		add(c,con);
//	}
//}



