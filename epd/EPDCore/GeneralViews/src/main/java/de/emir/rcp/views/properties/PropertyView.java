package de.emir.rcp.views.properties;

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
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.disposables.Disposable;

public class PropertyView extends AbstractView {
	public static final String UNIQUE_ID = "Properties";

	JPropertySheet mPropertySheet = null;

	private Disposable selectionObserver;

	public PropertyView() {
		super(UNIQUE_ID);
	}

	// private void setInput(Selection selection) {
	// final List<IProperty> sortedProperties = new ArrayList<>();
	// if (selection != null && selection.firstValue() != null){
	// Map<String, IProperty> properties =
	// PropertyManager.getInstance().collectProperties(selection.firstValue());
	// PropertyManager.getInstance().sort(properties, sortedProperties);
	// }
	// SwingUtilities.invokeLater(new Runnable() {
	// @Override
	// public void run() {
	// mPropertySheet.clear();
	// if (sortedProperties != null){
	// mPropertySheet.addProperties(sortedProperties);
	// }
	// }
	// });
	// }

	@Override
	public Component createContent() {
		
		Container parent = new JPanel();
		
		mPropertySheet = new JPropertySheet();
		JScrollPane jsp = new JScrollPane(mPropertySheet);

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
		// TODO HiWi: Ensure that we do not get to busy by getting updates / change with
		// high frequency
		final List<IProperty> sortedProperties = new ArrayList<>();
		if (selection != null && selection.size() != 0 && selection.get(0) != null) {
			Map<String, IProperty> properties = PropertyManager.getInstance().collectProperties(selection.get(0));
			PropertyManager.getInstance().sort(properties, sortedProperties);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mPropertySheet.clear();
				if (sortedProperties != null) {
					mPropertySheet.addProperties(sortedProperties);
				}
			}
		});
	}
}
