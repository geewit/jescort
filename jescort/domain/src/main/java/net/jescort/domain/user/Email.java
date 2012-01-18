package net.jescort.domain.user;

import net.gelif.kernel.core.data.domain.AbstractPersistable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Email extends AbstractPersistable<EmailPk>
{
    private static final long serialVersionUID = 1L;

    public Email()
    {
    }

    public Email(String emailAddress)
    {
        this.emailPk = new EmailPk(emailAddress);
    }

    private EmailPk emailPk;
    private Integer priority;

    public EmailPk getEmailPk()
    {
        return emailPk;
    }

    public void setEmailPk(EmailPk emailPk)
    {
        this.emailPk = emailPk;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Email))
        {
            return false;
        }
        final Email email = (Email) object;
        return emailPk.equals(email.getEmailPk());
    }

    @Override
    public int hashCode()
    {
        return emailPk != null ? emailPk.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return emailPk.toString();
    }
}
