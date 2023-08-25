package de.emir.rcp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.Util;

import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.impl.DynamicObjectCharacteristicImpl;
import de.emir.model.universal.physics.impl.MultiViewObjectSurfaceInforamtionImpl;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.QuaternionImpl;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.rcp.ids.Basic;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.TypePointerImpl;


/**
 * Autocompletion for PointerStrings, according to the de.emir.tuml.ucore.runtime.pointer.PointerStrings class. 
 *
 * The PointerStringAutocompletion provider needs some base information to work on, that is either an concrete instance. This instance will be 
 * analysed to get the current values, to get the concrete class and for documentation purpose. 
 * However if no concrete instance is available (for example when used during design time) you may provide a root pointer to be the starting point. 
 * Such an root pointer may again be build upon a pointer string...
 * 
 * The CompletionProvider assumes that only one of the possible root elements (instance, pointer or classifier) is set. However if more than one
 * is set, they will be evaluated in the following order
 * 1) getRootElement() 2) getRootPointer() 3) getRootClassifier()
 * 
 * See below for further examples
 *
 * to embeed the autocompletion into an JTextComponent use the following code fragement
 * 
 * - Example of static root element
	\code{.java}
		JTextComponent tf = new JTextField();
		PointerStringAutocompletion provider = new PointerStringAutocompletion();
		provider.setRootElement(testObject);
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(300);
		ac.setShowDescWindow(true); 				//activate / deactivate the documentation popup
		ac.setParameterAssistanceEnabled(true);		//has to be true, otherwise the TemplateCompletion does not work.
		
		ac.install(tf);
	\endcode
	
 - example of dynamic root element
	if the root element of the pointer changes with each call, you may overwrite the getRootElement method as follows: 

	\code{.java}
		JTextComponent tf = new JTextField();
		PointerStringAutocompletion provider = new PointerStringAutocompletion() {
			@Override
			public UObject getRootElement(){
				//dynamically discover your root element
			}
		};
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(300);
		ac.setShowDescWindow(true); 				//activate / deactivate the documentation popup
		ac.setParameterAssistanceEnabled(true);		//has to be true, otherwise the TemplateCompletion does not work.
		
		ac.install(tf);
	\endcode
	
 - example of unknown root element but known type (e.g. Classifier)
 	
 	\code{.java}
		JTextComponent tf = new JTextField();
		PointerStringAutocompletion provider = new PointerStringAutocompletion() {
			@Override
			public Pointer getRootClassifier(){
				return MyPackage.Literals.MY_FANCY_CLASSIFIER;
			}
		};
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(300);
		ac.setShowDescWindow(true); 				//activate / deactivate the documentation popup
		ac.setParameterAssistanceEnabled(true);		//has to be true, otherwise the TemplateCompletion does not work.
		
		ac.install(tf);
	\endcode

for more information about the PointerStrings see: 
 * -	\ref pagePointer
 * -	de.emir.tuml.ucore.runtime.pointer.PointerStrings
 * -	test.de.emir.tuml.ucore.runtime.pointer.PointerStringsTest
 * @author sschweigert
 *
 */
public class PointerStringAutocompletion extends DefaultCompletionProvider{

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("PointerCompletion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextField tf = new JTextField();
		
		PhysicalObjectImpl testObject = new PhysicalObjectImpl();
		testObject.getPose().set(new CoordinateImpl(1,2,3,null), new QuaternionImpl());
		testObject.getCharacteristics().add(new DynamicObjectCharacteristicImpl(
				new AngularVelocityImpl(new EulerImpl(6, 5, 4, AngleUnit.DEGREE), AngularSpeedUnit.DEGREES_PER_HOUR, null),
				new VelocityImpl(new Vector3DImpl(7, 8, 9), SpeedUnit.KM_PER_MINUTE, null)));
		testObject.getCharacteristics().add(new DynamicObjectCharacteristicImpl(
				new AngularVelocityImpl(new EulerImpl(16, 15, 14, AngleUnit.DEGREE), AngularSpeedUnit.DEGREES_PER_HOUR, null),
				new VelocityImpl(new Vector3DImpl(17, 18, 19), SpeedUnit.KM_PER_MINUTE, null)));
		testObject.getCharacteristics().add(new MultiViewObjectSurfaceInforamtionImpl());
		
		PointerStringAutocompletion provider = new PointerStringAutocompletion();
//		provider.setRootElement(testObject);
//		provider.setRootPointer(new ObjectPointerImpl(testObject));
		provider.setRootClassifier(PhysicsPackage.Literals.PhysicalObject);
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(300);
		ac.setShowDescWindow(true);
		ac.setParameterAssistanceEnabled(true);
		
		ac.install(tf);
		
		frame.add(tf);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	}



