package net.jescort.persistence.dao;


import net.jescort.domain.forum.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer>
{

}
