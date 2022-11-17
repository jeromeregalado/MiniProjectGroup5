package com.example.MiniProjectGroup5.service;

import com.example.MiniProjectGroup5.enums.UserType;
import com.example.MiniProjectGroup5.model.User;
import com.example.MiniProjectGroup5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Invalid User");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthority(user));
    }
    private List<SimpleGrantedAuthority> grantedAuthority(User user){
        if(user.getType().equals(UserType.ADMIN)){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}