package com.opal.brew.users;

import com.opal.brew.users.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

  MyUser findByUsername(String username);
}
