package com.opal.brew;

import com.opal.brew.setup.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrewApplication implements CommandLineRunner {

  @Autowired
  private MainService service;

  public static void main(String[] args) {
    SpringApplication.run(BrewApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  service.initialize();
  }
}
