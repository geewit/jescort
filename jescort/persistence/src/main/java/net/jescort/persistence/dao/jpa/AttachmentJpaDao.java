package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Attachment;
import net.jescort.persistence.dao.AttachmentDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("attachmentDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AttachmentJpaDao extends GenericJpaDao<Attachment, Integer> implements AttachmentDao
{
}
