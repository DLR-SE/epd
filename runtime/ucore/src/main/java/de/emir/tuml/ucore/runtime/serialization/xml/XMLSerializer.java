package de.emir.tuml.ucore.runtime.serialization.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.serialization.ISerializer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.XMLReader;


public class XMLSerializer extends AbstractSerializer implements ISerializer
{
	public static final String 	XMI_NS = "http://www.omg.org/XMI";
	public static final String 	XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
	private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(XMLSerializer.class);
	public interface IXMLReadListener {
		public void onObjectRead(UObject object, final Node xmlNode, XMLReader reader, XMLReaderImpl serializer);

		public UClassifier onCouldNotFindClassifier(String type, UObject instance, UStructuralFeature feature,
				Node node, XMLReader mReader, XMLReaderImpl xmlReaderImpl);
	}


	protected class XMLWriter {
		
		private HashMap<UObject, String>		mObjectIds = new HashMap<UObject, String>();
		
		
		public String createID(UObject instance){
			String str = UUID.randomUUID().toString();
			mObjectIds.put(instance, str);
			return str;
		}
		public String getID(UObject instance){
			return mObjectIds.get(instance);
		}
		
		
		OutputStream write(UObject instance, final OutputStream stream) throws ParserConfigurationException, TransformerException{
			final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			final Document doc = builder.newDocument();
			
			Element el = writeInstance(doc, instance);
			doc.appendChild(el);
			
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			final DOMSource source = new DOMSource(doc);
//			final OutputStream stream = new ByteArrayOutputStream();
			final StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
			return stream;
		}
		
		
		/*
		 * 		public string write (UObject obj){
			XDocument doc = new XDocument();
			XElement el = write(doc, obj);
			el.Add(new XAttribute(XNamespace.Xmlns + "xmi", "http://www.omg.org/XMI"));
			el.Add(new XAttribute(XNamespace.Xmlns + "xsi", "http://www.w3.org/2001/XMLSchema-instance"));
			doc.Add(el);
			return doc.ToString();
		}
		 */
		public String write(UObject instance) throws ParserConfigurationException, TransformerException{
			final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			final Document doc = builder.newDocument();
			
			Element el = writeInstance(doc, instance);
			doc.appendChild(el);
			
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
		    DOMSource domSource = new DOMSource(doc);
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    TransformerFactory tf = TransformerFactory.newInstance();
		    transformer.transform(domSource, result);

			return writer.toString();
		}

		protected Element writeInstance(Document doc, UObject instance) {
			UClassifier cl = instance.getUClassifier();
			Element el = doc.createElement(cl.getName());
			el.setAttributeNS(XMI_NS, "xmi:id", createID(instance));
			writeProperties(doc, el, instance.getAllProperties());
			
			writeAttributes(doc, el, instance, cl.getAllAttributes());
			writeReferences(doc, el, instance, cl.getAllReferences());
			return el;
		}


		protected void writeProperties(Document doc, Element el, Collection<IProperty> allProperties) {
			if (allProperties != null && allProperties.isEmpty() == false){
				for (IProperty p : allProperties){
					Element pel = doc.createElement("UProperty");
					pel.setAttribute("n", p.getName());
					pel.setAttribute("d", p.getDescription());
					pel.setAttribute("e", p.isEditable()?"true":"false");
					if (p.getValue() != null){
						pel.setAttribute("t", p.getType().getSimpleName());
						pel.setAttribute("v", p.getValue().toString());
					}
					writeProperties(doc, pel, p.getSubProperties());
					el.appendChild(pel);
				}
			}
		}
		protected void writeReferences(Document doc, Element el, UObject instance, List<UStructuralFeature> allReferences) {
			for (UStructuralFeature f : allReferences){
				Object value = f.get(instance);
				if (value == null)
					continue;
				if (f.isMany()){
					List list = (List)value;
					for (Object v : list){
						if (v != null){
							Element child = writeSingleReference(doc, f, (UObject) v, instance); //we now that its an UObject, since its part of an reference
							if (child != null)
								el.appendChild(child);
						}
					}
				}else{
					Element child = writeSingleReference(doc, f, (UObject) value, instance);
					if (child != null)
						el.appendChild(child);
				}
			}
		}

