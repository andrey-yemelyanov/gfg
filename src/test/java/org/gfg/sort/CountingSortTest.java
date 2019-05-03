package org.gfg.sort;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.gfg.sort.CountingSort.*;
import static org.hamcrest.Matchers.*;

public class CountingSortTest{
    @Test
    public void countSortIntegers(){
        Integer[] array = new Integer[]{4,1,3,2,3};
        countingSort(array, i -> i, 5);
        assertThat(array, is(new Integer[]{1,2,3,3,4}));

        array = new Integer[]{1,1,1,1,1,1,1,1};
        countingSort(array, i -> i, 5);
        assertThat(array, is(new Integer[]{1,1,1,1,1,1,1,1}));

        array = new Integer[]{4,3,2,1,4,3,2,1};
        countingSort(array, i -> i, 5);
        assertThat(array, is(new Integer[]{1,1,2,2,3,3,4,4}));
    }

    @Test
    public void countSortObjects(){
        class Person{
            public String name;
            public int age;
            public Person(String name, int age){
                this.name = name; this.age = age;
            }
            @Override
            public boolean equals(Object obj){
                Person other = (Person)obj;
                return this.name.equals(other.name) 
                    && this.age == other.age;
            }
            @Override
            public String toString(){
                return name + " " + age;
            }
        }

        Person[] array = new Person[]{
            new Person("Jane", 22),
            new Person("Dick", 10),
            new Person("Shawn", 22),
            new Person("Leila", 11),
            new Person("Bridgit", 10)
        };
        countingSort(array, p -> p.age, 101);
        assertThat(array, is(new Person[]{
            new Person("Dick", 10),
            new Person("Bridgit", 10),
            new Person("Leila", 11),
            new Person("Jane", 22),
            new Person("Shawn", 22)
        }));
    }

    @Test
    public void radixSortIntegers(){
        Integer[] array = new Integer[]{
            2341,
            1432,
            2413,
            1243,
            2143,
            1234
        };
        radixSort(array, i -> i); // we use base 10 for digits
        assertThat(array, is(new Integer[]{
            1234,
            1243,
            1432,
            2143,
            2341,
            2413
        }));

        array = new Integer[]{170, 45, 75, 90, 1123, 02, 802, 2, 66};
        radixSort(array, i -> i);
        assertThat(array, is(new Integer[]{2, 2, 45, 66, 75, 90, 170, 802, 1123}));
    }
}