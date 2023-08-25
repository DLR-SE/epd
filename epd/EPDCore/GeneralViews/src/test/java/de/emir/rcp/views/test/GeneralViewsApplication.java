//package de.emir.rcp.views.test;
//
//import javax.swing.JFrame;
//
//import de.emir.rcp.MainWindow;
//import de.emir.rcp.RCP;
//import de.emir.rcp.editor.text.TextEditor;
//import de.emir.rcp.ui.editor.AbstractFileEditorFactory;
//import de.emir.rcp.ui.editor.FileEditorLayout;
//import de.emir.rcp.views.console.ConsoleView;
//import de.emir.rcp.views.properties.PropertyView;
//import de.emir.rcp.views.workspace.WorkspaceView;
//
//public class GeneralViewsApplication extends RCP
//{
//
//
//	protected GeneralViewsApplication(JFrame frame) {
//		super(frame);
//	}
//
//	public static void main(String[] args){
//		MainWindow frame = new MainWindow("Test Application");
//		GeneralViewsApplication rcp = new GeneralViewsApplication(frame);
//
//		frame.setBounds( 20, 20, 800, 600 );
//		frame.show();
//	}
//
//
//	@Override
//	protected void registerViews() {
//		ConsoleView d = new ConsoleView();
//		d.setCloseable(true);
//		windowManager().addView(0, 0, 1, 4, d);
//		d.setVisible(true);
//
//		WorkspaceView wv = new WorkspaceView();
//		wv.setCloseable(true);
//		windowManager().addView(0, 0, 1, 4, wv);
//		wv.setVisible(true);
//
//		PropertyView pv = new PropertyView();
//		pv.setCloseable(true);
//		windowManager().addView(0, 0, 1, 4, pv);
//		pv.setVisible(true);
//	}
//
//	@Override
//	protected void registerEditors() {
//		windowManager().registerFileEditor(new AbstractFileEditorFactory<TextEditor>() {
//
//			@Override
//			public TextEditor read(FileEditorLayout layout) {
//				return new TextEditor(this, layout.getPath());
//			}
//
//		}, "Text Editor", new String[]{"*.txt", "*.xml", "*.ini"});
//	}
//}
