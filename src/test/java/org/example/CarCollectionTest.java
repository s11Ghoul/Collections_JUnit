package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarCollectionTest {

    private CarCollection<Car> carCollectionLList;
    private CarCollection<Car> carCollectionSet;
    @Before
    public void setUp() throws Exception {
        carCollectionLList = new CarLinkedList();
        carCollectionSet = new CarHashSet();
        for (int i = 0; i < 100; i++) {
            carCollectionLList.add(new Car("Brand" + i, i));
            carCollectionSet.add(new Car("Brand" + i, i));
        }


    }

    @Test
    public void WhenCarCollectionUseMethodContainsReturnsTrue() {
        Car car1 = new Car("Brand3", 3);
        assertTrue(carCollectionLList.contains(car1));
        assertTrue(carCollectionSet.contains(car1));
    }

    @Test
    public void WhenCarCollectionUseMethodContainsReturnsFalse() {
        Car car1 = new Car("Brand1", 3);
        assertFalse(carCollectionLList.contains(car1));
        assertFalse(carCollectionSet.contains(car1));
    }

    @Test
    public void  testForEach() {
        int index = 0;
        for (Car car : carCollectionSet){
            index++;
        }
        for (Car car : carCollectionLList){
            index++;
        }
        assertEquals(200,index);

    }

}