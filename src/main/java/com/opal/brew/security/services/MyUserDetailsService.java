package com.opal.brew.security.services;

import com.opal.brew.users.MyUser;
import com.opal.brew.users.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
  private MyUserRepository repository;

  @Autowired
  public MyUserDetailsService(MyUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (repository.findByUsername(username) != null) {
      MyUser userToLogin = repository.findByUsername(username);
      UserBuilder builder = User.withUsername(username);
      builder.password(userToLogin.getPassword());
      builder.roles(userToLogin.getRole());
      return builder.build();
    } else {
      throw new BadCredentialsException("Username or password is incorrect");
    }
  }
}
