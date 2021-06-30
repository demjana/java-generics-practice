package com.demjana.ringbuffer;

public class EmptyRingBufferException extends RuntimeException {
  public EmptyRingBufferException(String message) {
    super(message);
  }
}