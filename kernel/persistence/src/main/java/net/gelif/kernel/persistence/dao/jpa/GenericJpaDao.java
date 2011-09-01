package net.gelif.kernel.persistence.dao.jpa;

import static org.springframework.data.jpa.repository.query.QueryUtils.COUNT_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.DELETE_ALL_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.applyAndBind;
import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;
import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.gelif.kernel.core.util.GenericsUtils;
import net.gelif.kernel.core.util.JpaEntityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.util.Assert;

public class GenericJpaDao<T extends Persistable<PK>, PK extends Serializable> implements JpaRepository<T, PK>, JpaSpecificationExecutor<T>
{
    protected final Log logger = LogFactory.getLog(this.getClass());
    
    @SuppressWarnings("unchecked")
    public GenericJpaDao()
    {
        entityClass = (Class<T>)GenericsUtils.getSuperClassGenricType(getClass(), 0);
        entityName = entityClass.getSimpleName();
    }
    
    private final Class<T> entityClass;
    private final String entityName;
    
    protected EntityManager entityManager;
    
    @PersistenceContext(unitName = "escort")
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    
    public JpaEntityInformation<T, PK> getEntityInformation()
    {
        return JpaEntityUtils.getMetadata(entityClass, entityManager);
    }
    
    protected TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        
        Root<T> root = applySpecificationToCriteria(spec, query);
        query.select(root);
        
        if(pageable != null)
        {
            query.orderBy(toOrders(pageable.getSort(), root, builder));
        }
        
        return entityManager.createQuery(query);
    }
    
    private TypedQuery<T> getQuery(Specification<T> spec, Sort sort)
    {
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        
        Root<T> root = applySpecificationToCriteria(spec, query);
        query.select(root);
        
        if(sort != null)
        {
            query.orderBy(toOrders(sort, root, builder));
        }
        
        return entityManager.createQuery(query);
    }
    
    protected TypedQuery<Long> getCountQuery(Specification<T> spec)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        
        Root<T> root = applySpecificationToCriteria(spec, query);
        query.select(builder.count(root)).distinct(true);
        
        return entityManager.createQuery(query);
    }
    
    protected <S>Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query)
    {
        Assert.notNull(query);
        Root<T> root = query.from(entityClass);
        
        if(null == spec)
        {
            return root;
        }
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Predicate predicate = spec.toPredicate(root, query, builder);
        
        if(null != predicate)
        {
            query.where(predicate);
        }
        
        return root;
    }
    
    protected String getDeleteAllQueryString()
    {
        return getQueryString(DELETE_ALL_QUERY_STRING, entityName);
    }
    
    protected Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec)
    {
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        
        Long total = getCountQuery(spec).getSingleResult();
        
        return new PageImpl<T>(query.getResultList(), pageable, total);
    }
    
    private String getCountQueryString()
    {
        String countQuery = String.format(COUNT_QUERY_STRING, "x", "%s");
        return getQueryString(countQuery, entityName);
    }
    
    public void delete(PK pk)
    {
        delete(findOne(pk));
    }
    
    public void delete(T entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
    
    public void delete(Iterable<? extends T> entities)
    {
        if(entities == null)
        {
            return;
        }
        for(T entity : entities)
        {
            delete(entity);
        }
    }
    
    public void deleteInBatch(Iterable<T> entities)
    {
        if(null == entities || !entities.iterator().hasNext())
        {
            return;
        }
        
        applyAndBind(getQueryString(DELETE_ALL_QUERY_STRING, entityName), entities, entityManager).executeUpdate();
        entityManager.clear();
    }
    
    public void deleteAll()
    {
        entityManager.createQuery(getDeleteAllQueryString()).executeUpdate();
        entityManager.clear();
    }
    
    public T findOne(PK pk)
    {
        Assert.notNull(pk, "The given id must not be null!");
        return entityManager.find(entityClass, pk);
    }
    
    public boolean exists(PK pk)
    {
        Assert.notNull(pk, "The given id must not be null!");
        return null != findOne(pk);
    }
    
    public T findOne(Specification<T> spec)
    {
        try
        {
            return getQuery(spec, (Sort)null).getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }
    }
    
    public List<T> findAll()
    {
        return getQuery(null, (Sort)null).getResultList();
    }
    
    public List<T> findAll(Sort sort)
    {
        return getQuery(null, sort).getResultList();
    }
    
    public List<T> findAll(Specification<T> spec)
    {
        return getQuery(spec, (Sort)null).getResultList();
    }
    
    public Page<T> findAll(Pageable pageable)
    {
        if(null == pageable)
        {
            return new PageImpl<T>(findAll());
        }
        
        return findAll(null, pageable);
    }
    
    public Page<T> findAll(Specification<T> spec, Pageable pageable)
    {
        TypedQuery<T> query = getQuery(spec, pageable);
        return pageable == null ? new PageImpl<T>(query.getResultList()) : readPage(query, pageable, spec);
    }
    
    public List<T> findAll(Specification<T> spec, Sort sort)
    {
        return getQuery(spec, sort).getResultList();
    }
    
    public long count()
    {
        return entityManager.createQuery(getCountQueryString(), Long.class).getSingleResult();
    }
    
    public long count(Specification<T> spec)
    {
        return getCountQuery(spec).getSingleResult();
    }
    
    public T save(T entity)
    {
        if(this.getEntityInformation().isNew(entity))
        {
            entityManager.persist(entity);
            return entity;
        }
        else
        {
            return entityManager.merge(entity);
        }
    }
    
    public T saveAndFlush(T entity)
    {
        T result = save(entity);
        flush();
        return result;
    }
    
    public List<T> save(Iterable<? extends T> entities)
    {
        List<T> result = new ArrayList<T>();
        
        if(entities == null)
        {
            return result;
        }
        
        for(T entity : entities)
        {
            result.add(save(entity));
        }
        
        return result;
    }
    
    public void flush()
    {
        entityManager.flush();
    }
}
