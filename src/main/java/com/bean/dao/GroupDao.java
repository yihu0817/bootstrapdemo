package main.java.com.bean.dao;

import main.java.com.bean.service.DataAccessObject;
import main.java.com.domain.group.Group;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@DataAccessObject
@SuppressWarnings("ALL")
public class GroupDao extends UtsDaoSupport {

    public Group findGroupByName(final String name) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Group>() {
            public Group doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(" from Group utsGroup where utsGroup.name  = :name");
                query.setParameter("name", name);
                return (Group) query.uniqueResult();
            }
        });
    }

    public Group findGroupByUuid(final String uuid) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Group>() {
            public Group doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(" from Group utsGroup where utsGroup.uuid  = :uuid");
                query.setParameter("uuid", uuid);
                return (Group) query.uniqueResult();
            }
        });
    }

    /**
     * 查询群组
     **/
    public long countAllGroups() {
        return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select count(*) from Group");
                Object result = query.uniqueResult();
                return Long.parseLong(result.toString());
            }
        });
    }

    public long countAllGroupsInCache(final Collection<Long> cache) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select count(*) from Group where id in (:groupIds)");
                query.setParameter("groupIds", cache);
                Object result = query.uniqueResult();
                return Long.parseLong(result.toString());
            }
        });
    }


    public long countAllGroupsByNameLike(final String name) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select count(*) from Group utsGroup where utsGroup.name  like :name");
                query.setParameter("name", "%" + name + "%");
                Object result = query.uniqueResult();
                return Long.parseLong(result.toString());
            }
        });
    }

    public long countAllGroupsByNameLikeInCache(final String name, final Collection<Long> cache) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select count(*) from Group utsGroup where utsGroup.name  like :name and utsGroup.id in (:groupIds)");
                query.setParameter("name", "%" + name + "%");
                query.setParameter("groupIds", cache);
                Object result = query.uniqueResult();
                return Long.parseLong(result.toString());
            }
        });
    }

    public List<Group> findAllGroupsByNameLikeInCache(final String name, final Collection<Long> cache) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Group>>() {
            public List<Group> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Group.class).add(Restrictions.like("name", name));
                criteria.add(Restrictions.in("id", cache));
                return criteria.list();
            }
        });
    }


    public boolean deleteGroup(final long id) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
            public Boolean doInHibernate(Session session) throws HibernateException {
                Query query = null;

                query = session.createQuery("delete from Group where id = ?");
                query.setLong(0, id);
                query.executeUpdate();
                return true;
            }
        });
    }


}
