package org.gfg;

import java.util.Iterator;

/**
 * Represents a set that keeps its elements in sorted order. This allows for efficient 
 * ordering-dependent lookup operations.
 */
public interface SortedSet<T extends Comparable<T>> extends Set<T> {
    /**
     * Returns the least element in this set that is strictly greater than the given key.
     * If no such element is present, null is returned.
     * @param key key whose successor to search for
     * @return inorder successor of the supplied key
     */
    public T successor(T key);

    /**
     * Returns the greatest element in this set that is strictly less than the given key.
     * If no such element is present, null is returned.
     * @param key key whose predecessor to search for
     * @return inorder predecessor of the supplied key
     */
    public T predecessor(T key);

    /**
     * Returns the least element in this set that is greater than or equal to the given key.
     * If no such element is present, null is returned.
     * @param key key whose ceiling to search for
     * @return ceiling of the supplied key
     */
    public T ceil(T key);

    /**
     * Returns the greatest element in this set that is less than or equal to the given key.
     * If no such element is present, null is returned.
     * @param key key whose floor to search for
     * @return floor of the supplied key
     */
    public T floor(T key);

    /**
     * Returns the smallest key in this sorted set.
     * @return min key in the sorted set
     */
    public T min();

    /**
     * Returns the greatest key in this sorted set.
     * @return max key in the sorted set
     */
    public T max();

    /**
     * Returns an iterator over elements in this set in sorted order.
     * @return iterator that supplies elements in inorder traversal order
     */
    @Override
    public Iterator<T> iterator();
}