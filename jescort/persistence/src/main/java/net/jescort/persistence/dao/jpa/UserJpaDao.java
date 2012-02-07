package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.UserDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Blob;


@Repository("userDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserJpaDao extends GenericJpaDao<User, Integer> implements UserDao
{
    //private transient final static Log logger = LogFactory.getLog(UserJpaDao.class);

    @Override
    public User findByUsername(String username)
    {
        return (User) entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username").setParameter("username", username).getSingleResult();
    }

    @Override
    public String findPasswordByUsername(String username)
    {
        return (String) entityManager.createQuery("SELECT user.password FROM User user WHERE user.username = :username").setParameter("username", username).getSingleResult();
    }

    @Override
    public void increasePosts(Integer userId)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set posts = posts + 1 where post_id = ?");
        query.setParameter(1, userId);
        query.executeUpdate();
    }

    @Override
    public void increasePosts(String username)
    {
        Query query = entityManager.createNativeQuery("UPDATE users set posts = posts + 1 where username = ?");
        query.setParameter(1, username);
        query.executeUpdate();
    }

    @Override
    public Blob findPhoto(Integer id)
    {
        return (Blob) entityManager.createNativeQuery("SELECT user_profiles.photo FROM user_profiles WHERE users.id = ?").setParameter(1, id).getSingleResult();
    }

    @Override
    public Blob findPhotoByUsername(String username)
    {
        return (Blob) entityManager.createNativeQuery("SELECT user_profiles.photo FROM user_profiles INNER JOIN users ON user_profiles.id = users.id WHERE users.username = ?").setParameter(1, username).getSingleResult();
    }
}
