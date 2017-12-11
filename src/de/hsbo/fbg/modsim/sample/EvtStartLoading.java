package de.hsbo.fbg.modsim.sample;

import de.hsbo.fbg.modsim.SimEvent;

/**
 * Start loading event
 * 
 * @author wytzisk
 *
 */
public class EvtStartLoading extends SimEvent {

	private static int MIN_TRANSPORTION_TIME = 40;
	private static int MAX_TRANSPORTATION_TIME = 100;

	/**
	 * Start loading event for the given truck scheduled for current model  
	 * time + a random duration between MIN_TRANSPORTION_TIME and 
	 * MAX_TRANSPORTATION_TIME.
	 * 
	 * @param truck
	 * @param recentTime
	 */
	public EvtStartLoading(EntyTruck truck, long recentTime) {
		super(truck, recentTime, MIN_TRANSPORTION_TIME, MAX_TRANSPORTATION_TIME);
	}

}
