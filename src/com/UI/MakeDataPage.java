package com.UI;

import javax.swing.JFrame;

public class MakeDataPage {

	public void init(){
		MainJFrame mframe = new MainJFrame();
		ScreenInfo sinfo = new ScreenInfo();
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.setVisible(true);
		mframe.setResizable(false);
		mframe.setLocation((sinfo.getScreenWidth()-mframe.getDEFAULT_WIDTH()+50)/2, (sinfo.getScreenHeight()-mframe.getDEFAULT_HEIGHT()+50)/2);
		mframe.setTitle("造数工具-----造数界面");	
	}
}
