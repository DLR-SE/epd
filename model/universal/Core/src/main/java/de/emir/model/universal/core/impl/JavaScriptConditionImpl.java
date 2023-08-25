package de.emir.model.universal.core.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import de.emir.model.universal.core.Condition;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.JavaScriptCondition;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;


/**
 *	@generated 
 */
@UMLImplementation(classifier = JavaScriptCondition.class)
@Deprecated
public class JavaScriptConditionImpl extends UObjectImpl implements JavaScriptCondition , Condition 
{
	
	
	/**
	 The script to evaluate.
	 * There are two options to define a script:
	 * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term "function"
	 * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called "reference". This method is used if the first word of the script is NOT "function" 
	 * @generated 
	 */
	private String mScript = "";
	/**
	 an optional reference value that is provided as function parameter to the script function 
	 * @generated 
	 */
	private UObject mReference = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public JavaScriptConditionImpl(){
		super();
		//set the default values and assign them to this instance 
		setReference(mReference);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public JavaScriptConditionImpl(final JavaScriptCondition _copy) {
		mScript = _copy.getScript();
		mReference = _copy.getReference();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public JavaScriptConditionImpl(String _script, UObject _reference) {
		mScript = _script;
		mReference = _reference; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.JavaScriptCondition;
	}
	
	/**
	 The script to evaluate.
	 * There are two options to define a script:
	 * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term "function"
	 * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called "reference". This method is used if the first word of the script is NOT "function" 
	 * @generated 
	 */
	public void setScript(String _script) {
		if (needNotification(CorePackage.Literals.JavaScriptCondition_script)){
			String _oldValue = mScript;
			mScript = _script;
			notify(_oldValue, mScript, CorePackage.Literals.JavaScriptCondition_script, NotificationType.SET);
		}else{
			mScript = _script;
		}
	}

	/**
	 The script to evaluate.
	 * There are two options to define a script:
	 * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term "function"
	 * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called "reference". This method is used if the first word of the script is NOT "function" 
	 * @generated 
	 */
	public String getScript() {
		return mScript;
	}
	/**
	 an optional reference value that is provided as function parameter to the script function 
	 * @generated 
	 */
	public void setReference(UObject _reference) {
		Notification<UObject> notification = basicSet(mReference, _reference, CorePackage.Literals.JavaScriptCondition_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 an optional reference value that is provided as function parameter to the script function 
	 * @generated 
	 */
	public UObject getReference() {
		return mReference;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	private ScriptEngineManager			mScriptManager = null;
	private ScriptEngine				mScriptEngine = null;
	private String 						mScriptName = null;
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean evaluate()
	{
		try{
			if (mScriptManager == null){
				mScriptManager = new ScriptEngineManager();
			}
			if (mScriptEngine == null && getScript() != null && getScript().isEmpty() == false) {
				mScriptEngine = mScriptManager.getEngineByName("JavaScript");
				String script = getJavaScript();
				if (script != null && script.isEmpty() == false){
					mScriptName = getScriptName(script);
					if (mScriptName != null && mScriptName.isEmpty() == false){
						ULog.debug("Compile Scipt: " + mScriptName + " content: " + script);
						try{
							mScriptEngine.eval(script);
						}catch(ScriptException se){
							ULog.error("Failed to compile JavaScript with following error: " + se.getMessage() + " Line: " + se.getLineNumber() + " Column: " + se.getColumnNumber());
							se.printStackTrace();
							mScriptEngine = null;
							mScriptName = null;
						}
						
					}
				}			    
			}
			if (mScriptEngine != null && mScriptName != null){
				Invocable inv = (Invocable) mScriptEngine;
		        Object n = inv.invokeFunction(mScriptName, getReference());
		        return (Boolean)n;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		}
        
		return false;
	}

	private String getScriptName(String script){
		if (script.startsWith("function") == false)
			return null;
		int idx = script.indexOf('(');
		if (idx < 0) return null;
		String[] split = script.substring(9).trim().split(" ");
		if (split != null && split.length > 0)
			return split[0];
		return null;
	}

	/**
	 * creates a fully defined java script function or simply returns the script if it starts with the term "function"
	 * @return
	 */
	private String getJavaScript() {
		String script = getScript();
		if (script == null || script.isEmpty())
			return null;
		script = script.trim();
		if (script.startsWith("function"))
			return script;
		return "function eval(reference) { "+script+" }";
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "JavaScriptConditionImpl{" +
		" script = " + getScript() + 
		"}";
	}
}
