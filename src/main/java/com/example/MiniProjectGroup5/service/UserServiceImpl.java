package com.example.MiniProjectGroup5.service;

import com.example.MiniProjectGroup5.enums.UserType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.model.User;
import com.example.MiniProjectGroup5.repository.UserRepository;
import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

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

    @Override
    public User saveUser(User user) throws RecordNotFoundException {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Long userId) throws RecordNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Page<User> findByRole(UserType userType, Pageable pageable) throws RecordNotFoundException {
        List<User> userWithSpecificType = userRepository.findAll(pageable)
                .stream()
                .filter(user -> user.getType().equals(userType)).toList();
        return new PageImpl<>(userWithSpecificType);
    }

    @Override
    public void deleteUser(Long userId) throws RecordNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if  (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RecordNotFoundException();
        }

    }
}