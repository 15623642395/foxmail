package org.crazyit.foxmail.mail;

import java.util.List;

import org.crazyit.foxmail.object.Mail;
import org.crazyit.foxmail.ui.MailContext;

/**
 * 读取邮件信息的接口
 * 
 */
public interface MailLoader {
	
	/**
	 * 得到INBOX的所有邮件
	 * @param ctx 邮箱的上下文
	 * @return
	 */
	List<Mail> getMessages(MailContext ctx);
	
}
