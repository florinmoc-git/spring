package com.hospital.patientadmin.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "@class", "entry" })
@Generated("jsonschema2pojo")
public class Statistics {

	@JsonProperty("@class")
	private String _class;
	@JsonProperty("entry")
	private List<Entry> entry = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("@class")
	public String getClass_() {
		return _class;
	}

	@JsonProperty("@class")
	public void setClass_(String _class) {
		this._class = _class;
	}

	@JsonProperty("entry")
	public List<Entry> getEntry() {
		return entry;
	}

	@JsonProperty("entry")
	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Statistics.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("_class");
		sb.append('=');
		sb.append(((this._class == null) ? "<null>" : this._class));
		sb.append(',');
		sb.append("entry");
		sb.append('=');
		sb.append(((this.entry == null) ? "<null>" : this.entry));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}