		/**
		 * 
		 * @param doc
		 * @param f
		 * @param instance
		 * @param parent parent from which this method is called, not necessarily the container of the UObject. This attribute may be used to decide if it's owned by the caller
		 * @return
		 */
		protected Element writeSingleReference(Document doc, UStructuralFeature f, UObject instance, UObject parent) {
			Element el = doc.createElement(f.getName());
			String id = getID(instance);
			if (id == null){ // if the ID is null, the object has not yet been serialized
				if (instance == null)
					ULog.error("can not serialize null instance: " + instance + " Feature: " + f);
				UClassifier cl = instance.getUClassifier();
				if (cl != f.getType()){
					el.setAttributeNS(XSI_NS, "xsi:type", cl.getName());
				}
				el.setAttributeNS(XMI_NS, "xmi:id", createID(instance));
				Collection<IProperty> allProperties = instance.getAllProperties();
				if (allProperties != null && !allProperties.isEmpty())
					writeProperties(doc, el, allProperties);
				writeAttributes(doc, el, instance, cl.getAllAttributes());
				writeReferences(doc, el, instance, cl.getAllReferences());
			}else{
				el.setAttributeNS(XMI_NS, "xmi:ref", id);
			}
			return el;
		}

		protected void writeAttributes(Document doc, Element el, UObject instance, List<UStructuralFeature> allAttributes) {
			for (UStructuralFeature attr : allAttributes){ 
				Object value = attr.get(instance);
				if (value != null){ //no need to write anything, if the value is not defined
					UType type = attr.getType();
					if (attr.isMany()){
						for (Object v : ((List)value)){
							if (v == null) 
								continue;
							Element list_entry = doc.createElement(attr.getName());
							if (type instanceof UPrimitiveType) {
								list_entry.setTextContent(((UPrimitiveType)type).toString(v));
							}else
								list_entry.setTextContent(v.toString());
//							str += v.toString() + " ";
							el.appendChild(list_entry);
						}
					}else{
						String str = null;
						if (type instanceof UPrimitiveType) 
							str = ((UPrimitiveType)type).toString(value);
						else
							str = value.toString();
						el.setAttribute(attr.getName(), str);
					}					
				}
			}
		}
	}

	public static class XMLReaderImpl {
		XMLReader 							mReader = null;
		
		private HashMap<String, UObject> 	mInstanceMap = new HashMap<String, UObject>();
		private IXMLReadListener 			mCallback;
		private boolean 					mAcceptMissingReferences = false;
		
		public XMLReaderImpl(IXMLReadListener callback) {
			mCallback = callback;
		}
		void enableMissingReferences(boolean b) { mAcceptMissingReferences = b; }
		
		private void rememberID(Node node, UObject instance) {
			String id = mReader.getAttributeValue(node, "xmi:id");
			if (id != null)
				mInstanceMap.put(id, instance);
		}
		private UObject getInstance(String id){
			return mInstanceMap.get(id);
		}
		
		public UObject read(InputStream stream) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, InstantiationException {
			mReader = new XMLReader();
			mReader.parseStream(stream);
			Node rootNode = mReader.getDocument().getFirstChild();

			return read(rootNode);
		}

		/** reads a UObject from a given XML node 
		 * @throws InstantiationException 
		 * @throws ClassNotFoundException */
		public UObject read(Node rootNode, UClass cl) throws ClassNotFoundException, InstantiationException {
			UObject instance = cl.createNewInstance();
			rememberID(rootNode, instance);
			readAttributes(rootNode, instance, cl);
			try {
				readReferences(rootNode, instance, cl);
			}catch(ClassNotFoundException cnfe) {
				if (!mAcceptMissingReferences)
					throw cnfe;
				ULog.warn("Ignoring unknown reference: " + cnfe.getMessage());
			}
			if (mCallback != null)
				try { mCallback.onObjectRead(instance, rootNode, mReader, this); }catch(Exception e) {e.printStackTrace();} //notify listener, if we have one
			return instance;
		}
		public UObject read(Node rootNode) throws ClassNotFoundException, InstantiationException {
			String str = rootNode.getNodeName();
            if (str.contains(":")) {
                str = str.substring(str.indexOf(":") + 1);
            }
			UClass cl = UCoreMetaRepository.findClassBySimpleName(str);
			if (cl == null){
                StringBuilder sb = new StringBuilder();
				for (UClassifier _c : UCoreMetaRepository.getAllClassifier()){
					sb.append(_c.getName()).append("\n");
				}
                LOG.debug("List of available UClssifiers: \n" + sb.toString());
				throw new NullPointerException("Could not find Classifier for: " + str);
			}
			return read(rootNode, cl);
		}

