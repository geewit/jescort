package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.PostEdit;
import net.jescort.persistence.dao.PostEditDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Repository("postEditDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PostEditJpaDao extends GenericJpaDao<PostEdit, Integer> implements PostEditDao
{
}
