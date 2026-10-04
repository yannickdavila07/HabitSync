package br.com.api.habitFlow.infra.exception;

public class SecurityException extends RuntimeException {
  public SecurityException(String message) {
    super(message);
  }
}
