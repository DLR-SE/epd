package de.emir.epd.nmeasensor.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiverProperty {
	private static final String MAP_NMEASENTENCES = "nmeaSentences";

	private static final String MAP_ISOUTPUT = "isOutput";

	private static final String MAP_ISACTIVE = "isActive";

	private static final String MAP_ATTRIBUTES = "attributes";

	private static final String MAP_RECEIVERTYPE = "receiverType";

	private static final String MAP_LABEL = "label";

	public enum ReceiverType {
		UDP, TCP, Serial, File
	}
	
	private static final long serialVersionUID = -2580547818415124300L;

	private String label;
	private ReceiverType receiverType;
	private Map<String, Object> attributes;
	private boolean isActive;
	private boolean isOutput;
	private List<SentenceType> nmeaSentences;
	
	public ReceiverProperty() {
		
	}

	public ReceiverProperty(String label, ReceiverType receiverType, Map<String, Object> attributes, boolean isActive,
			boolean isOutput, List<SentenceType> nmeaSentences) {
		super();
		this.label = label;
		this.receiverType = receiverType;
		this.attributes = attributes;
		this.isActive = isActive;
		this.isOutput = isOutput;
		this.nmeaSentences = nmeaSentences;
	}
	
	public ReceiverProperty(ReceiverProperty _copy) {
		super();
		this.label = _copy.label;
		this.receiverType = _copy.receiverType;
		this.attributes = _copy.attributes;
		this.isActive = _copy.isActive;
		this.isOutput = _copy.isOutput;
		this.nmeaSentences = _copy.nmeaSentences;
	}
	
	public ReceiverProperty(Map<String, Object> data) {
		super();
		fromMap(data);
	}

	/**
	 * @return the type
	 */
	public ReceiverType getReceiverType() {
		if (receiverType == null) {
			receiverType = ReceiverType.UDP;
		}
		return receiverType;
	}
	/**
	 * @param type the type to set
	 */
	public void setReceiverType(ReceiverType type) {
		this.receiverType = type;
	}
	/**
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
		}
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the isOutput
	 */
	public boolean isOutput() {
		return isOutput;
	}
	/**
	 * @param isOutput the isOutput to set
	 */
	public void setOutput(boolean isOutput) {
		this.isOutput = isOutput;
	}
	/**
	 * @return the nmeaSentences
	 */
	public List<SentenceType> getNmeaSentences() {
		if (nmeaSentences == null) {
			nmeaSentences = new ArrayList<SentenceType>();
		}
		return nmeaSentences;
	}

	public List<String> getNmeaSentencesAsStrings() {
		List<String> result = new ArrayList<String>();
		for (SentenceType st : getNmeaSentences()) {
			result.add(st.name());
		}
		return result;
	}
	/**
	 * @param nmeaSentences the nmeaSentences to set
	 */
	public void setNmeaSentences(List<SentenceType> nmeaSentences) {
		this.nmeaSentences = nmeaSentences;
	}

	public void setNmeaSentencesFromStrings(List<String> nmeaSentenceNames) {
		if (nmeaSentenceNames == null) return;
		nmeaSentences = new ArrayList<SentenceType>();
		for (String name : nmeaSentenceNames) {
			nmeaSentences.add(SentenceType.valueOf(name));
		}
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReceiverProperty [label=")
				.append(getLabel()).append(", receiverType=")
				.append(getReceiverType().name()).append(", isActive=")
				.append(isActive()).append(", isOutput=")
				.append(isOutput()).append(", attributes=")
				.append(getAttributes()).append(", nmeaSentences=")
				.append(getNmeaSentences()).append("]");
		return builder.toString();
	}
	
	/**
	 * This is just a workaround to help serealization until a real converter can be used.
	 * @return The objects complete data as a HashMap.
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<>();
		result.put(MAP_LABEL, label);
		result.put(MAP_RECEIVERTYPE, getReceiverType().name());
		result.put(MAP_ATTRIBUTES, getAttributes());
		result.put(MAP_ISACTIVE, isActive);
		result.put(MAP_ISOUTPUT, isOutput);
		result.put(MAP_NMEASENTENCES, getNmeaSentencesAsStrings());
		return result;
	}

	/**
	 * This is a helper method to copy all data from a HashMap into this object. This can be deprecated and replaced as
	 * soon as a converter is available.
	 * @param data The data to load into this object in form of a HashMap.
	 */
	public void fromMap(Map<String, Object> data) {
		if (data == null) return;
		if (data.containsKey(MAP_LABEL) && data.get(MAP_LABEL) != null) label = (String) data.get(MAP_LABEL);
		if (data.containsKey(MAP_RECEIVERTYPE) && data.get(MAP_RECEIVERTYPE) != null) receiverType = ReceiverType.valueOf((String) data.get(MAP_RECEIVERTYPE));
		if (data.containsKey(MAP_ATTRIBUTES) && data.get(MAP_ATTRIBUTES) != null) attributes = (Map<String, Object>) data.get(MAP_ATTRIBUTES);
		if (data.containsKey(MAP_ISACTIVE) && data.get(MAP_ISACTIVE) != null) isActive = (Boolean) data.get(MAP_ISACTIVE);
		if (data.containsKey(MAP_ISOUTPUT) && data.get(MAP_ISOUTPUT) != null) isOutput = (Boolean) data.get(MAP_ISOUTPUT);
		if (data.containsKey(MAP_NMEASENTENCES) && data.get(MAP_NMEASENTENCES) != null) this.setNmeaSentencesFromStrings((List<String>) data.get(MAP_NMEASENTENCES));
	}
}
