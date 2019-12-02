package de.hsbo.fbg.modsim.sample;

import java.util.ArrayList;
import java.util.List;

import de.hsbo.fbg.modsim.InvalidEntityException;
import de.hsbo.fbg.modsim.SimEntity;
import de.hsbo.fbg.modsim.SimQueue;

/**
 * Entity representing a queued loading station with an arbitrary number of
 * loading hubs.
 * 
 * @author wytzisk
 *
 */
public class EntyLoadStat extends SimEntity {
	
	private static final String QUEUE_NAME = "Loading Queue";
	
	private SimQueue<EntyTruck> queue = new SimQueue<EntyTruck>(QUEUE_NAME);
	private ArrayList<EntyTruck> list  = new ArrayList<EntyTruck>(); 
	private int size ;

	/**
	 * Creates an EntyCpxLoadStat object with size hubs.
	 * 
	 * @param model
	 * @param name
	 * @param size
	 */
	public EntyLoadStat(TransportModel model, String name, int size) {
		this(model, name, size, null);
	}
	
	/**
	 * Creates an EntyCpxLoadStat object with size hubs and filled with a list 
	 * of trucks. If the number of trucks exceeds the loading station hubs, 
	 * the remaining trucks will be queued.
	 * 
	 * @param model
	 * @param name
	 * @param size
	 * @param trucks
	 */
	public EntyLoadStat(TransportModel model, String name, int size, List<EntyTruck> trucks) {
		super(model, name);
		this.size = size;
		if (trucks != null)
			this.addTruck(trucks);
	}
	
	/**
	 * Adds a a number of trucks to the loading station. If the loading station is 
	 * getting blocked by trucks, the incoming trucks will be queued.
	 * 
	 * @param truck
	 */
	public void addTruck(List<EntyTruck> trucks) {
		for (EntyTruck t : trucks) 
			addTruck(t);
	}
	
	/**
	 * Adds a truck to an available loading station. If the loading station is 
	 * blocked by other trucks, the incoming truck will be queued.
	 * 
	 * @param truck
	 */
	public void addTruck(EntyTruck truck) {
		// Is there an empty loading station available?
		// If not, queue the incoming truck.
		if (list.size() < size) {
			list.add(truck);
			model.enqueueEvent(new EvtEndLoading(truck, model.getTime()));
		} else
			queue.enqueue(truck);
	}
	
	/**
	 * Removes truck from the loading station. If there is a truck waiting in
	 * the queue, it will be dequeued and added to the loading station.
	 * 
	 * @param truck
	 */
	public EntyTruck removeTruck(EntyTruck truck) throws InvalidEntityException {
		// Does the event match a truck actually being weighted?
		if (!list.remove(truck))
			throw new InvalidEntityException();

		// Start loading next truck (if any truck is being queued)
		if (!queue.isEmpty())
			addTruck(queue.dequeue());
		
		return truck;
	}

	@Override
	public String toString() {
		return getName() + "(" + size + ") " + list + "\n" + queue;
	}
}
