package de.emir.model.universal.math.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;

import java.util.Random;

import de.emir.model.universal.math.Function1;
import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.StringFunction1;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


/**
 *	@generated 
 */
@UMLImplementation(classifier = StringFunction1.class)
public class StringFunction1Impl extends UObjectImpl implements StringFunction1 , Function1 
{
	
	
	/**
	 textual definition of the function. 
	 * The definition string needs to have at least one x or X variable and no other non digit values
	 * for example
	 * log(x) - y * (sqrt(x^cos(x)))
	 * @ntoe x and X will be handled as the same variable
	 * @generated 
	 */
	private String mDefinition = "";

	/**
	 *	Default constructor
	 *	@generated
	 */
	public StringFunction1Impl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public StringFunction1Impl(final StringFunction1 _copy) {
		mDefinition = _copy.getDefinition();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public StringFunction1Impl(String _definition) {
		mDefinition = _definition;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.StringFunction1;
	}
	
	/**
	 textual definition of the function. 
	 * The definition string needs to have at least one x or X variable and no other non digit values
	 * for example
	 * log(x) - y * (sqrt(x^cos(x)))
	 * @ntoe x and X will be handled as the same variable
	 * @generated 
	 */
	public void setDefinition(String _definition) {
		if (needNotification(MathPackage.Literals.StringFunction1_definition)){
			String _oldValue = mDefinition;
			mDefinition = _definition;
			notify(_oldValue, mDefinition, MathPackage.Literals.StringFunction1_definition, NotificationType.SET);
		}else{
			mDefinition = _definition;
		}
	}

	/**
	 textual definition of the function. 
	 * The definition string needs to have at least one x or X variable and no other non digit values
	 * for example
	 * log(x) - y * (sqrt(x^cos(x)))
	 * @ntoe x and X will be handled as the same variable
	 * @generated 
	 */
	public String getDefinition() {
		return mDefinition;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	
	private Expression mExpression = null;
	private IValueChangeListener mListener = null;
	
	private Expression getExpression() {
		if (mExpression == null) {
			try {
				mExpression = new ExpressionBuilder(getDefinition().toLowerCase())
		                .variables("x")
		                .build();
				//from now on we do observe ourselef to be notified about changes of the definition
				if (mListener == null) {
					registerListener(mListener = new IValueChangeListener() {
						@Override
						public void onValueChange(Notification notification) {
							//invalidation of the expression is sufficient, the next time it shall be used it will be regenerated
							mExpression = null;
						}
					});
				}
			}catch(Exception e) {
				e.printStackTrace();
				mExpression = null;
			}
		}
		return mExpression;
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean isValid()
	{
		return getExpression() != null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public double getValue(final double x)
	{
		Expression expr = getExpression();
		if (expr != null) {
			expr.setVariable("x", x);
			return expr.evaluate();
		}
		return Double.NaN;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "StringFunction1Impl{" +
		" definition = " + getDefinition() + 
		"}";
	}
}
