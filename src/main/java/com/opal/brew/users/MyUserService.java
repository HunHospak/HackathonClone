package com.opal.brew.users;

import com.opal.brew.security.models.AuthenticationRequest;

public interface MyUserService {

  void save(MyUser myUser);

  boolean validateAuthenticationRequest(AuthenticationRequest request);

  MyUser findByUsername(String username);
}
