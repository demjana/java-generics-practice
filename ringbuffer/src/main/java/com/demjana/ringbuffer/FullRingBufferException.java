package com.demjana.ringbuffer;

public class FullRingBufferException extends RuntimeException {
  public FullRingBufferException(String message) {
    super(message);
  }
}