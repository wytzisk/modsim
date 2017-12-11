package de.hsbo.fbg.modsim.sample;

import de.hsbo.fbg.modsim.SimEvent;

/**
 * End weighing event
 * 
 * @author wytzisk
 *
 */
public class EvtEndWeighing extends SimEvent {

	private static int MIN_WEIGHING_TIME = 12;
	private static int MAX_WEIGHING_TIME = 16;

	/**
	 * End weighing event for the given truck scheduled for current model time 
	 * + a random duration between MIN_WEIGHING_TIME and MAX_WEIGHING_TIME.
	 * @param truck
	 * @param recentTime
	 */
	public EvtEndWeighing(EntyTruck truck, long recentTime) {
		super(truck, recentTime, MIN_WEIGHING_TIME, MAX_WEIGHING_TIME);
	}

}
