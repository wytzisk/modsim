package de.hsbo.fbg.modsim.sample;

import de.hsbo.fbg.modsim.SimEntity;
import de.hsbo.fbg.modsim.SimEvent;
import de.hsbo.fbg.modsim.SimEventListener;
import de.hsbo.fbg.modsim.SimException;

/**
 * Represents a truck entity as part of a transport model.
 * 
 * @author Andreas Wytzisk
 *
 */
public class EntyTruck extends SimEntity implements SimEventListener {

	/**
	 * Instantiate a truck object.
	 * 
	 * @param model
	 * @param name
	 */
	public EntyTruck(TransportModel model, String name) {
		super(model, name);
	}

	@Override
	public void handleEvent(SimEvent e) throws SimException {
		
		// process only those events, which are scheduled for this truck
		if (e.getEntity() == this) {

			if (e instanceof EvtStartLoading)
				((TransportModel)this.model).loadStat.addTruck(this);
			
			else if (e instanceof EvtEndLoading) {
				((TransportModel)this.model).loadStat.removeTruck(this);
				((TransportModel)this.model).weighStat.addTruck(this);
			}
			
			else if (e instanceof EvtEndWeighing)
				((TransportModel)this.model).weighStat.removeTruck(this);
		}

	}

}
