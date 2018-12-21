package org.crazyit.foxmail.mail;

import org.crazyit.foxmail.object.Mail;
import org.crazyit.foxmail.ui.MailContext;

/**
 * 发送邮件接口
 * 
 */
public interface MailSender {

	/**
	 * 发送一封邮件并返回该邮件对象
	 * @param mail
	 * @param ctx
	 * @return
	 */
	Mail send(Mail mail, MailContext ctx);
}
