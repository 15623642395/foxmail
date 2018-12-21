package org.crazyit.foxmail;

import org.crazyit.foxmail.ui.LoginFrame;

/**
 * 程序入口
 * 邮件发送，带界面版
 * 操作步骤：
 * 1、运行main方法
 * 2、随便输入一个用户名(用一个就够了,邮箱随便添加)
 * 	已经配置了zhuhao用户名的邮箱
 * 3、在设置按钮中设置邮箱smtp、pop3等信息
 * 	注意：发送邮件时465端口是SSL发送，25端口是非SSL发送
 * 4、可以使用foxmail用户名登陆，已设置
 * 
 */
public class Main {

	/**
	 * 主启动类
	 * @param args
	 */
	public static void main(String[] args) {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

}
