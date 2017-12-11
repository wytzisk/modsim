package de.hsbo.fbg.modsim.sample;

import de.hsbo.fbg.modsim.InvalidEntityException;
import de.hsbo.fbg.modsim.SimEntity;
import de.hsbo.fbg.modsim.SimQueue;

/**
 * Simulation entity representing a queued weighing station
 * 
 * @author wytzisk
 *
 */
public class EntyWeighStat extends SimEntity {
	
	public static final String QUEUE_NAME = "Weighing queue"; 
	
	private SimQueue<EntyTruck> queue = new SimQueue<EntyTruck>(QUEUE_NAME);
	private EntyTruck truck;

	/**
	 * Creates a EntyWeighStat object.
	 * 
	 * @param model
	 * @param name
	 */
	public EntyWeighStat(TransportModel model, String name) {
		this(model, name, null);
	}
	
	/**
	 * Creates a EntyWeighStat object filled with the given truck.
	 * 
	 * @param model
	 * @param name
	 * @param truck
	 */
	public EntyWeighStat(TransportModel model, String name, EntyTruck truck) {
		super(model, name);
		this.truck = truck;
	}
	
	/**
	 * Adds a truck to the weighing station and creates a corresponding
	 * EvtEndWeighing Event. If the station is still blocked by another truck,
	 * the incoming trucked is being queued.
	 * 
	 * @param truck
	 */
	public void addTruck(EntyTruck truck) {
		// Schedule weighing of fully loaded truck
		if (this.truck != null)
			queue.enqueue(truck);
		else {
			this.truck = truck;
			model.enqueueEvent(new EvtEndWeighing(truck, model.getTime()));
		}
	}
	
	/**
	 * Moves the truck out of a weighing station and schedules a 
	 * StartLoadingEvent. Throws an InvalidEntityException, if the 
	 * truck is not in the station.
	 * 
	 * @param truck
	 * @throws InvalidEntityException
	 */
	public EntyTruck removeTruck(EntyTruck truck) throws InvalidEntityException {
		// Does the event match the truck actually being weighted?
		if (truck != this.truck)
			throw new InvalidEntityException();

		// Schedule loading of truck
		model.enqueueEvent(new EvtStartLoading(truck, model.getTime()));

		// Weigh next truck (if any is queued)
		EntyTruck nextTruck = queue.dequeue();
		this.truck = nextTruck; // null, if queue was empty
		if (nextTruck != null)
			model.enqueueEvent(new EvtEndWeighing(nextTruck, model.getTime()));
		
		return truck;
	}

	@Override
	public String toString() {
		return getName() + " [" + truck + "]" + "\n" + queue;
	}
}