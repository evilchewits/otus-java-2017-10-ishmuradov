package com.ishmuradov.otus.homework3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Custom implementation of ArrayList. Warning: this implementation is
 * thread-unsafe.
 * 
 * This is a "necessary and sufficient" implementation to get the following
 * methods working:
 * 
 * Collections.addAll(Collection<? super T> c, T... elements)
 * Collections.copy(List<? super T> dest, List<? extends T> src)
 * Collections.sort(List<T> list, Comparator<? super T> c)
 * 
 * @author Ishmuradov
 *
 * @param <E>
 */
public class MyArrayList<E> implements List<E> {

  private static final int DEFAULT_CAPACITY = 2;
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  private Object[] array = new Object[DEFAULT_CAPACITY];
  private int size;
  private final Supplier<Integer> ARRAY_NEW_SIZE_SUPPLIER = () -> Math
      .min(array.length + (int) (0.5 * array.length) + 1, MAX_ARRAY_SIZE);

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(Object o) {
    throw new NotImplementedException("Method not implemented");
  }

  public Iterator<E> iterator() {
    return new MyIterator();
  }

  public Object[] toArray() {
    return Arrays.copyOf(array, size);
  }

  public <T> T[] toArray(T[] a) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean add(E e) {
    if (size + 1 >= MAX_ARRAY_SIZE) {
      return false;
    }

    if (size + 1 >= array.length) {
      array = Arrays.copyOf(array, ARRAY_NEW_SIZE_SUPPLIER.get());
    }
    array[size++] = e;

    return true;
  }

  public boolean remove(Object o) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean containsAll(Collection<?> c) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean addAll(Collection<? extends E> c) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean addAll(int index, Collection<? extends E> c) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean removeAll(Collection<?> c) {
    throw new NotImplementedException("Method not implemented");
  }

  public boolean retainAll(Collection<?> c) {
    throw new NotImplementedException("Method not implemented");
  }

  public void clear() {
    throw new NotImplementedException("Method not implemented");
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    rangeCheck(index);

    return (E) array[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E element) {
    rangeCheck(index);
    E oldValue = (E) array[index];
    array[index] = element;

    return oldValue;
  }

  public void add(int index, E element) {
    throw new NotImplementedException("Method not implemented");
  }

  public E remove(int index) {
    throw new NotImplementedException("Method not implemented");
  }

  public int indexOf(Object o) {
    throw new NotImplementedException("Method not implemented");
  }

  public int lastIndexOf(Object o) {
    throw new NotImplementedException("Method not implemented");
  }

  public ListIterator<E> listIterator() {
    return new MyListIterator();
  }

  public ListIterator<E> listIterator(int index) {
    throw new NotImplementedException("Method not implemented");
  }

  public List<E> subList(int fromIndex, int toIndex) {
    throw new NotImplementedException("Method not implemented");
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sort(Comparator<? super E> c) {
    Arrays.sort((E[]) array, 0, size, c);
  }

  private void rangeCheck(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException();
  }

  ////////////////////////////////

  private class MyIterator implements Iterator<E> {
    protected int cursor;
    protected int lastRet = -1; // index of last element returned; -1 if no such

    @Override
    public boolean hasNext() {
      return cursor < size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
      if (cursor >= size) {
        throw new NoSuchElementException();
      }
      lastRet = cursor;

      return (E) array[cursor++];
    }
  }

  private class MyListIterator extends MyIterator implements ListIterator<E> {
    @Override
    public boolean hasPrevious() {
      throw new NotImplementedException("Method not implemented");
    }

    @Override
    public E previous() {
      throw new NotImplementedException("Method not implemented");
    }

    @Override
    public int nextIndex() {
      throw new NotImplementedException("Method not implemented");
    }

    @Override
    public int previousIndex() {
      throw new NotImplementedException("Method not implemented");
    }

    @Override
    public void remove() {
      throw new NotImplementedException("Method not implemented");
    }

    @Override
    public void set(E e) {
      MyArrayList.this.set(lastRet, e);
    }

    @Override
    public void add(E e) {
      throw new NotImplementedException("Method not implemented");
    }
  }

}
