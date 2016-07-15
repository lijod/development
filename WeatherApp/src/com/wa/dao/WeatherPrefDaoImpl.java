package com.wa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.wa.model.WeatherPrefPK;
import com.wa.model.WeatherPreference;

@Service
public class WeatherPrefDaoImpl implements WeatherPrefDao{
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public WeatherPreference addPreference(long userId, String zipcode, boolean isLocal, String name) {
    	WeatherPreference pref = new WeatherPreference(new WeatherPrefPK(userId, zipcode), isLocal, name); 
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	session.persist(pref);
    	tx.commit();
    	
    	return pref;
    }

    @Override
	public void removePreference(WeatherPreference pref) {
		// TODO Auto-generated method stub
	}

	@Override
	public void editPreference(WeatherPreference pref, String name) {
		// TODO Auto-generated method stub
	}

	
}
