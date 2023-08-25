package de.emir.service.geometry;

import java.util.List;

import de.emir.model.universal.spatial.Envelope;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true)	
public interface ISpatialIndex extends UObject 
{
	
	/**
	 *	@generated 
	 */
	void insert(final Envelope env, final Object value);

	/**
	 *	@generated 
	 */
	boolean remove(final Envelope env, final Object value);

	/**
	 *	@generated 
	 */
	List<Object> query(final Envelope env);

	/**
	 *	@generated 
	 */
	void query(final Envelope env, final ISpatialIndexVisitor visitor);
	
}
