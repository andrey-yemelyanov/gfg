package org.gfg;

/**
 * Dictionary abstraction that stores a set of keys and associated values.
 * @param <K> type of keys
 * @param <V> type of values
 */
public interface Dictionary<K, V>{
    /**
     * Checks for presence of a key in this dictionary.
     * @param key input key
     * @return true if the key is present in the dictionary
     */
    public boolean containsKey(K key);
    
    /**
     * Removes a value associated with a specified key from this dictionary.
     * @param key input key
     * @return value associated with the deleted key
     */
    public V delete(K key);
    
    /**
     * Adds a key/value pair to this dictionary.
     * @param key input key
     * @param value input value
     */
    public void add(K key, V value);

    /**
     * Retrieves value associated with a given key.
     * @param key input key
     * @return value associated with the key
     */
    public V get(K key);

    /**
     * Returns number of key/value pairs stored in this dictionary.
     * @return size of this dictionary
     */
    public int size();
}