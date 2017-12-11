package de.hsbo.fbg.modsim;

import java.util.ArrayList;
import java.util.List;

/**
* Queue implementation following FIFO principle.
* 
* @author Andreas Wytzisk
*/
public class SimQueue<T> {
	
	private List<T> q = new ArrayList<T>();
	private String name;
	
	/**
	 * Returns a SimQueue with the specified name.
	 * 
	 * @param name
	 */
	public SimQueue (String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the queue.
	 */	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the queue.
	 */	
	public List<T> getQueue() {
		return this.q;
	}
	
	/**
	 * Check whether the queue is empty. Returns <i>true</i> for empty queues, 
	 * else <i>false</i>.
	 */	
	public boolean isEmpty() {
		return q.size() == 0;
	}
	
	/**
	 * Enqueue an element to the queue.
	 *  
	 * @param elements Objects to be enqueued
	 */	
	public void enqueue(T element) {
		q.add(element);
	}
	
	/**
	 * Enqueue elements to the queue.
	 *  
	 * @param elements Objects to be enqueued
	 */	
	public void enqueue(List<T> elements) {
		for (T e: elements) 
			enqueue(e);
	}
	
	/**
	 * Dequeue an element from the queue. According to the fifo principle,
	 * the object enqueued first will be dequeued first.
	 *  
	 * @return object that has been removed from the queue
	 */
	public T dequeue() {
		return (q.size() <= 0) ? null : q.remove(0);
	}
	
	/**
	 * Reads next element from queue without dequeuing. 
	 * 
	 * @return object that would be dequeued next
	 */
	public T readNext() {
		return q.get(0);
	}

	@Override
	public String toString() {
		return name + " " + q;
	}

}
