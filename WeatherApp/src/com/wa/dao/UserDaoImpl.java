package com.wa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
		return user;
	}
	
	@Override
	public User getUserById(long id) {

		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.openSession();
		List<User> userList = session.createQuery("from app_user").list();
		session.close();
		return userList;
	}

}