package net.gelif.modules.sendmail;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public class GenericSendmailService extends AbstractSendmailService
{
    @Override
    public void send(final SimpleMailMessage mailMessage) throws MailException
    {
        try
        {
            mailSender.send(mailMessage);
        }
        catch(final MailException e)
        {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    @Override
    public void send(final SimpleMailMessage mailMessage, final String templateName, final Map<String, Object> model) throws MailException
    {
        final String content = generateEmailContent(templateName, model);
        MimeMessage mimeMessage = null;
        try
        {
            mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(mailMessage.getTo());
            if(!ArrayUtils.isEmpty(mailMessage.getBcc()))
            {
                helper.setBcc(mailMessage.getBcc());
            }
            helper.setSubject(mailMessage.getSubject());
            if(StringUtils.isBlank(mailMessage.getFrom()))
            {
                helper.setFrom(defaultFrom);
            }
            else
            {
                helper.setFrom(mailMessage.getFrom());
            }
            helper.setText(content, true);
        }
        catch(final MessagingException ex)
        {
            logger.error(ex.getMessage());
        }
        mailMessage.setText(content);
        
        mailSender.send(mimeMessage);
    }
    
    public void setFrom(String from)
    {
        defaultFrom = from;
    }
    private String defaultFrom;
}
