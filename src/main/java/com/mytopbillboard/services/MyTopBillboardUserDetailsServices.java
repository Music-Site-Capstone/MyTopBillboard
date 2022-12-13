package com.mytopbillboard.services;

import com.mytopbillboard.models.MyTopBillboardUserDetails;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyTopBillboardUserDetailsServices implements UserDetailsService {

    public final UserRepository userDao;

    public MyTopBillboardUserDetailsServices(UserRepository userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User details not found for user:" + username);
        } else {
            return new MyTopBillboardUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        }

    }



}
