package com.wa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wa.model.User;

@Service
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		return user;
	}
	
	@Override
	public User getUserById(long id) {
		
		Session session = this.sessionFactory.getCurrentSession();		
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		tx.commit();
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.openSession();
		List<User> userList = session.createQuery("from app_user").list();
		session.close();
		return userList;
	}

	@Override
	public User createUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	session.persist(user);
    	tx.commit();
    	user.setPassword(null);
		return user;
	}

	@Override
	public User getByUserName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("username", name)).uniqueResult();
		tx.commit();
		return user;
	}

}
