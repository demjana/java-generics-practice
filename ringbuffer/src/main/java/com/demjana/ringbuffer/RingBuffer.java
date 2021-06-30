package com.demjana.ringbuffer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RingBuffer<T> {

  private T[] elements;
  private int start = 0;
  private int end = 0;

  @SuppressWarnings("unchecked")
  public RingBuffer(int limit, Class<T> componentType) {
    this.elements = (T[]) Array.newInstance(componentType, limit);
  }

  private int next(int index) {
    return (index + 1) % elements.length;
  }

  public boolean isFull() {
    return end == start && Arrays.stream(elements).anyMatch(Objects::nonNull);
  }

  public boolean isEmpty() {
    return end == start && Arrays.stream(elements).allMatch(Objects::isNull);
  }

  public void put(T t) throws FullRingBufferException {
    if (isFull()) {
      throw new FullRingBufferException("Buffer is full!");
    }
    elements[start] = t;
    start = next(start);
  }

  public T get() throws EmptyRingBufferException {
    if (isEmpty()) {
      throw new EmptyRingBufferException("Buffer is empty!");
    }
    T value = elements[end];
    if (value != null) {
      elements[end] = null;
      end = next(end);
    }
    return value;
  }

  @SuppressWarnings("unchecked")
  public T[] toArray() throws EmptyRingBufferException {
    if (isEmpty()) {
      throw new EmptyRingBufferException("Buffer is empty!");
    }
    int nullCounter = 0;
    for (T element : elements) {
      if (element == null) {
        nullCounter++;
      }
    }
    T[] array = (T[]) Array.newInstance(elements.getClass().getComponentType(), elements.length - nullCounter);

    if (nullCounter > 0) {
      if (start >= 0) {
        System.arraycopy(elements, 0, array, 0, start);
      }
      if (end != 0) {
        if (elements.length - end >= 0) {
          System.arraycopy(elements, end, array, end - nullCounter, elements.length - end);
        }
      }
    } else {
      System.arraycopy(elements, 0, array, 0, array.length);
    }
    return array;
  }

  public List<T> asList() throws EmptyRingBufferException {
    if (isEmpty()) {
      throw new EmptyRingBufferException("Buffer is empty!");
    }
    List<T> list = new ArrayList<>();
    for (T element : elements) {
      if (element != null) {
        list.add(element);
      }
    }
    return list;
  }

  public void addAll(List<? extends T> toAdd) throws FullRingBufferException {
    if (toAdd.size() > elements.length - start) {
      throw new FullRingBufferException("Not enough space to add all elements!");
    } else {
      for (int i = 0; i < toAdd.size(); i++) {
        elements[start + i] = toAdd.get(i);
      }
      start = start + toAdd.size() - 1;
    }
  }

  public void sort(Comparator<? super T> comparator) throws EmptyRingBufferException {
    if (isEmpty()) {
      throw new EmptyRingBufferException("Buffer is empty!");
    }
    T[] array = toArray();
    this.elements = array;
    Arrays.sort(elements, 0, array.length, comparator);
    System.out.println(Arrays.toString(array));
  }

  @Override
  public String toString() {
    return "CircularBuffer: " +
        "elements=" + Arrays.toString(elements) +
        ", limit=" + elements.length +
        ", head=" + start +
        ", tail=" + end;
  }
}