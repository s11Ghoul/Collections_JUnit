package org.example;

public interface CarList<T> extends CarCollection<T> {
    T get(int index);

    @Override
    boolean add(T car);

    boolean add(T car, int index);

    boolean remove(T car);

    boolean removeAt(int index);

    int size();

    void clear();

    boolean contains(T car);
}
