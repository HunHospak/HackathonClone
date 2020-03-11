package com.opal.brew.users;

import com.opal.brew.users.MyUser;
import com.opal.brew.users.MyUserRepository;
import com.opal.brew.security.models.AuthenticationRequest;
import javax.ws.rs.BadRequestException;

import com.opal.brew.users.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService {
  private MyUserRepository repository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public MyUserServiceImpl(MyUserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void save(MyUser myUser) {
    myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
    repository.save(myUser);
  }

  @Override
  public boolean validateAuthenticationRequest(AuthenticationRequest request) {
    if (request.getUsername() == null || request.getUsername().equals("")) {
      throw new UsernameNotFoundException("Please provide a username ");
    } else if (!validateUsername(request.getUsername())) {
      throw new BadRequestException(
          "This username is already taken. Please select another valid username");
    } else if (request.getPassword() == null || request.getPassword().equals("")) {
      throw new BadRequestException("Please provide a password ");
    } else if (request.getEmail() == null || request.getEmail().equals("")) {
      throw new BadRequestException("Please provide an email address");
    } else {
      return true;
    }
  }

  @Override
  public MyUser findByUsername(String username) {
    return repository.findByUsername(username);
  }

  private boolean validateUsername(String username) {
    return repository.findByUsername(username) == null;
  }
}
