package org.gfg;

/**
 * Represents a set abstraction - a collection of distinct items.
 * @param <T> type of elements stored in the set
 */
public interface Set<T>{
    /**
     * Returns a number of items contained in this set.
     */
    public int size();

    /**
     * Returns true if this set is empty.
     */
    public boolean isEmpty();

    /**
     * Adds an item to this set. If the item is already present in the set, it is overwritten.
     * @param item item to be added
     */
    public void add(T item);

    /**
     * Checks for set membership.
     * @param item item to look up in the set
     * @return true if the item is present in the set
     */
    public boolean contains(T item);
}