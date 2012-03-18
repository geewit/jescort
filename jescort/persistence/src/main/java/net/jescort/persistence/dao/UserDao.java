package net.jescort.persistence.dao;

import net.jescort.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;


public interface UserDao extends JpaRepository<User, String>
{
    public User findByUsername(String username);

    public String findPasswordByUsername(String username);

    public void increasePostsById(String userId);

    public void increasePostsByUsername(String username);

    public String findAvatar(String userId);

    public void insertAvatar(String avatar, String userId);

    public void updateAvatar(String avatar, String userId);

    public String findAvatarByUsername(String username);
}
