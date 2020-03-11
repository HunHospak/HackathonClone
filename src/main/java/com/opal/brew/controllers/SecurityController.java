package com.opal.brew.controllers;

import com.opal.brew.exceptions.UnauthorizedRequestException;
import com.opal.brew.users.MyUser;
import com.opal.brew.security.models.AuthenticationRequest;
import com.opal.brew.security.models.AuthenticationResponse;
import com.opal.brew.security.models.ConfirmationToken;
import com.opal.brew.security.repositories.ConfirmationTokenRepository;
import com.opal.brew.security.services.EmailSenderService;
import com.opal.brew.security.services.MyUserDetailsService;
import com.opal.brew.security.util.JwtUtil;
import com.opal.brew.users.MyUserService;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

  private AuthenticationManager authenticationManager;
  private JwtUtil jwtTokenUtil;
  private MyUserService userService;
  private MyUserDetailsService myUserDetailsService;
  private ConfirmationTokenRepository confirmationTokenRepository;
  private EmailSenderService emailSenderService;

  @Autowired
  public SecurityController(AuthenticationManager authenticationManager,
                            JwtUtil jwtTokenUtil,
                            MyUserService userService,
                            MyUserDetailsService myUserDetailsService,
                            ConfirmationTokenRepository confirmationTokenRepository,
                            EmailSenderService emailSenderService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userService = userService;
    this.myUserDetailsService = myUserDetailsService;
    this.confirmationTokenRepository = confirmationTokenRepository;
    this.emailSenderService = emailSenderService;
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> createAuthenticationToken(
          @RequestBody AuthenticationRequest authenticationRequest) {
    MyUser user = userService.findByUsername(authenticationRequest.getUsername());
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
      } catch (BadCredentialsException e) {
        throw new UnauthorizedRequestException("Incorrect username or password");
      }
      final UserDetails userDetails = myUserDetailsService
              .loadUserByUsername(authenticationRequest.getUsername());
      final String jwt = jwtTokenUtil.generateToken(userDetails);
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<?> registerNewUser(@RequestBody AuthenticationRequest request) {
    if (userService.validateAuthenticationRequest(request)) {
      MyUser newUser = new MyUser(request.getUsername(), request.getEmail(), request.getPassword(), request.getRole());
      ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
      SimpleMailMessage mailMessage = emailSenderService.createEmail(confirmationToken, newUser);
      userService.save(newUser);
      confirmationTokenRepository.save(confirmationToken);
      emailSenderService.sendEmail(mailMessage);
      return new ResponseEntity(HttpStatus.OK);
    } else {
      throw new BadRequestException("Please provide valid details");
    }
  }

  @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
    ConfirmationToken token = confirmationTokenRepository
            .findByConfirmationToken(confirmationToken);

    if (token != null) {
      MyUser user = userService.findByUsername(token.getUser().getUsername());
      user.setEnabled(true);
      userService.save(user);
      return new ResponseEntity(HttpStatus.OK);
    } else {
      throw new BadRequestException("Invalid token");
    }
  }
}
