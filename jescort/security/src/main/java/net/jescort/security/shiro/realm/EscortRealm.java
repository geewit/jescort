package net.jescort.security.shiro.realm;

import net.jescort.domain.user.Role;
import net.jescort.domain.user.ShiroUser;
import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-6 Time: 下午10:07
 */
public class EscortRealm extends AuthorizingRealm
{
    public EscortRealm()
    {
        //setName("EscortRealm");
        setCredentialsMatcher(new HashedCredentialsMatcher("SHA-1"));
    }

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userRepository.findUserByUsername(token.getUsername());
        if (null != user)
        {
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar()), user.getPassword(), getName());
        } else
        {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
        User user = userRepository.getUser(shiroUser.getId());
        if (null != user)
        {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for(Role role : user.getRoles())
            {
                info.addRole(role.getAuthority());
                info.addStringPermissions(role.getPermissions());
            }

            return info;
        } else
        {
            return null;
        }
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }
}
