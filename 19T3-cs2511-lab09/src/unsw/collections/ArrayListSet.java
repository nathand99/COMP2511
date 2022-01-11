/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        if (!elements.contains(e)) {
        	elements.add(e);
        }
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        for (Object e : elements) {
        	if (!other.contains(e)) {
        		return false;
        	}
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
    	return elements.iterator();
    }

	@Override
    public Set<E> union(Set<? extends E> other) {
        ArrayListSet<E> set = new ArrayListSet<E>();
       // add everything from elements into set
    	for (E e : elements) {
    		if (!other.contains(e)) {
    			set.add(e);
    		}
    	}
    	// add everything from other thats not in set into set
    	for (E e : other) {
    		if (!set.contains(e)) {
    			set.add(e);
    		}
    	}
        return set;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
    	ArrayListSet<E> set = new ArrayListSet<E>();
    	for (E e : elements) {
    		if (other.contains(e)) {
    			set.add((E) e);
    		}
    	}
        return set;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null && elements == null) {
        	return true;
        }
        if (!(other instanceof Set)) {
        	return false;
        }
        Set<?> o = (Set<?>)other;
        for (E e : elements) {
    		if (!o.contains(e)) {
    			return false;
    		}
    	}
        if (size() != o.size()) {
        	return false;
        }
        return true;
    }

}