		private void readProperty(Node n, UObject instance, String parentName) {
			String name = mReader.getAttributeValue(n, "n");
			String desc = mReader.getAttributeValue(n, "d");
			boolean edit = mReader.getAttributeValueAsBoolean(n, "e");
			String typeName = mReader.getAttributeValue(n, "t");
			if (typeName != null){
				String valueStr = mReader.getAttributeValue(n, "v");
				IProperty p = instance.addProperty(parentName+name, desc, edit);
				if (typeName.equals("String")){
					p.setValue(valueStr);
				}else if (typeName.equals("Byte") || typeName.equals("Char"))
					p.setValue(Byte.parseByte(valueStr));
				else if (typeName.equals("Short"))
					p.setValue(Short.parseShort(valueStr));
				else if (typeName.equals("Integer"))
					p.setValue(Integer.parseInt(valueStr));
				else if (typeName.equals("Long"))
					p.setValue(Long.parseLong(valueStr));
				else if (typeName.equals("Float"))
					p.setValue(Float.parseFloat(valueStr));
				else if (typeName.equals("Double"))
					p.setValue(Double.parseDouble(valueStr));
				else if (typeName.equals("Boolean"))
					p.setValue(Boolean.parseBoolean(valueStr));
			}
			Collection<Node> children = mReader.getChildNodes(n, "UProperty");
			for (Node cn : children){
				readProperty(cn, instance, parentName + name + ".");
			}			
		}
		private void readReferences(Node node, UObject instance, UClass cl) throws ClassNotFoundException, InstantiationException {
			HashSet<UStructuralFeature> readFeatures = new HashSet<UStructuralFeature>();
			for (Node child : mReader.getChildren(node)){
				UStructuralFeature feature = cl.getFeature(child.getNodeName());
				if (feature == null){
					if (child.getNodeName().equals("UProperty")){
						readProperty(child, instance, "");
					}
					continue;
				}
				if (feature.isReadOnly() ) {
					//if readonly we would get an exception
//					continue;
				}
				if (feature.isMany() && !feature.isReadOnly()){
					if (readFeatures.contains(feature) == false){
						readFeatures.add(feature);
						//if we read a list feature the first time, clear possible default values
						List l = (List)instance.uGet(feature);
						l.clear();
					}
				}
				if (feature.isMany() && feature.isAttribute()){
					readAttributeCollectionElement(child, instance, cl, feature);
				}else{
					String ref = mReader.getAttributeValue(child, "xmi:ref");
					UObject child_instance = null; 
					if (feature.isReadOnly()) {
						//try to get the existing instance and fill it with stored values
						child_instance = (UObject)feature.get(instance);
					}
					if (ref != null){
						child_instance = getInstance(ref);
						assert(child_instance != null);
					}else{
						String type = mReader.getAttributeValue(child, "xsi:type");
						UClassifier child_cl = (UClassifier) feature.getType(); //we know that its a classifier, since its part of an reference
						if (type != null)
							child_cl = UCoreMetaRepository.findClassifierBySimpleName(type);
						if (child_cl == null){
							//ask the callback if there are has been some changes in the model
							if (mCallback != null)
								try{ child_cl = mCallback.onCouldNotFindClassifier(type, instance, feature, node, mReader, this); }catch(Exception e) {e.printStackTrace();}
						}
						if (child_cl == null) { //if we still could not find the classifier we have to aboard at this point 
							throw new ClassNotFoundException("Could not find Class: " + type);
						}else if (child_cl instanceof UClass && ((UClass)child_cl).getAbstract())
							ULog.error("The class: " + type + " is abstract and can not be instantiiated " + node);// InstantiationException
						if (child_cl instanceof UEnum){
							ULog.error("TODO: Read Enumeration value");
						}else{
							if (child_cl instanceof UClass == false){
								ULog.error("Classifier: " + child_cl.getName() + " is not an UClass");
							}
							UClass child_class = (UClass)child_cl;
							if (child_instance == null)
								child_instance = child_class.createNewInstance();
							rememberID(child, child_instance);
							readAttributes(child, child_instance, child_class);
							readReferences(child, child_instance, child_class);
							if (mCallback != null)
								try { mCallback.onObjectRead(child_instance, child, mReader, this); }catch(Exception e) {e.printStackTrace();} //notify listener, if we have one
						}
					}
					assert(child_instance != null);
					if (feature.isMany()){
						instance.uAdd(feature, child_instance);
					}else
						if (feature.isReadOnly() == false)
							feature.set(instance, child_instance);
				}
			}
		}

