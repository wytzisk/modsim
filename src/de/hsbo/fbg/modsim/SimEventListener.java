package de.hsbo.fbg.modsim;

/**
 * Listener interface for receiving a SimEvent
 * @author Andreas Wytzisk
 *
 */
public interface SimEventListener {
	
	/**
	 * Handle the specified event.
	 * 
	 * @param e
	 * @throws SimException
	 */
	public void handleEvent(SimEvent e) throws SimException;

}