package net.jescort.persistence.dao;


import net.jescort.domain.forum.PostEdit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEditDao extends JpaRepository<PostEdit, Integer>
{
}
