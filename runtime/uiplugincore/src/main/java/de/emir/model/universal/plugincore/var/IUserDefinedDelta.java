package de.emir.model.universal.plugincore.var;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import java.util.List;
import java.util.Map;

/**
 *	@generated 
 */
@UMLInterface(name = "IUserDefinedDelta")
public interface IUserDefinedDelta extends UObject 
{
	
	
	
	/**
	 *	@generated 
	 */
	void apply(final List<AbstractKeyBinding> bindings);

	/**
	 *	@generated_not
	 */
	void apply(final Map<String, List<AbstractKeyBinding>> bindings);
}
