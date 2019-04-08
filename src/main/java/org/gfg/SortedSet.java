package org.gfg;

public interface SortedSet<T extends Comparable<T>> extends Set<T>{
    /**
     * Returns the least element in this set that is strictly greater than the given key.
     * If no such element is present, null is returned.
     * @param key key whose successor to search for
     */
    public T successor(T key);

    /**
     * Returns the greatest element in this set that is strictly less than the given key.
     * If no such element is present, null is returned.
     * @param key key whose predecessor to search for
     */
    public T predecessor(T key);

    /**
     * Returns the least element in this set that is greater than or equal to the given key.
     * If no such element is present, null is returned.
     * @param key key whose ceiling to search for
     */
    public T ceil(T key);

    /**
     * Returns the greatest element in this set that is less than or equal to the given key.
     * If no such element is present, null is returned.
     * @param key key whose floor to search for
     */
    public T floor(T key);

    /**
     * Returns the smallest key in this set.
     */
    public T min();

    /**
     * Returns the greatest key in this set.
     */
    public T max();
}