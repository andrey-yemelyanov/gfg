package org.gfg.hash;

import org.gfg.Dictionary;
import java.util.*;

/**
 * Implements {@link Dictionary} interface using a hash table. 
 * Keys shall provide their own hash code and equals logic by overriding 
 * {@code hashCode()} and {@code equals(Object obj)} methods.
 * @param <K> type of keys stored in this hash table
 * @param <V> type of values stored in this hash table
 */
public class HashDictionary<K, V> implements Dictionary<K, V>{
    private class KeyValuePair{
        public K key;
        public V value;
        public KeyValuePair(K key, V value){
            this.key = key; 
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj){
            if(obj == null || !(obj instanceof HashDictionary.KeyValuePair)) return false;
            KeyValuePair other = (KeyValuePair) obj;
            return other.key.equals(this.key);
        }
    }

    private int n; // number of keys currently stored in the hash table
    private int m = 1; // initial size of the hash table
    private List<LinkedList<KeyValuePair>> hashTable;

    private double loadFactor(){
        return (double) n / m;
    }

    public HashDictionary(){
        hashTable = initHashTable(m);
    }

    private void doubleHashTable(){
        m *= 2;
        List<LinkedList<KeyValuePair>> newHashTable = initHashTable(m);
        for(LinkedList<KeyValuePair> bucket : hashTable){
            for(KeyValuePair keyValuePair : bucket){
                addToHashTable(keyValuePair, newHashTable, m);
            }
        }
        hashTable = newHashTable;
    }

    private int computeHash(K key, int tableSize){
        // get rid of the key's hash code sign bit before computing MOD
        return (key.hashCode() & 0x7FFFFFFF) % tableSize;
    }

    private void addToHashTable(KeyValuePair keyValuePair, List<LinkedList<KeyValuePair>> hashTable, int tableSize){
        int hash = computeHash(keyValuePair.key, tableSize);
        hashTable.get(hash).addLast(keyValuePair);
    }

    private List<LinkedList<KeyValuePair>> initHashTable(int tableSize){
        List<LinkedList<HashDictionary<K, V>.KeyValuePair>> newHashTable = new ArrayList<>();
        for(int i = 0; i < tableSize; i++) {
            newHashTable.add(new LinkedList<>());
        }
        return newHashTable;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = computeHash(key, m);
        return hashTable.get(hash).contains(new KeyValuePair(key, null));
    }

    @Override
    public V delete(K key) {
        ensureKeyExists(key);
        int hash = computeHash(key, m);
        KeyValuePair keyValuePair = findKeyValuePair(hashTable.get(hash), key);
        hashTable.get(hash).remove(keyValuePair);
        n--;
        return keyValuePair.value;
    }

    @Override
    public void add(K key, V value) {
        if(loadFactor() > 0.75){
            doubleHashTable();
        }

        if(containsKey(key)){
            delete(key);
        }

        addToHashTable(new KeyValuePair(key, value), hashTable, m);
        n++;
    }

    @Override
    public V get(K key) {
        ensureKeyExists(key);
        int hash = computeHash(key, m);
        return findKeyValuePair(hashTable.get(hash), key).value;
    }

    @Override
    public int size() {
        return n;
    }

    private KeyValuePair findKeyValuePair(LinkedList<KeyValuePair> list, K key){
        int keyIndex = list.indexOf(new KeyValuePair(key, null));
        return list.get(keyIndex);
    }

    private void ensureKeyExists(K key){
        if(!containsKey(key)){
            throw new IllegalArgumentException(String.format("Key '%s' does not exist in the dictionary.", key));
        }
    }
}