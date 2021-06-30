## Ring Buffer

 In computer science, a circular buffer, circular queue, cyclic buffer or ring buffer is a data structure that uses a single, fixed-size buffer as if it were connected end-to-end. This structure lends itself easily to buffering data streams.
 
 Detailed description: https://en.wikipedia.org/wiki/Circular_buffer
 
- First of all you have to define the size of buffer  in the constructor.
```
  public RingBuffer(int limit, Class<T> componentType) {
    this.elements = (T[]) Array.newInstance(componentType, limit);
  }
```
- The buffer is full when the start and end index are same and all buffer space is occupied.
```
end == start && Arrays.stream(elements).anyMatch(Objects::nonNull)
```
- The buffer is empty when the start and end index are same and there is not a single element to the buffer.
```
end == start && Arrays.stream(elements).allMatch(Objects::isNull);
```
- If you want to put an element to the buffer, use the put() function after that the start index will be increased: <br />
```
Put 1 to buffer: CircularBuffer: elements=[1, null, null, null], limit=4, head=1, tail=0
Put 2 to buffer: CircularBuffer: elements=[1, 2, null, null], limit=4, head=2, tail=0
Put 3 to buffer: CircularBuffer: elements=[1, 2, 3, null], limit=4, head=3, tail=0
Put 4 to buffer: CircularBuffer: elements=[1, 2, 3, 4], limit=4, head=0, tail=0
```
- You can remove an element with the get() function, in this case the end index will be increased:
```
Get: 1 CircularBuffer: elements=[null, 2, 3, 4], limit=4, head=0, tail=1
Get: 2 CircularBuffer: elements=[null, null, 3, 4], limit=4, head=0, tail=2
Get: 3 CircularBuffer: elements=[null, null, null, 4], limit=4, head=0, tail=3
Get: 4 CircularBuffer: elements=[null, null, null, null], limit=4, head=0, tail=0
```
- Furthermore it contains several custom implementation e.g. toArray-, asList-, addAll-, or sort method.
```
  public void sort(Comparator<? super T> comparator) throws EmptyRingBufferException {
  ...
    T[] array = toArray();
    this.elements = array;
    Arrays.sort(elements, 0, array.length, comparator);
  ...
  }
```

![image](https://user-images.githubusercontent.com/58112290/123967342-81ce7a80-d9b6-11eb-93ef-c5262c0915b1.png)
