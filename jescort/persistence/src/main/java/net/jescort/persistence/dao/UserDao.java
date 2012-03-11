package net.jescort.persistence.dao;

import net.jescort.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;


public interface UserDao extends JpaRepository<User, Integer>
{
    public User findByUsername(String username);

    public String findPasswordByUsername(String username);

    public void increasePosts(Integer userId);

    public void increasePosts(String username);

    public Blob findAvatar(Integer id);

    public Blob findAvatarByUsername(String username);
}
