package net.jescort.persistence.dao.jpa;


import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.Email;
import net.jescort.domain.user.EmailPk;
import net.jescort.persistence.dao.EmailDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("emailDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EmailJpaDao extends GenericJpaDao<Email, EmailPk> implements EmailDao
{
}
