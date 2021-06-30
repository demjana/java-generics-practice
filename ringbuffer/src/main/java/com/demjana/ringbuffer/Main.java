package com.demjana.ringbuffer;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    RingBuffer<Integer> buffer = new RingBuffer<>(4, Integer.class);

    System.out.println("Check the buffer is empty: " + buffer.isEmpty());
    System.out.println(buffer);

    buffer.put(1);
    System.out.println("Put 1 to buffer: " + buffer);
    buffer.put(2);
    System.out.println("Put 2 to buffer: " + buffer);
    buffer.put(3);
    System.out.println("Put 3 to buffer: " + buffer);
    buffer.put(4);
    System.out.println("Put 4 to buffer: " + buffer);

    System.out.println("Check the buffer is full: " + buffer.isFull() + "\n");

    Object[] newArray = buffer.toArray();
    System.out.println("Array: " + Arrays.toString(newArray));

    List<Integer> list = buffer.asList();
    System.out.println("List: " + list + "\n");

    System.out.println(buffer);

    System.out.println("#1 Get: " + buffer.get());
    System.out.println(buffer);

    System.out.println("#2 Get: " + buffer.get());
    System.out.println(buffer);

    System.out.println("#3 Get: " + buffer.get());
    System.out.println(buffer);

    System.out.println("#4 Get: " + buffer.get());
    System.out.println(buffer);
    System.out.println("Check the buffer is empty: " + buffer.isEmpty() + "\n");

    RingBuffer<Integer> newBuffer = new RingBuffer<>(7, Integer.class);

    newBuffer.put(5);
    newBuffer.put(6);
    newBuffer.put(7);
    System.out.println("Put 5, 6, 7 to newBuffer: " + newBuffer);

    newBuffer.addAll(list);
    System.out.println("Add all elements from List to newBuffer: " + newBuffer + "\n");

    System.out.println("Check the buffer is circular:");
    System.out.println("New buffer: " + newBuffer + "\n");

    newBuffer.get();
    newBuffer.put(27);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(28);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(39);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(10);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(11);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(12);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.get();
    newBuffer.put(13);
    System.out.println("New buffer: " + newBuffer);

    newBuffer.put(0);
    System.out.println("New buffer: " + newBuffer);

    NaturalComparator<Integer> comparator = new NaturalComparator<>();

    System.out.print("\nSorted buffer ASC: ");
    newBuffer.sort(comparator);

    newBuffer.get();
    System.out.println("\nNew buffer: " + newBuffer);

    System.out.print("\nSorted buffer ASC: ");
    newBuffer.sort(comparator);

    RingBuffer<String> stringBuffer = new RingBuffer<>(4, String.class);

    stringBuffer.put("apple");
    stringBuffer.put("pear");
    stringBuffer.put("banana");

    System.out.println("\nPut 3 elements to stringBuffer: " + stringBuffer);
    String value = concatenate(stringBuffer);
    System.out.println("Output: " + value);
    System.out.println("Check the buffer is empty: " + stringBuffer.isEmpty());
  }

  private static String concatenate(RingBuffer<String> buffer) {
    StringBuilder result = new StringBuilder();
    String value;
    try {
      while ((value = buffer.get()) != null) {
        result.append(value);
      }
    } catch (EmptyRingBufferException e) {
      System.out.println("All elements of the buffer were removed and concatenated!");
    }
    return result.toString();
  }
}
