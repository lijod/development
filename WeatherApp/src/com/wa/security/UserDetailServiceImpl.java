package com.wa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wa.dao.UserDao;
import com.wa.model.User;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getByUserName(name);
        if(user == null) {
            throw new UsernameNotFoundException("no user found with " + name);
        }
        return new AppUserDetails(user);
    }
}