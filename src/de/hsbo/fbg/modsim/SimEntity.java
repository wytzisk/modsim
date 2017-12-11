package de.hsbo.fbg.modsim;


/**
 * Simulation entity associated with a simulation model.
 * 
 * @author wytzisk
 *
 */
public abstract class SimEntity {
	
	protected SimModel model;
	private String name;
	
	/**
	 * Creates a SimEntity associated with the specified simulation model 
	 * and name.
	 * 
	 * @param model
	 * @param name
	 */
	public SimEntity(SimModel model, String name) {
		this.model = model;
		this.name = name;
	}

	/**
	 * Returns the associated simulation model.
	 * 
	 * @return model
	 */
	public SimModel getModel() {
		return model;
	}
	
	/**
	 * Returns the entity's name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}	
	
}
