package de.hsbo.fbg.modsim;

import java.util.Random;

/**
 * Abstract simulation event representation.
 * 
 * @author wytzisk
 *
 */
public abstract class SimEvent implements Comparable<SimEvent> {

	private static Random rnd = new Random();

	private SimEntity entity;
	private long timestamp;

	/**
	 * Creates a SimEvent for the specified entity at recentTime plus a random
	 * duration within the interval [minDuration, maxDuration].
	 * 
	 * @param entity
	 * @param recentTime
	 * @param minDuration
	 * @param maxDuration
	 */
	protected SimEvent(SimEntity entity, long recentTime, int minDuration,
			int maxDuration) {
		this(entity, recentTime + minDuration
				+ rnd.nextInt(maxDuration - minDuration + 1));
	}

	/**
	 * Creates a SimEvent for the specified entity at the specified timestamp.
	 * 
	 * @param entity
	 * @param timestamp
	 */
	public SimEvent(SimEntity entity, long timestamp) {
		this.entity = entity;
		this.timestamp = timestamp;
	}

	/**
	 * Returns the associated entity.
	 * 
	 * @return entity
	 */
	public SimEntity getEntity() {
		return this.entity;
	}

	/**
	 * Returns the timestamp.
	 * 
	 * @return timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + entity + ", " + timestamp
				+ "]";
	}

	/*
	 * Allows simulation events to be ordered according to their timestamps
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SimEvent e) {
		if (timestamp == e.getTimestamp())
			return 0;
		if (timestamp < e.getTimestamp())
			return -1;
		return 1;
	}

}
