package net.jescort.web.servlet.controller.auth;

import net.gelif.kernel.core.validation.constraint.FieldMatch;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-19
 * Time: 下午7:57
 */
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm", message = "{validator.password.not_match}")
})
public class PasswordCommand
{
    @NotBlank(message = "{validator.old_password.not_blank}")
    private String oldPassword;

    @NotBlank(message = "{validator.password.not_blank}")
    @Size(min = 5, max = 16, message = "{validator.password.illegal}")
    private String password;

    @NotBlank(message = "{validator.passwordConfirm.not_blank}")
    private String passwordConfirm;

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
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
}
