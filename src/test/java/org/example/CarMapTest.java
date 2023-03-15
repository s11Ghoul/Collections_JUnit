package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CarMapTest {
    private CarMap carmap = new CarHashMap();

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 100; i++) {
            carmap.put(new CarOwner(i,"Owner" + i, "Smith"),new Car("Brand" + i, i));
        }
    }

    @Test
    public void WhenGetCarFromMapItReturnCar() {
        assertEquals(carmap.get(new CarOwner(5, "Owner5","Smith")),new Car("Brand5",5));
    }

    @Test
    public void WhenPut100ElementsWtih10DifferentKeysThenSize10(){
        carmap.clear();
        for (int i = 0; i < 100; i++) {
            int index = i % 10;
            carmap.put(new CarOwner(index,"Owner" + index, "Smith"),new Car("Brand" + index, index));
        }
        assertEquals(10, carmap.size());

    }

    @Test
    public void keySet() {
        assertEquals(100,carmap.keySet().size());
    }

    @Test
    public void values() {
        assertEquals(100, carmap.values().size());
    }

    @Test
    public void remove() {
        carmap.remove(new CarOwner(5, "Owner5","Smith"));
        assertEquals(carmap.size(),99);
        assertFalse(carmap.remove(new CarOwner(5, "Owner5","Smith")));
    }

    @Test
    public void size() {
        assertEquals(100,carmap.size());
    }

    @Test
    public void clear() {
        carmap.clear();
        assertEquals(0,carmap.size());
    }
}