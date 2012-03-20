package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.UserDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;


@Repository("userDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserJpaDao extends GenericJpaDao<User, String> implements UserDao
{
    //private transient final static Log logger = LogFactory.getLog(UserJpaDao.class);

    @Override
    public User findByUsername(String username)
    {
        return (User) entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username").setParameter("username", username).getSingleResult();
    }

    @Override
    public String findPasswordByUserId(String userId)
    {
        return (String) entityManager.createQuery("SELECT user.password FROM User user WHERE user.id = :id").setParameter("id", userId).getSingleResult();
    }

    @Override
    public String findPasswordByUsername(String username)
    {
        return (String) entityManager.createQuery("SELECT user.password FROM User user WHERE user.username = :username").setParameter("username", username).getSingleResult();
    }
    
    public void updatePassword(String password, String userId)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set password = ? where id = ?");
        query.setParameter(1, password);
        query.setParameter(2, userId);
        query.executeUpdate();
    }

    @Override
    public void increasePostsById(String userId)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set posts = posts + 1 where post_id = ?");
        query.setParameter(1, userId);
        query.executeUpdate();
    }

    @Override
    public void increasePostsByUsername(String username)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set posts = posts + 1 where username = ?");
        query.setParameter(1, username);
        query.executeUpdate();
    }

    @Override
    public String findAvatar(String userId)
    {
        try
        {
            return (String) entityManager.createNativeQuery("SELECT users.avatar FROM users WHERE users.id = ?").setParameter(1, userId).getSingleResult();
        }catch(NoResultException  e)
        {
            return null;
        }
    }

    @Override
    public void insertAvatar(String avatar, String userId)
    {
        User user = new User(userId);
        user.setAvatar(avatar);
        entityManager.persist(user);
    }

    @Override
    public void updateAvatar(String avatar, String userId)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set avatar = ? where id = ?");
        query.setParameter(1, avatar).setParameter(2, userId);
        query.executeUpdate();
    }

    @Override
    public String findAvatarByUsername(String username)
    {
        try
        {
            return (String) entityManager.createNativeQuery("SELECT users.avatar FROM users WHERE users.username = ?").setParameter(1, username).getSingleResult();
        }catch(NoResultException e)
        {
            return null;
        }
    }
}
