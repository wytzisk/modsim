package de.hsbo.fbg.modsim.sample;

import de.hsbo.fbg.modsim.SimEvent;

/**
 * End loading event.
 * 
 * @author wytzisk
 *
 */
public class EvtEndLoading extends SimEvent {

	private static int MIN_LOADING_TIME = 5;
	private static int MAX_LOADING_TIME = 15;

	/**
	 * End loading event for the given truck scheduled for current model time 
	 * + a random duration between MIN_LOADING_TIME and MAX_LOADING_TIME.
	 * 
	 * @param truck
	 * @param recentTime
	 */
	public EvtEndLoading(EntyTruck truck, long recentTime) {
		super(truck, recentTime, MIN_LOADING_TIME, MAX_LOADING_TIME);
	}

}
