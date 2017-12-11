package de.hsbo.fbg.modsim;

import java.util.ArrayList;

/**
 * Abstract class representing a simulation model.
 * 
 * @author wytzisk
 *
 */
public abstract class SimModel {

	private static String QUEUE_NAME = "MAIN EVENT QUEUE";

	private SimOrderedQueue<SimEvent> evtList = new SimOrderedQueue<SimEvent>(QUEUE_NAME);
	private ArrayList<SimEventListener> evtListenerList = new ArrayList<SimEventListener>();
	private long time = 0;

	/**
	 * Adds a SimEventListener.
	 * 
	 * @param listener
	 */
	public void addSimEventListener(SimEventListener listener) {
		evtListenerList.add(listener);
	}
	
	/**
	 * Removes the specified SimEventListener.
	 * 
	 * @param listener
	 * @return
	 */
	public boolean removeSimEventListener(SimEventListener listener) {
		return evtListenerList.remove(listener);
	}

	/**
	 * Fires the specified SimEvent by calling all registered listeners.
	 * 
	 * @param e
	 * @throws SimException
	 */
	private void fireSimEvent(SimEvent e) throws SimException {
		for (SimEventListener listener : evtListenerList)
			listener.handleEvent(e);
	}

	/**
	 * Returns current model time.
	 * 
	 * @return model time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Sets the model time.
	 * 
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Returns the model's event list.
	 * 
	 * @return event list
	 */
	public SimQueue<SimEvent> getEvtList() {
		return evtList;
	}

	/**
	 * Checks if the model's event list is empty.
	 * 
	 * @return true if the event queue is empty 
	 */
	public boolean isQueueEmpty() {
		return evtList.isEmpty();
	}

	/**
	 * Enqueues a SimEvent to the model's event list.
	 * 
	 * @param e
	 */
	public void enqueueEvent(SimEvent e) {
		evtList.enqueue(e);
	}

	/**
	 * Dequeues a SimEvent to the model's event list.
	 * 
	 */
	public SimEvent dequeueEvent() {
		return evtList.dequeue();
	}

	/**
	 * Runs a simulation experiment until model time tmax has been reached.
	 * 
	 * @param tmax
	 * @param dump
	 * @throws Exception
	 */
	public void simulateTimespan(long tmax, boolean dump) throws Exception {
		simulate(tmax, -1, dump);
	}

	/**
	 * Runs a simulation experiment handling maximum maxSteps events.
	 * 
	 * @param maxSteps
	 * @param dump
	 * @throws Exception
	 */
	public void simulateMaxEvents(long maxSteps, boolean dump) throws Exception {
		simulate(-1, maxSteps, dump);
	}

	/**
	 * Main simulation loop.
	 * 
	 * @param tmax
	 * @param maxEvents
	 * @param dump
	 * @throws Exception
	 */
	private void simulate(long tmax, long maxEvents, boolean dump)
			throws Exception {

		long iterations = 0;
		do {

			// Get next queued event, advance time and handle event
			SimEvent e = this.dequeueEvent();
			this.setTime(e.getTimestamp());
			fireSimEvent(e);
			
			iterations++;

			if (dump)
				dump();

		} while (((tmax != -1 && evtList.readNext().getTimestamp() < tmax) || 
				(maxEvents != -1 && iterations < maxEvents)) && !this.isQueueEmpty());
	}

	/**
	 * Dumps intermediate results. Needs to be implemented by inherited
	 * classes. 
	 */
	public abstract void dump();

	/**
	 * Initialize a simulation experiment. Needs to be implemented by 
	 * inherited classes.
	 */
	public abstract void initialize();

}
