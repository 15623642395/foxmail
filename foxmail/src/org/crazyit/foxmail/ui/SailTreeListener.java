package org.crazyit.foxmail.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 导航树监听器
 * 
 */
public class SailTreeListener extends MouseAdapter {
	private MainFrame mainFrame;

	public SailTreeListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void mousePressed(MouseEvent e) {
		mainFrame.select();
	}
}
