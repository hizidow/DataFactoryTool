package com.UI;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * get screen info
 * @author zidow
 * 2016-11-23 first design* 
 * 
 */

public class ScreenInfo {

	private static int screenWidth;
	private static int screenHeight;
	static Toolkit kit = Toolkit.getDefaultToolkit();
	static Dimension screenSize = kit.getScreenSize();
	
	public static int getScreenWidth() {
		screenWidth = (int) screenSize.getWidth();
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public static int getScreenHeight() {
		screenHeight = (int) screenSize.getHeight();
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
}
