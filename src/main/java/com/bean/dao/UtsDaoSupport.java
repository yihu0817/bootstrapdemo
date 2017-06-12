package main.java.com.bean.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public abstract class UtsDaoSupport extends HibernateDaoSupport {

    private static Logger log = LoggerFactory.getLogger(UtsDaoSupport.class);

    public <T> void save(final T object) {
        this.getHibernateTemplate().save(object);
    }

    public <T> void delete(final T object) {
        this.getHibernateTemplate().delete(object);
    }

    public <T> void update(final T object) {
        this.getHibernateTemplate().update(object);
    }

    public <T> void saveOrUpdate(final T object) {
        this.getHibernateTemplate().saveOrUpdate(object);
    }

    public <T> void deleteById(Class<T> clazz, Long id) {
        T obj = findById(clazz, id);
        if (obj != null) {
            this.delete(obj);
        } else {
            log.debug("指定的对象[CLASS:" + clazz.getCanonicalName() + "][ID:" + id + "]，不存在");
        }
    }

    public <T> T findById(Class<T> clazz, Long id) {
        return this.getHibernateTemplate().get(clazz, id);
    }

    public <T> List<T> findAll(Class<T> clazz) {
        return (List<T>) this.getHibernateTemplate().find("from " + clazz.getName());
    }

    public <T> List<T> findAll(String hql) {
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    public <T> Set<T> findAllByIds(Class<T> clazz, String[] ids) {
        Set<T> objects = new TreeSet<T>();
        for (String id : ids) {
            T obj = findById(clazz, Long.parseLong(id));
            if (obj != null) {
                objects.add(obj);
            }
        }
        return objects;
    }

    public <T> Set<T> findAllByIds(Class<T> clazz, Long[] ids) {
        Set<T> objects = new TreeSet<T>();
        for (Long id : ids) {
            T obj = findById(clazz, id);
            if (obj != null) {
                objects.add(obj);
            }
        }
        return objects;
    }

    public <T> Set<T> findAllByIds(Class<T> clazz, Collection<Long> ids) {
        Set<T> objects = new TreeSet<T>();
        for (Long id : ids) {
            T obj = findById(clazz, id);
            if (obj != null) {
                objects.add(obj);
            }
        }
        return objects;
    }

    @Autowired
    public void setTemplate(HibernateTemplate template) {
        super.setHibernateTemplate(template);
    }

    public HibernateTemplate getTemplate() {
        return super.getHibernateTemplate();
    }

}
