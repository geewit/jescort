package net.jescort.web.servlet.controller.auth;


import net.gelif.kernel.core.validation.constraint.FieldMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
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
        @FieldMatch(first = "password", second = "passwordConfirm", message = "{validator.password.not_match}"),
        @FieldMatch(first = "email", second = "emailConfirm", message = "{validator.email.not_match}")
})
public class RegisterCommand
{
    @NotBlank(message = "{validator.username.not_blank}")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9_\\-\\.]{4,30}[A-Za-z0-9]$", message = "{validator.username.illegal}")
    private String username;

    @NotBlank(message = "{validator.password.not_blank}")
    @Size(min = 5, max = 16, message = "{validator.password.illegal}")
    private String password;

    @NotBlank(message = "{validator.passwordConfirm.not_blank}")
    private String passwordConfirm;

    @NotBlank(message = "{validator.email.not_blank}")
    @Email(message = "{validator.email.illegal}")
    private String email;

    @NotBlank(message = "{validator.emailConfirm.not_blank}")
    @Email(message = "{validator.emailConfirm.illegal}")
    private String emailConfirm;

    private String nickname;

    @AssertTrue(message = "{validator.agreeTerms.must_be_checked}")
    private Boolean agreeTerms;

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

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public Boolean getAgreeTerms()
    {
        return agreeTerms;
    }

    public void setAgreeTerms(Boolean agreeTerms)
    {
        this.agreeTerms = agreeTerms;
    }
}
