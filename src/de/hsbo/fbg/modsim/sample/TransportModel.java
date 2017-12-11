package de.hsbo.fbg.modsim.sample;

import java.util.Arrays;

import de.hsbo.fbg.modsim.SimModel;

/**
 * Sample transportation model according to the example given in 
 * Page & Kreutzer (2005), chapter 2. Simulates 6 trucks being loaded 
 * at a queued loading station (with two loading hubs) and a queued 
 * weighing station.
 *  
 * @author wytzisk
 *
 */
public class TransportModel extends SimModel {
	
	private static int LOADING_HUBS = 2;
	private static int TRUCK_COUNT = 6;
	
	private EntyTruck[] trucks = new EntyTruck[TRUCK_COUNT];
	
	protected EntyLoadStat loadStat = new EntyLoadStat(this, "Loading station", LOADING_HUBS);
	protected EntyWeighStat weighStat = new EntyWeighStat(this, "Weighing station");
	
	/* (non-Javadoc)
	 * @see de.hsbo.fbg.modsim.SimModel#dump()
	 */
	@Override
	public void dump() {
		System.out.println("Time: " + this.getTime());
		System.out.println(loadStat);
		System.out.println(weighStat);
		System.out.println(this.getEvtList());
	}

	/* (non-Javadoc)
	 * @see de.hsbo.fbg.modsim.SimModel#initialize()
	 */
	@Override
	public void initialize() {
		for (int i=0; i<TRUCK_COUNT; i++) {
			trucks[i] = new EntyTruck(this, "Truck" + String.valueOf(i));
			this.addSimEventListener(trucks[i]);
		}
		
		// 2 trucks already being loaded, 3 queued
		// Corresponding "end loading events" are being scheduled implicitly
		loadStat.addTruck(Arrays.asList(trucks[0], trucks[1], trucks[2], trucks[3], trucks[4]));
		
		// 1 truck already being weight
		// Corresponding "end weighing event" is being scheduled implicitly
		weighStat.addTruck(trucks[5]);
		
		dump();
	}
	
	public static void main(String[] args) throws Exception {
		TransportModel model = new TransportModel();
		model.initialize();
		model.simulateTimespan(250, true);
	}

}