		private void readAttributeCollectionElement(Node child, UObject instance, UClass cl, UStructuralFeature feature) {
			try {
				String txt = child.getTextContent();
				Object value = ((UPrimitiveType)feature.getType()).parseString(txt);
				if (value != null){
					instance.uAdd(feature, value);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		private void readAttributes(Node node, UObject instance, UClass cl) {
			for (Attr attr : mReader.getAttributes(node)){
				UStructuralFeature f = cl.getFeature(attr.getName());
				if (f != null && f.isReadOnly() == false){
					if (f.isMany()){
//						Collection values = readAttributeValues(f, attr);
//						if (values != null){
//							List l = (List)f.get(instance);
//							if (l != null)
//								l.addAll(values);
//						}
						throw new UnsupportedOperationException();
					}else{
						Object value = readAttributeValue(f, attr);
						if (value != null)
							f.set(instance, value);
					}
				}
			}
		}
		
		public Object readAttributeValue(UStructuralFeature f, Attr attr) {
			if (f.getType() instanceof UPrimitiveType){
				Object value = ((UPrimitiveType)f.getType()).parseString(attr.getValue());
				if (value != null)
					return value;
			}else{
				UEnum en = (UEnum) f.getType();
				Object value = en.createNewInstance(attr.getValue());
				if (value != null)
					return value;
			}
			return null;
		}
		
				
	}

	static class MultipleReadCallbacks implements IXMLReadListener {
		ArrayList<IXMLReadListener> 	mDelegates = new ArrayList<>();
		@Override
		public void onObjectRead(UObject object, Node xmlNode, XMLReader reader, XMLReaderImpl serializer) {
			for (IXMLReadListener d : mDelegates) {
				try { d.onObjectRead(object, xmlNode, reader, serializer); }catch(Exception e) {e.printStackTrace();}
			}
		}
		@Override
		public UClassifier onCouldNotFindClassifier(String type, UObject instance, UStructuralFeature feature,
				Node node, XMLReader mReader, XMLReaderImpl xmlReaderImpl) {
			for (IXMLReadListener d : mDelegates) {
				try { 
					UClassifier cl = d.onCouldNotFindClassifier(type, instance, feature, node, mReader, xmlReaderImpl);
					if (cl != null)
						return cl;
				}catch(Exception e) {e.printStackTrace();}
			}
			return null;
		}
	}

	
	
	private MultipleReadCallbacks mReadCallback = null;
	
	
	
	public XMLSerializer() {
		XMLCompatibilityManager mgr = ExtensionPointManager.getExtensionPoint(XMLCompatibilityManager.class);
		if (mgr != null)
			addReadCallback(mgr);
	}
	
	public void addReadCallback(IXMLReadListener callback) { 
		if (mReadCallback == null) mReadCallback = new MultipleReadCallbacks();
		mReadCallback.mDelegates.add(callback);
	}
	public void removeReadCallback(IXMLReadListener callback) { 
		if (mReadCallback == null) return ;
		mReadCallback.mDelegates.remove(callback);
		if (mReadCallback.mDelegates.isEmpty()) mReadCallback = null;
	}
	
	/** if set to true, the serializer accepts missing references, e.g. ClassNotFoundExceptions if a model has not been loaded.
	 * @note this option is disabled by defeault as the resulting object may not be the same as the serialized object -> use with caution
	 */
	private boolean mAcceptMissingReferences = false; 
	public void setAcceptMissingReferences(boolean b) { mAcceptMissingReferences = b; }
	public boolean isAcceptMissingReferencesEnabled() { return mAcceptMissingReferences; }
	
	
	public String serialize(UObject instance){
		try {
			return new XMLWriter().write(instance);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void serialize(UObject instance, OutputStream stream) {
		try {
			new XMLWriter().write(instance, stream);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}



	@Override
	public UObject deserialize(InputStream stream) {
		try{
			XMLReaderImpl reader = new XMLReaderImpl(mReadCallback);
			reader.enableMissingReferences(isAcceptMissingReferencesEnabled());
			return reader.read(stream);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
