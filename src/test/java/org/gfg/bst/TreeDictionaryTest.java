package org.gfg.bst;

import static org.junit.Assert.assertThat;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import org.gfg.Dictionary;
import org.junit.Test;

public class TreeDictionaryTest{
    @Test
    public void getKeys(){
        Dictionary<Integer, String> dict = new TreeDictionary<>();
        dict.add(1, "One");
        dict.add(2, "Two");
        dict.add(3, "Three");
        dict.add(4, "Four");
        dict.add(5, "Five");
        assertThat(dict.keys(), is(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testAddAndContains(){
        Dictionary<Integer, String> dict = new TreeDictionary<>();
        assertThat(dict.containsKey(1), is(false));
        dict.add(1, "One");
        assertThat(dict.containsKey(1), is(true));
        assertThat(dict.get(1), is("One"));
        dict.add(1, "ONE");
        assertThat(dict.containsKey(1), is(true));
        assertThat(dict.get(1), is("ONE"));
        assertThat(dict.size(), is(1));
    }

    @Test
    public void testRemoveAndContains(){
        Dictionary<Integer, String> dict = new TreeDictionary<>();
        assertThat(dict.containsKey(1), is(false));
        dict.add(1, "One");
        assertThat(dict.containsKey(1), is(true));
        assertThat(dict.size(), is(1));
        dict.delete(1);
        assertThat(dict.containsKey(1), is(false));
        assertThat(dict.size(), is(0));
    }

    @Test
    public void testSquares(){
        Dictionary<Integer, Integer> squares = new TreeDictionary<>();
        final int MAX = 1000;
        for(int i = 0; i < MAX; i++) squares.add(i, i * i);

        assertThat(squares.size(), is(MAX));
        for(int i = 0; i < MAX; i++){
            assertThat(squares.get(i), is(i * i));
        }

        for(int i = 1; i < MAX; i += 2) assertThat(squares.delete(i), is(i * i));
        for(int i = 0; i < MAX; i++){
            if((i & 1) == 0) assertThat(squares.get(i), is(i * i));
            else assertThat(squares.containsKey(i), is(false));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteExceptionIfKeyDoesNotExist(){
        Dictionary<Integer, String> dict = new TreeDictionary<>();
        dict.add(1, "ONE");
        dict.delete(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetExceptionIfKeyDoesNotExist(){
        Dictionary<Integer, String> dict = new TreeDictionary<>();
        dict.add(1, "ONE");
        dict.get(2);
    }
}