package de.emir.rcp.editor.model.impl;

import java.net.URL;
import java.util.Arrays;

import de.emir.model.universal.core.CorePackage;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderExtImpl;
import de.emir.ui.utils.treetable.umodel.UNode;

public class EMIRLabelProvider extends GenericLabelProvider
{

	public EMIRLabelProvider() {
		mNameFeatures.addAll(Arrays.asList(
				CorePackage.Literals.NamedElement_name, 
				CorePackage.Literals.MDIdentifier_code
		));
		mNameProvider = new QualifiedNameProviderExtImpl(mNameFeatures);
		mUseQualifiedName = false;
	}	
	
	private URL 		mYellowFolderURL = ResourceManager.get(getClass()).getResource("icons/emiricons/32/folder.png");
	private URL 		mGrayFolderURL = ResourceManager.get(getClass()).getResource("icons/emiricons/32/folder_special.png");

	@Override
	public URL getIcon(Object ptr) {
		URL url = null;
		if (ptr instanceof UObject == false)
			return ResourceManager.get(getClass()).getResource("icons/emiricons/32/folder_open.png");
        if (ptr instanceof UNode) {
            ULog.info("ptr is unode");
        }
		//check wheather the object under the pointer is owned by its parent or if it is linked
		UObject uobj = (UObject)ptr;
//        ULog.info(PointerOperations.toPointerString(PointerOperations.createPointerToObject(uobj)));
		UObject ptrContainer = uobj.getUContainer();
		if (ptrContainer != null && ptr != null) {
			if (uobj.getUContainer() == ptrContainer) //owned by its parent (not referenced / shared)
				return mYellowFolderURL;
			else
				return mGrayFolderURL;
		}
		
		return url;
	}
}
