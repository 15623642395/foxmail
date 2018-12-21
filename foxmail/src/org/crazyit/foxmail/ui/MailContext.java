package org.crazyit.foxmail.ui;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import org.crazyit.foxmail.exception.MailConnectionException;

/**
 * 邮箱上下文
 * 
 */
public class MailContext {
	// 系统用户
	private String user;
	// 用户帐号
	private String account;
	// 密码
	private String password;
	// smtp邮件服务器
	private String smtpHost;
	// smtp端口
	private int smtpPort;
	// pop3邮件服务器
	private String pop3Host;
	// pop3的端口
	private int pop3Port;
	// 是否进行重置信息
	private boolean reset = false;

	public MailContext(String user, String account, String password, String smtpHost, int smtpPort, String pop3Host,
			int pop3Port) {
		super();
		this.user = user;
		this.account = account;
		this.password = password;
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.pop3Host = pop3Host;
		this.pop3Port = pop3Port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getPop3Host() {
		return pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public int getPop3Port() {
		return pop3Port;
	}

	public void setPop3Port(int pop3Port) {
		this.pop3Port = pop3Port;
	}

	private Store store;

	// 定时器每10秒会自动调用一次，定时器首次调用时！this.store.isConnected()为true
	public Store getStore() {
		// 重置了信息, 设置session为null
		if (this.reset) {
			this.store = null;
			this.session = null;
			this.reset = false;
		}
		if (this.store == null || !this.store.isConnected()) {
			try {
				Properties props = System.getProperties();
				if (isSsl()) {
					props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
				}
				// 创建mail的Session
				Session session = Session.getDefaultInstance(props, getAuthenticator());
				// 使用pop3协议接收邮件
				URLName url = new URLName("pop3", getPop3Host(), getPop3Port(), null, getAccount(), getPassword());
				// 得到邮箱的存储对象
				Store store = session.getStore(url);
				store.connect();
				this.store = store;
			} catch (Exception e) {
				e.printStackTrace();
				throw new MailConnectionException("连接邮箱异常，请检查配置");
			}
		}
		return this.store;
	}

	private Session session;

	private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	// 第一次发送邮件时session为null，后面发送时session为非null
	public Session getSession() {
		// 重置了信息, 设置session为null
		if (this.reset) {
			this.session = null;
			this.store = null;
			this.reset = false;
		}
		if (this.session == null) {
			System.out.println("端口号:" + this.getSmtpPort());
			Properties props = System.getProperties();
			//SSL加密发送
			if (isSsl()) {
				// SSL加密发送
				props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			}
			// 非SSL加密发送
			props.put("mail.smtp.host", this.getSmtpHost());
			props.put("mail.smtp.port", this.getSmtpPort());
			props.put("mail.smtp.auth", true);// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
			Session sendMailSession = Session.getDefaultInstance(props, getAuthenticator());
			this.session = sendMailSession;
		}
		return this.session;
	}

	// 校验是否需要SSL安全发送
	private boolean isSsl() {
		// 没有用户名和密码绝对不需要SSL加密
		if (this.account == null || this.account.trim().equals("")) {
			return false;
		}
		// 有用户名和密码并且端口号是465的需要SSL安全发送,25端口是不需要SSL安全发送的
		if (this.account != null && !"".equals(this.account.trim()) && this.smtpPort == Integer.valueOf("465")) {
			return true;
		}
		return false;
	}

	private Authenticator getAuthenticator() {
		return new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getAccount(), getPassword());
			}
		};
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	@Override
	public String toString() {
		return "MailContext [user=" + user + ", account=" + account + ", password=" + password + ", smtpHost="
				+ smtpHost + ", smtpPort=" + smtpPort + ", pop3Host=" + pop3Host + ", pop3Port=" + pop3Port + ", reset="
				+ reset + ", store=" + store + ", session=" + session + "]";
	}

}
