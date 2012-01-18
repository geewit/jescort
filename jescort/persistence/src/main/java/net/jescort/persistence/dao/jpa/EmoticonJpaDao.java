package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Emoticon;
import net.jescort.persistence.dao.EmoticonDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("emoticonDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EmoticonJpaDao extends GenericJpaDao<Emoticon, Integer> implements EmoticonDao
{
    public Emoticon findByEmoticon(String emoticon)
    {
        return (Emoticon) entityManager.createQuery("SELECT t FROM  Emoticon t WHERE t.emoticon = :emoticon").setParameter("emoticon", emoticon).getSingleResult();
    }
}
