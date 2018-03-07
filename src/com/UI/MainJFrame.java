package com.UI;

import javax.swing.JFrame;

/**
 * name a main frame
 * @author zidow
 * 2016-11-23 first design
 */

public class MainJFrame extends JFrame{
	private  int DEFAULT_WIDTH = 800;
	private  int DEFAULT_HEIGHT = 600;
	
	public int getDEFAULT_WIDTH() {
		return DEFAULT_WIDTH;
	}

	public void setDEFAULT_WIDTH(int dEFAULT_WIDTH) {
		DEFAULT_WIDTH = dEFAULT_WIDTH;
	}

	public int getDEFAULT_HEIGHT() {
		return DEFAULT_HEIGHT;
	}

	public void setDEFAULT_HEIGHT(int dEFAULT_HEIGHT) {
		DEFAULT_HEIGHT = dEFAULT_HEIGHT;
	}
	
	
	public MainJFrame(){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
	}

}
