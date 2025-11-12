package de.emir.rcp.editor.model.impl;

import java.net.URL;
import java.util.Arrays;

import de.emir.model.universal.core.CorePackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderExtImpl;

public class EMIRLabelProvider extends GenericLabelProvider
{
	private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager
			.getLogger(EMIRLabelProvider.class);

	private static final URL COMPOSITION_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/source.png");
	private static final URL GRAY_FOLDER_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/folder_disabled.png");
	private static final URL COMPOSITION_PART_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/extension_composition.png");
	private static final URL NO_UOBJECT_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/code_primitive.png");
	private static final URL AGGREGATION_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/snippet_folder.png");
	private static final URL CONTENT_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/text_snippet.png");
	private static final URL PROPERTY_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/data_object.png");
	private static final URL APP_URL = ResourceManager.get(EMIRLabelProvider.class)
			.getResource("icons/emiricons/32/molecules.png");
	
	public EMIRLabelProvider() {
		mNameFeatures.addAll(Arrays.asList(
				CorePackage.Literals.NamedElement_name, 
				CorePackage.Literals.MDIdentifier_code
		));
		mNameProvider = new QualifiedNameProviderExtImpl(mNameFeatures);
		mUseQualifiedName = false;
	}

	@Override
	public URL getIcon(Object ptr) {
		if (!(ptr instanceof Pointer) && !(ptr instanceof UObject)) {
			return super.getIcon(ptr); // This is no Pointer, let the super class handle the outcome.
		}
		if (!(((Pointer) ptr).getValue() instanceof UObject)) {
			// Value is a POJO, no UOBject.
			return NO_UOBJECT_URL;
		}
		//check whether the object under the pointer is owned by its parent or if it is linked
		UObject uobj = (UObject) ((Pointer) ptr).getValue();
        UObject ptrContainer = uobj.getUContainer();
		if (ptrContainer != null && ptr != null) {
			if (uobj.getUContainer() == ptrContainer) { //owned by its parent (not referenced / shared)
				if (uobj.getUContainingFeature() != null && uobj.getUContainingFeature().isMany()) {
					return COMPOSITION_PART_URL;
				} else {
					// Try to differentiate between aggregation and composition, this does not semm to work yet.
					Iterable<UObject> compos = uobj.getContentIterator(UAssociationType.COMPOSITION);
					boolean isCompos = compos != null && compos.iterator() != null && compos.iterator().hasNext();
					Iterable<UObject> agrgtn = uobj.getContentIterator(UAssociationType.AGGREGATION);
					boolean isAgrgtn = agrgtn != null && agrgtn.iterator() != null && agrgtn.iterator().hasNext();

					if (isCompos) {
						return COMPOSITION_URL;						
					} else if (isAgrgtn) {
						return AGGREGATION_URL;
					}
					return CONTENT_URL;
				}
			} else {
				return GRAY_FOLDER_URL;
			}
		} else if (ptrContainer == null && ptr != null) {
			if (((Pointer) ptr).getPointedContainer() == null) {
				return APP_URL;
			}
			return PROPERTY_URL;
		} 
		return super.getIcon(ptr);
	}
}
