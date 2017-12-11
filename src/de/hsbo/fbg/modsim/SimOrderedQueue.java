package de.hsbo.fbg.modsim;


/**
 * Implements an ordered queue. Each time a new element has been added, the
 * queue will be re-ordered. I.e. dequeuing means to extract the "smallest"
 * element.
 * 
 * @author Andreas Wytzisk
 */
public class SimOrderedQueue<T> extends SimQueue<T> {

	/**
	 * Creates a SimOrderedQueue with the specified name.
	 * 
	 * @param name
	 */
	public SimOrderedQueue(String name) {
		super(name);
	}

	/**
	 * Enqueue elements to the queue and re-orders the underlying list.
	 * Needs to be optimized!
	 * 
	 * @param elements	Objects to be enqueued
	 */
	@Override
	public void enqueue(T element) {
		super.enqueue(element);
		getQueue().sort(null);
	}

}
