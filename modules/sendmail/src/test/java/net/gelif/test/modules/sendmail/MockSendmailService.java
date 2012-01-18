package net.gelif.test.modules.sendmail;

import java.util.Map;

import net.gelif.modules.sendmail.AbstractSendmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

/**
 * Mock Mail Service, print mail on screen
 */
public class MockSendmailService extends AbstractSendmailService
{
    @Override
    public void send(SimpleMailMessage mailMessage) throws MailException
    {
        StringBuffer mail = new StringBuffer();
        mail.append(this.getMailHeader(mailMessage) + "\n");
        mail.append(mailMessage.getText());
        logger.info(mail.toString());
    }

    @Override
    public void send(SimpleMailMessage mailMessage, String templateName, Map<String, Object> model) throws MailException
    {
        StringBuffer mail = new StringBuffer();
        mail.append(this.getMailHeader(mailMessage) + "\n");
        mail.append(this.generateEmailContent(templateName, model));
        logger.info(mail.toString());
    }

    protected String getMailHeader(SimpleMailMessage mailMessage)
    {
        StringBuffer header = new StringBuffer();

        header.append("To: ");
        for(String to : mailMessage.getTo())
        {
            header.append(to).append(";");
        }
        header.append("\nFrom: " + mailMessage.getFrom());
        header.append("\nSubject: " + mailMessage.getSubject());
        return header.toString();
    }
}
