package net.jescort.persistence.dao.jpa;


import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Category;
import net.jescort.persistence.dao.CategoryDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("categoryDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CategoryJpaDao extends GenericJpaDao<Category, Integer> implements CategoryDao
{
}
