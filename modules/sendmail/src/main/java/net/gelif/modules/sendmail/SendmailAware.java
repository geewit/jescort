package net.gelif.modules.sendmail;

import org.springframework.mail.SimpleMailMessage;

public interface SendmailAware
{
    public void setMailService(AbstractSendmailService mailService);
    public void setMailMessage(SimpleMailMessage mailMessage);
}
