package com.opal.brew.exceptions;

public class UnauthorizedRequestException extends RuntimeException {

  public UnauthorizedRequestException(String message) {
    super(message);
  }
}
