package net.jescort.web.servlet.controller.auth;


import net.gelif.kernel.core.validation.constraint.FieldMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-14
 * Time: 上午11:44
 */
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "emailConfirm", message = "The email fields must match")
})
public class RegisterCommand
{
    @NotBlank
    @Size(min = 5, max = 12)
    //@Pattern(regexp = "^[a-z][a-z0-9_\\-\\.]{4,30}[a-z0-9]$")
    private String username;

    @NotBlank
    @Size(min = 5, max = 16)
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Email
    private String emailConfirm;

    private String timezone = "8";
    
    private Locale locale;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordConfirm()
    {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm)
    {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmailConfirm()
    {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm)
    {
        this.emailConfirm = emailConfirm;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }
}
