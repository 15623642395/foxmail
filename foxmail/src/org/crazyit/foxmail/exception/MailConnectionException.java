package org.crazyit.foxmail.exception;

/**
 * 邮箱连接异常
 * .
 */
@SuppressWarnings("serial")
public class MailConnectionException extends RuntimeException {

	public MailConnectionException(String s) {
		super(s);
	}
}
