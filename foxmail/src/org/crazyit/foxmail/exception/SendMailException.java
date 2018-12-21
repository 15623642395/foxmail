package org.crazyit.foxmail.exception;

/**
 * 发送邮件异常
 * .
 */
@SuppressWarnings("serial")
public class SendMailException extends RuntimeException {

	public SendMailException(String s) {
		super(s);
	}
}
