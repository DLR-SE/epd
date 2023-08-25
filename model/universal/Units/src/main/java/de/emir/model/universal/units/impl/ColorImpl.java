package de.emir.model.universal.units.impl;

import java.util.HashMap;

import de.emir.model.universal.units.Color;
import de.emir.model.universal.units.PredefinedColour;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.universal.units.impl.RGBColorImpl;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Color.class)
public class ColorImpl extends RGBColorImpl implements Color  
{
	
	/**
	 *	@generated 
	 */
	private PredefinedColour mColor = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public ColorImpl(){
		super();
		//set the default values and assign them to this instance 
		setColor(mColor);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ColorImpl(final Color _copy) {
		super(_copy);
		mColor = _copy.getColor();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ColorImpl(float _r, float _g, float _b, float _a, PredefinedColour _color) {
		super(_r,_g,_b,_a);
		mColor = _color; 
	}
	
	public ColorImpl(PredefinedColour pc){
		super();
		setColor(pc);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Color;
	}

	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	
	private static HashMap<PredefinedColour, java.awt.Color> sColorMap;
	
	private void updateColor(PredefinedColour newColor){
		if (newColor != null){
			java.awt.Color c = getColor(newColor);
			if (c != null){
				setR(c.getRed() / 255.0f);
				setG(c.getGreen() / 255.0f);
				setB(c.getBlue() / 255.0f);
			}
		}
	}

	private static java.awt.Color getColor(PredefinedColour newColor) {
		if (sColorMap == null){
			sColorMap = new HashMap<PredefinedColour, java.awt.Color>();
			for (UEnumerator lit : UnitsPackage.Literals.PredefinedColour.getLiterals()){
				UAnnotationDetail detail = lit.getAnnotationDetail("Color", "Hex");
				if (detail == null) continue;
				String hex = detail.getValue();
				if (hex != null)
					sColorMap.put(PredefinedColour.get(lit.getValue()), hex2Rgb(hex));
			}
		}
		return sColorMap.get(newColor);
	}

	public static java.awt.Color hex2Rgb(String colorStr) {
	    return new java.awt.Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}

	
	/**
	 *	@generated not
	 */
	@Override
	public void setColor(PredefinedColour _color) {
		Notification<PredefinedColour> notification = basicSet(mColor, _color, UnitsPackage.Literals.Color_color);
		mColor = _color;
		if (notification != null){
			dispatchNotification(notification);
		}
		updateColor(_color);
	}
	/**
	 *	@generated 
	 */
	public PredefinedColour getColor() {
		return mColor;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ColorImpl{" +
		" r = " + getR() + 
		" g = " + getG() + 
		" b = " + getB() + 
		" a = " + getA() + 
		"}";
	}
}