	private Pointer 	mRootPointer; 		
	private UObject 	mRootElement;
	private UClassifier	mRootClassifier;
	//only one of the above member shall be set or its getter shall be overwritten
	
	public PointerStringAutocompletion() {
		super();
		
	}
	
	public void setRootElement(UObject rootElement) {
		mRootElement = rootElement;
	}
	public void setRootPointer(Pointer pointer) {
		mRootPointer = pointer;		
	}
	public void setRootClassifier(UClassifier cl) {
		mRootClassifier = cl;
	}
	public UObject getRootElement() {
		return mRootElement;
	}
	
	public Pointer getRootPointer() {
		return mRootPointer;
	}
	
	public UClassifier getRootClassifier() {
		return mRootClassifier;
	}
	
	protected boolean isValidChar(char ch) {
		return Character.isLetterOrDigit(ch) || ch=='_' ;//|| ch == '<' || ch == '>';
	}
	
	private String[] getEnteredTextAndSegment(JTextComponent comp) {
		Document doc = comp.getDocument();

		int dot = comp.getCaretPosition();
		Element root = doc.getDefaultRootElement();
		int index = root.getElementIndex(dot);
		Element elem = root.getElement(index);
		int start = elem.getStartOffset();
		int len = dot-start;
		try {
			doc.getText(start, len, seg);
		} catch (BadLocationException ble) {
			ble.printStackTrace();
			return new String[] {null, null};
		}

		int segEnd = seg.offset + len;
		start = segEnd - 1;
		while (start>=seg.offset && isValidChar(seg.array[start])) {
			start--;
		}
		start++;

		len = segEnd - start;		
		String sec = len==0 ? EMPTY_STRING : new String(seg.array, start, len);
		String fir = new String(seg.array, 0, start);
		return new String[] { fir, sec};
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Completion> getCompletionsImpl(JTextComponent comp) {

		List<Completion> retVal = new ArrayList<Completion>();
		String[] segAndText = getEnteredTextAndSegment(comp);
		String text = segAndText[1];
		String ptrStr = segAndText[0];
		boolean isComponent = false;
		if (ptrStr.endsWith("<") && ptrStr.length() >= 11) {
			ptrStr = ptrStr.substring(0, ptrStr.length()-11); //we can not handle this information now
			isComponent = true;
		}
		Pointer basePtr = null;
		if (getRootElement() != null) {
			basePtr = PointerOperations.create(getRootElement());
		}else if (getRootPointer() != null) {
			basePtr = getRootPointer().copy();
		}else if (getRootClassifier() != null) {
			basePtr = new TypePointerImpl(null, getRootClassifier(), -1);
		}
		assert(basePtr != null);
		
		Pointer ptr = null; 
		if (ptrStr != null && ptrStr.isEmpty() == false) {
			ptr = PointerOperations.createPointerFromString(basePtr, ptrStr);
		}else
			ptr = basePtr; //no need to copy, that has been done before
		

		assert(ptr != null);
		if (ptr != null){
			UType t = ptr.getType();
			if (t == null) t = ptr.getExpectedType();
			if (t != null && t instanceof UClassifier){
				if (isComponent == false) {
					addAllFeaturesAndOperations(ptr, (UClassifier)t, retVal);
				}else {
					addAllClassifiers(retVal);
				}
			}
			if (t instanceof UPrimitiveType == false)
				retVal.add(getUComponentCompletion()); //always add the UComponent<?> completion
		}		
		 
		
		
		Collections.sort(retVal);
		completions = retVal;
		return super.getCompletionsImpl(comp);
	}
	
	
	private ArrayList<Completion> mClassifierCompletions;
	private void addAllClassifiers(List<Completion> retVal) {
		if (mClassifierCompletions == null) {
			mClassifierCompletions = new ArrayList<>();
			for (UClassifier cl : UCoreMetaRepository.getAllClassifier()) {
				String doc = cl.getDocumentation();
				String name = cl.getName();
				mClassifierCompletions.add(new BasicCompletion(this, name, "Classifier:" + name, doc));
			}
		}
		retVal.addAll(mClassifierCompletions);
	}

	private void addAllFeaturesAndOperations(Pointer ptr, UClassifier cl, List<Completion> retVal) {
		for (UStructuralFeature f : cl.getAllStructuralFeatures()) {
			retVal.add(buildFeatureCompletion(ptr, cl, f));
		}
		for (UOperation op : cl.getAllOperations()) {
			if (hasInputParameters(op) == false)
				retVal.add(buildOperationCompletion(ptr, cl, op));
		}
		
	}

	private Completion buildOperationCompletion(Pointer ptr, UClassifier cl, UOperation op) {
		UType type = ptr.getType(); if (type == null) type = ptr.getExpectedType();
		String signature = op.getType().getName();
		if (op.isMany()) signature += "[*]";
		signature += " - " + ((UClassifier)op.getUContainer()).getName();
		String docSignature = op.getType().getName(); if (op.isMany()) docSignature += "[*]"; docSignature += " " + op.getName() + " () ";
		String doc = "<b>" + type.getName() + "<br><i>" + docSignature + "</b></i><br><h2>Documentation</h2>"+op.getDocumentation() + "<br>";
		if (op.isMany()){
			
			return new TemplateCompletion(this, op.getName() + "()", op.getName() + "() -" + signature, op.getName()+"():${idx}${cursor}", op.getType().getName(), doc);
		}else{
			return new BasicCompletion(this, op.getName() + "()", signature, doc);
		}
	}

	private boolean hasInputParameters(UOperation op) {
		for (UParameter p : op.getParameters()) {
			if (p.getDirection() != UDirectionType.RETURN)
				return true;
		}
		return false;
	}

	private Completion buildFeatureCompletion(Pointer ptr, UClassifier cl, UStructuralFeature f) {
		UType type = ptr.getType(); if (type == null) type = ptr.getExpectedType();
		String doc = "<b>" + type.getName() + "<br><i>" + f.getName() + " : " + f.getType().getName() + "</b></i><br><h2>Documentation</h2>"+f.getDocumentation() + "<br><h2>Value</h2>";
		Object val = ptr.getValue();
		if (f.isMany()){
			if (val != null){
				List l = (List) f.get((UObject)val);
				doc += "<ol>\n";
				int m = Math.min(l.size(), 50);
				for (int i = 0; i < m; i++){
					doc += "<li value=\""+i+"\">" + l.get(i) + "</li>";
				}
				doc += "</ol>";
			}
			return new TemplateCompletion(this, f.getName(), f.getName()+":"+f.getType().getName(), f.getName()+":${idx}${cursor}", f.getType().getName(), doc);
		}else{
			if (val != null)
				doc = doc + f.get((UObject) val);
			return new BasicCompletion(this, f.getName(), f.getType().getName(), doc);
		}
	}

	Completion getUComponentCompletion() {
		String shortDescription = "add a component with specified type";
		String summary = "<code>UComponent<Classifier>:idx</code>";
		String template = "UComponent<${Classifier}>:${idx}";
		return new TemplateCompletion(this, "UComponent", "UComponent", template, shortDescription, summary);
	}
}
