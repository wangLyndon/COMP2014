package dsa.impl;

import dsa.iface.IEntry;
import dsa.iface.IList;
import dsa.iface.IPosition;
import dsa.iface.ISortedMap;

import java.util.Iterator;
import java.util.function.Function;

public class BinarySearchTreeMap<K extends Comparable<K>,V> extends ProperLinkedBinaryTree<IEntry<K,V>> implements ISortedMap<K,V> {

    protected IPosition<IEntry<K, V>> find(IPosition<IEntry<K, V>> start, K key) {
        // 1. Return the node if it is external.
        if (isExternal(start)){
            return start;
        }
        // 2. Compare the key of the node with 'key'.
        // 3. If the key is less than the key in the node's element, recursively call this method to search the left sub-tree.
        if (key.compareTo(start.element().key()) < 0){
            return find(left(start), key);
        } else if (key.compareTo(start.element().key()) > 0) {
            return find(right(start), key);
        }else{
            return start;
        }
        // 4. If the key is greater than the key in the node's element, recursively call this method to search the right sub-tree.
        // 5. If the key is equal to the key in the node's element, we have found it! Return this node.
//        return null; // <-- This is so that the class will compile. Remove it when writing your code.
    }

    @Override
    public V get(K key) {
        // 1. Use the 'find' method to find the node that contains the given key (if it is in the map).
        IPosition<IEntry<K, V>> iEntryIPosition = find(root, key);
        // 2. If 'find' returned an internal node, return the value stored in that node. Otherwise return null.
        if (isInternal(iEntryIPosition)){
            return iEntryIPosition.element().value();
        }
        return null; // <-- This is so that the class will compile. Remove it when writing your code.
    }

    @Override
    public V put(K key, V value) {
        IPosition<IEntry<K, V>> pos = find( this.root(), key );

        if (this.isExternal(pos)) {
            this.expandExternal(pos, new Entry<>(key, value));
            return null;
        } else {
            V toReturn = pos.element().value();
            ((Entry<K, V>) pos.element()).value = value;

            return toReturn;
        }
    }

    @Override
    public V remove(K key) {
        IPosition<IEntry<K,V>> pos = find( this.root(), key );
        // value is in the tree so remove it
        if ( this.isInternal( pos ) ) {

            // remember the value to return later
            V toReturn = pos.element().value();

            // both children are internal, so find the position of the next
            // largest value copy the value and remove the position
            if (isInternal(left(pos)) && isInternal(right(pos))) {
                IPosition<IEntry<K, V>> current = right(pos);
                while (isInternal(left(current)))
                    current = left(current);
                replace(pos, current.element());
                remove(current);
            }
            // must have at least one external child, so we can remove directly
            else {
                remove( pos );
            }

            return toReturn;
        }
        else {
            return null;
        }
    }

    // Below this line are some other helper methods for iterating through the entries/keys/values in this map.


    @Override
    public Iterator<K> keys() {
        IList<K> list = new SLinkedList<>();
        inOrderVisitor( this.root(), list, IEntry::key );
        return list.iterator();
    }

    @Override
    public Iterator<V> values() {
        IList<V> list = new SLinkedList<>();
        inOrderVisitor( this.root(), list, IEntry::value );
        return list.iterator();
    }

    @Override
    public Iterator<IEntry<K, V>> entries() {
        IList<IEntry<K,V>> list = new SLinkedList<>();
        inOrderVisitor( this.root(), list, ( IEntry<K,V> e ) -> e );
        return list.iterator();
    }

    protected <T> void inOrderVisitor( IPosition<IEntry<K,V>> start, IList<T> list, Function<IEntry<K,V>,T> func ) {
        if ( this.isInternal( start ) ) {
            inOrderVisitor(this.left(start), list, func );
            list.insertLast( func.apply( start.element() ) );
            inOrderVisitor(this.right(start), list, func );
        }
    }




    /**
     * Class to represent a key/value pair that becomes an entry in a map.
     * @param <K> generic type of keys (must be comparable).
     * @param <V> generic type of values.
     */
    protected static class Entry<K extends Comparable<K>,V> implements IEntry<K,V> {

        K key;
        V value;

        Entry( K key, V value ) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        public String toString() {
            return String.format( "{ key: [%s], value: [%s] }", key, value );
        }
    }
}