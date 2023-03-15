package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ObjLongConsumer;

public class CarHashMap<K,T> implements CarMap<K,T> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private Object[] array = new Object[INITIAL_CAPACITY];

    @Override
    public void put(K key, T value) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = put(key, value, array);
        if (added) {
            size++;
        }
    }

    public boolean put(K key, T value, Object[] dst) {
        int position = getElementPosition(key, dst.length);
        if (dst[position] == null) {
            CarHashMap.Entry entry = new CarHashMap.Entry(key, value, null);
            dst[position] = entry;
            return true;
        } else {
            CarHashMap.Entry existedElement = (Entry) dst[position];
            while (true) {
                if (existedElement.key.equals(key)) {
                    existedElement.value = value;
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new CarHashMap.Entry(key, value, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    @Override
    public T get(K key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return null;
        }
        CarHashMap.Entry secondLast = (Entry) array[position];
        CarHashMap.Entry last = secondLast.next;
        if (secondLast.key.equals(key)) {
            return (T) secondLast.value;
        }
        while (last != null) {
            if (last.key.equals(key)) {
                return (T) last.value;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();

        for (Object entry : array) {
            Entry existedElement = (Entry) entry;
            while (existedElement != null) {
                result.add((K) existedElement.key);
                existedElement = existedElement.next;
            }
        }

        return result;
    }

    @Override
    public List<T> values() {
        List<T> result = new ArrayList<>();

        for (Object entry : array) {
            Entry existedElement = (Entry) entry;
            while (existedElement != null) {
                result.add((T) existedElement.value);
                existedElement = existedElement.next;
            }
        }

        return result;
    }

    @Override
    public boolean remove(K key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }
        CarHashMap.Entry secondLast = (Entry) array[position];
        CarHashMap.Entry last = secondLast.next;
        if (secondLast.key.equals(key)) {
            array[position] = last;
            size--;
            return true;
        }
        while (last != null) {
            if (last.key.equals(key)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new CarHashMap.Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArray() {
        CarHashMap.Entry[] newArray = new CarHashMap.Entry[array.length * 2];
        for (Object entry : array) {
            CarHashMap.Entry existedElement = (Entry) entry;
            while (existedElement != null) {
                put((K) existedElement.key, (T) existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getElementPosition(K key, int arrayLength) {
        return Math.abs(key.hashCode() % arrayLength);
    }

    private static class Entry<K,T> {

        private K key;
        private T value;
        private CarHashMap.Entry next;

        public Entry(K key, T value, CarHashMap.Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
