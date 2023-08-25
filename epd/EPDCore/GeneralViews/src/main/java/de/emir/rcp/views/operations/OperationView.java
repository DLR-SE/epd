package de.emir.rcp.views.operations;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.disposables.Disposable;

public class OperationView extends AbstractView {
	public static final String UNIQUE_ID = "Operations";

	JOperationSheet mOperationsSheet = null;

	private Disposable selectionObserver;

	public OperationView() {
		super(UNIQUE_ID);
	}


	@Override
	public Component createContent() {
		
		Container parent = new JPanel();
		
		mOperationsSheet = new JOperationSheet();
		JScrollPane jsp = new JScrollPane(mOperationsSheet);

		parent.setLayout(new BorderLayout());
		parent.add(jsp, BorderLayout.CENTER);
		
		return parent;

	}

	@Override
	public void onOpen() {

		selectionObserver = PlatformUtil.getSelectionManager().subscribe(oo -> {
			if (oo.isPresent() == true) {

				Object selection = oo.get();

				if (selection instanceof List) {

					List<?> selectionList = (List<?>) selection;
					changed(selectionList);
				} else if (selection != null) {
					ArrayList<Object> selectionList = new ArrayList<Object>();
					selectionList.add(selection);
					changed(selectionList);
				}

			}
		});
	}

	@Override
	public void onClose() {
		selectionObserver.dispose();
	}

	public void changed(List<?> selection) {
		if (selection != null && selection.isEmpty() == false && selection.get(0) instanceof UObject) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					UObject uobj = (UObject)selection.get(0);
					mOperationsSheet.setInput(uobj);
					
				}
			});
		}
		System.out.println();
		// TODO HiWi: Ensure that we do not get to busy by getting updates / change with
		// high frequency
//		final List<IProperty> sortedProperties = new ArrayList<>();
//		if (selection != null && selection.size() != 0 && selection.get(0) != null) {
//			Map<String, IProperty> properties = PropertyManager.getInstance().collectProperties(selection.get(0));
//			PropertyManager.getInstance().sort(properties, sortedProperties);
//		}
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				mOperationsSheet.clear();
//				if (sortedProperties != null) {
//					mOperationsSheet.addProperties(sortedProperties);
//				}
//			}
//		});
	}
}
