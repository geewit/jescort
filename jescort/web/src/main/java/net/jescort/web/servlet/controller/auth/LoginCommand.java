package net.jescort.web.servlet.controller.auth;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-15
 * Time: 下午5:34
 */
public class LoginCommand
{
    @NotBlank(message = "{username.not_null}")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{4,11}", message = "{username.illegal}")
    private String username;

    @NotBlank
    @Size(min = 5, max = 12)
    private String password;

    private Boolean rememberMe;

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

    public Boolean getRememberMe()
    {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }
}
