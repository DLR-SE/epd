package de.emir.model.universal.units;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**

 * A rotation normally is an action. It uses the same representation as a orientation.  
 * @generated 
 */
@UMLInterface(name = "Rotation")
public interface Rotation extends UObject 
{
	
	/**
	 *	@generated 
	 */
	Vector2D transform2D(final Vector2D other);
	/**
	 *	@generated 
	 */
	Vector3D transform3D(final Vector3D other);
}
