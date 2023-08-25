package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "WatercraftHull")	
public interface WatercraftHull extends UObject 
{
	/**
	
	 * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)
	 * @generated 
	 */
	@UMLProperty(name = "beam", associationType = AssociationType.COMPOSITE)
	public void setBeam(Length _beam);
	/**
	
	 * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)
	 * @generated 
	 */
	@UMLProperty(name = "beam", associationType = AssociationType.COMPOSITE)
	public Length getBeam();
	/**
	
	 * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.
	 * @generated 
	 */
	@UMLProperty(name = "lengthAtWaterline", associationType = AssociationType.COMPOSITE)
	public void setLengthAtWaterline(Length _lengthAtWaterline);
	/**
	
	 * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.
	 * @generated 
	 */
	@UMLProperty(name = "lengthAtWaterline", associationType = AssociationType.COMPOSITE)
	public Length getLengthAtWaterline();
	/**
	
	 * (LOA) is the extreme length from one end to the other
	 * @generated 
	 */
	@UMLProperty(name = "overAllLength", associationType = AssociationType.COMPOSITE)
	public void setOverAllLength(Length _overAllLength);
	/**
	
	 * (LOA) is the extreme length from one end to the other
	 * @generated 
	 */
	@UMLProperty(name = "overAllLength", associationType = AssociationType.COMPOSITE)
	public Length getOverAllLength();
	/**
	
	 * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.
	 * @generated 
	 */
	@UMLProperty(name = "draft", associationType = AssociationType.COMPOSITE)
	public void setDraft(Length _draft);
	/**
	
	 * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.
	 * @generated 
	 */
	@UMLProperty(name = "draft", associationType = AssociationType.COMPOSITE)
	public Length getDraft();
	/**
	
	 * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side
	 * @generated 
	 */
	@UMLProperty(name = "mouldedDepth", associationType = AssociationType.COMPOSITE)
	public void setMouldedDepth(Length _mouldedDepth);
	/**
	
	 * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side
	 * @generated 
	 */
	@UMLProperty(name = "mouldedDepth", associationType = AssociationType.COMPOSITE)
	public Length getMouldedDepth();
	/**
	
	 * Freeboard (FB) is depth plus the height of the keel structure minus draft.
	 * @generated 
	 */
	@UMLProperty(name = "freeboard", associationType = AssociationType.COMPOSITE)
	public void setFreeboard(Length _freeboard);
	/**
	
	 * Freeboard (FB) is depth plus the height of the keel structure minus draft.
	 * @generated 
	 */
	@UMLProperty(name = "freeboard", associationType = AssociationType.COMPOSITE)
	public Length getFreeboard();
	
}
