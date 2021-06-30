package com.demjana.ringbuffer;

import java.util.Comparator;

public class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {
  public int compare(T a, T b) {
    return a.compareTo(b);
  }
}